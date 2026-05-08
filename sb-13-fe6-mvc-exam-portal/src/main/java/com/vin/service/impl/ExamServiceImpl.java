package com.vin.service.impl;

import com.vin.dto.request.ExamRequest;
import com.vin.dto.response.ExamResponse;
import com.vin.entity.Exam;
import com.vin.exception.ResourceNotFoundException;
import com.vin.repository.ExamRepository;
import com.vin.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl
        implements ExamService {

    private final ExamRepository examRepository;

    private final ModelMapper modelMapper;

    @Override
    public ExamResponse createExam(
            ExamRequest request) {

        Exam exam =
                modelMapper.map(
                        request,
                        Exam.class
                );

        Exam savedExam =
                examRepository.save(exam);

        return modelMapper.map(
                savedExam,
                ExamResponse.class
        );
    }

    @Override
    public List<ExamResponse> getAllExams() {

        return examRepository.findAll()
                .stream()
                .map(exam ->
                        modelMapper.map(
                                exam,
                                ExamResponse.class
                        ))
                .toList();
    }

    @Override
    public ExamResponse getExamById(Long id) {

        Exam exam =
                examRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Exam Not Found"));

        return modelMapper.map(
                exam,
                ExamResponse.class
        );
    }

    @Override
    public ExamResponse updateExam(
            Long id,
            ExamRequest request) {

        Exam exam =
                examRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Exam Not Found"));

        exam.setTitle(request.getTitle());
        exam.setDescription(
                request.getDescription());
        exam.setDurationMinutes(
                request.getDurationMinutes());
        exam.setTotalMarks(
                request.getTotalMarks());

        Exam updatedExam =
                examRepository.save(exam);

        return modelMapper.map(
                updatedExam,
                ExamResponse.class
        );
    }

    @Override
    public void deleteExam(Long id) {

        Exam exam =
                examRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Exam Not Found"));

        examRepository.delete(exam);
    }
}