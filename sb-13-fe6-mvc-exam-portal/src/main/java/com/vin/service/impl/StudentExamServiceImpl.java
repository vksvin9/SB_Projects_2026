package com.vin.service.impl;

import com.vin.dto.request.ExamSubmissionRequest;
import com.vin.dto.response.ExamResultResponse;
import com.vin.entity.Exam;
import com.vin.entity.Question;
import com.vin.entity.Result;
import com.vin.entity.Student;
import com.vin.entity.StudentAnswer;
import com.vin.entity.User;
import com.vin.exception.ResourceNotFoundException;
import com.vin.repository.ExamRepository;
import com.vin.repository.QuestionRepository;
import com.vin.repository.ResultRepository;
import com.vin.repository.StudentAnswerRepository;
import com.vin.repository.StudentRepository;
import com.vin.repository.UserRepository;
import com.vin.service.StudentExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentExamServiceImpl
        implements StudentExamService {

    private final ExamRepository examRepository;

    private final QuestionRepository questionRepository;

    private final ResultRepository resultRepository;

    private final StudentAnswerRepository
            studentAnswerRepository;

    private final UserRepository userRepository;

    private final StudentRepository studentRepository;

    @Override
    public List<Question> startExam(
            Long examId) {

        return questionRepository
                .findByExamId(examId);
    }

    @Override
    public ExamResultResponse submitExam(
            ExamSubmissionRequest request,
            String username) {

        Exam exam =
                examRepository.findById(
                        request.getExamId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Exam Not Found"));

        User user =
                userRepository.findByUsername(
                        username)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User Not Found"));

        Student student =
                studentRepository.findAll()
                        .stream()
                        .filter(s ->
                                s.getUser()
                                        .getId()
                                        .equals(user.getId()))
                        .findFirst()
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student Not Found"));

        /*
         Prevent Multiple Attempts
         */

        Optional<Result> existingResult =
                resultRepository
                        .findByStudentIdAndExamId(
                                student.getId(),
                                exam.getId());

        if (existingResult.isPresent()) {

            throw new RuntimeException(
                    "You already attempted this exam");
        }

        List<Question> questions =
                questionRepository.findByExamId(
                        exam.getId());

        int correctCount = 0;

        int wrongCount = 0;

        int score = 0;

        for (Question question : questions) {

            String selectedAnswer =
                    request.getAnswers()
                            .get(question.getId());

            boolean isCorrect =
                    question.getCorrectAnswer()
                            .equalsIgnoreCase(
                                    selectedAnswer);

            if (isCorrect) {

                correctCount++;

                score += question.getMarks();

            } else {

                wrongCount++;
            }

            StudentAnswer answer =
                    new StudentAnswer();

            answer.setStudent(student);

            answer.setExam(exam);

            answer.setQuestion(question);

            answer.setSelectedAnswer(
                    selectedAnswer);

            answer.setCorrect(isCorrect);

            studentAnswerRepository.save(
                    answer);
        }

        double percentage =
                ((double) score /
                        exam.getTotalMarks()) * 100;

        String status =
                percentage >= 40
                        ? "PASS"
                        : "FAIL";

        Result result =
                new Result();

        result.setStudent(student);

        result.setExam(exam);

        result.setScore(score);

        result.setPercentage(percentage);

        result.setStatus(status);

        result.setSubmittedAt(
                LocalDateTime.now());

        resultRepository.save(result);

        ExamResultResponse response =
                new ExamResultResponse();

        response.setExamTitle(
                exam.getTitle());

        response.setTotalQuestions(
                questions.size());

        response.setCorrectAnswers(
                correctCount);

        response.setWrongAnswers(
                wrongCount);

        response.setScore(score);

        response.setPercentage(
                percentage);

        response.setStatus(status);

        return response;
    }
}