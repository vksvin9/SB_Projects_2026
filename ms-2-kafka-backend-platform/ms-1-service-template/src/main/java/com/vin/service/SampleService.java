package com.vin.service;

import com.vin.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public String greet(String name) {
        return "Hello, " + name;
    }

    public String findById(Long id) {
        if (id != 1L) {
            throw new ResourceNotFoundException("Record not found with id: " + id);
        }
        return "Sample Record Found";
    }
}