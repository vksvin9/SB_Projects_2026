package com.vin.service;

import com.vin.entity.User;
import com.vin.helper.CsvHelper;
import com.vin.helper.ExcelHelper;
import com.vin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final CsvHelper csvHelper;
    private final ExcelHelper excelHelper;

    public void saveFile(MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();

        List<User> users;

        if (name.endsWith(".csv")) {
            users = csvHelper.parseCsv(file.getInputStream());
        } else {
            users = excelHelper.parseExcel(file.getInputStream());
        }

        repo.saveAll(users);
    }

    public ByteArrayInputStream exportCsv() {
        return csvHelper.toCsv(repo.findAll());
    }

    public ByteArrayInputStream exportExcel() {
        return excelHelper.toExcel(repo.findAll());
    }
}