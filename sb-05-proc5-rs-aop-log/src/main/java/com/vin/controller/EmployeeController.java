package com.vin.controller;

import com.vin.dto.CommonResponse;
import com.vin.model.Employee;
import com.vin.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/{dept}")
    public CommonResponse<List<Employee>> get(@PathVariable String dept) {

        return CommonResponse.<List<Employee>>builder()
                .status("SUCCESS")
                .message("DATA FETCHED")
                .data(service.getByDept(dept))
                .build();
    }
}