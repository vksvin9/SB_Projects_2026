package com.vin.service.impl;

import com.vin.dto.request.QuestionRequest;
import com.vin.dto.response.QuestionResponse;
import com.vin.entity.Exam;
import com.vin.entity.Question;
import com.vin.exception.ResourceNotFoundException;
import com.vin.repository.ExamRepository;
import com.vin.repository.QuestionRepository;
import com.vin.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl
                implements QuestionService {

        private final QuestionRepository questionRepository;

        private final ExamRepository examRepository;

        private final ModelMapper modelMapper;

        @Override
        public QuestionResponse createQuestion(
                        QuestionRequest request) {

                Exam exam = examRepository.findById(
                                request.getExamId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Exam Not Found"));

                Question question = new Question();

                question.setQuestionText(
                                request.getQuestionText());

                question.setOptionA(
                                request.getOptionA());

                question.setOptionB(
                                request.getOptionB());

                question.setOptionC(
                                request.getOptionC());

                question.setOptionD(
                                request.getOptionD());

                question.setCorrectAnswer(
                                request.getCorrectAnswer());

                question.setMarks(
                                request.getMarks());

                question.setExam(exam);

                Question savedQuestion = questionRepository.save(
                                question);

                QuestionResponse response = new QuestionResponse();

                response.setId(
                                savedQuestion.getId());

                response.setQuestionText(
                                savedQuestion.getQuestionText());

                response.setOptionA(
                                savedQuestion.getOptionA());

                response.setOptionB(
                                savedQuestion.getOptionB());

                response.setOptionC(
                                savedQuestion.getOptionC());

                response.setOptionD(
                                savedQuestion.getOptionD());

                response.setCorrectAnswer(
                                savedQuestion.getCorrectAnswer());

                response.setMarks(
                                savedQuestion.getMarks());

                response.setExamTitle(
                                exam.getTitle());

                return response;
        }

        @Override
        public List<QuestionResponse> getQuestionsByExam(Long examId) {

                return questionRepository.findAll()
                                .stream()
                                .filter(question -> question.getExam()
                                                .getId()
                                                .equals(examId))
                                .map(question -> {

                                        QuestionResponse response = modelMapper.map(
                                                        question,
                                                        QuestionResponse.class);

                                        response.setExamTitle(
                                                        question.getExam()
                                                                        .getTitle());

                                        return response;
                                })
                                .toList();
        }

        @Override
        public QuestionResponse getQuestionById(Long id) {

                Question question = questionRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Question Not Found"));

                QuestionResponse response = modelMapper.map(
                                question,
                                QuestionResponse.class);

                response.setExamTitle(
                                question.getExam()
                                                .getTitle());

                return response;
        }

        @Override
        public QuestionResponse updateQuestion(
                        Long id,
                        QuestionRequest request) {

                Question question = questionRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Question Not Found"));

                Exam exam = examRepository.findById(
                                request.getExamId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Exam Not Found"));

                question.setQuestionText(
                                request.getQuestionText());

                question.setOptionA(
                                request.getOptionA());

                question.setOptionB(
                                request.getOptionB());

                question.setOptionC(
                                request.getOptionC());

                question.setOptionD(
                                request.getOptionD());

                question.setCorrectAnswer(
                                request.getCorrectAnswer());

                question.setMarks(
                                request.getMarks());

                question.setExam(exam);

                Question updatedQuestion = questionRepository.save(
                                question);

                QuestionResponse response = new QuestionResponse();

                response.setId(
                                updatedQuestion.getId());

                response.setQuestionText(
                                updatedQuestion.getQuestionText());

                response.setOptionA(
                                updatedQuestion.getOptionA());

                response.setOptionB(
                                updatedQuestion.getOptionB());

                response.setOptionC(
                                updatedQuestion.getOptionC());

                response.setOptionD(
                                updatedQuestion.getOptionD());

                response.setCorrectAnswer(
                                updatedQuestion.getCorrectAnswer());

                response.setMarks(
                                updatedQuestion.getMarks());

                response.setExamTitle(
                                exam.getTitle());

                return response;
        }

        @Override
        public void deleteQuestion(Long id) {

                Question question = questionRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Question Not Found"));

                questionRepository.delete(question);
        }
}