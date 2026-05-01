package com.vin.service;

import com.vin.model.Employee;
import com.vin.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repo;

    public List<Employee> getByDept(String dept) {
        return repo.fetchByDept(dept);
    }
}