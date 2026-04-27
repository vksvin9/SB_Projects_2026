package com.vin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vin.service.FileProcessingService;

@RestController
@RequestMapping("/process")
public class FileProcessingController {

    @Autowired
    private FileProcessingService processingService;

    // TextFile / PDF → TEXT
    @PostMapping("/extract-text")
    public String extractText(@RequestParam("file") MultipartFile file) {
        return processingService.extractText(file);
    }
}