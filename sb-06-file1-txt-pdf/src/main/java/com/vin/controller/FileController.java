package com.vin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vin.dto.FileResponse;
import com.vin.service.FileService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    // CREATE FILE + RETURN DOWNLOAD URL
    @PostMapping("/text-to-file")
    public FileResponse createFile(HttpServletRequest request,
                                   @RequestBody String content) {

        // New (recommended)
        String baseUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();

        // Old way (manual) - reference
        /*
        String baseUrl = request.getScheme() + "://" +
                         request.getServerName() + ":" +
                         request.getServerPort();
        */

        return fileService.writeToFile(content, baseUrl);
    }

    // DOWNLOAD FILE
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        return fileService.downloadFile(fileName);
    }

    // DEBUG ENDPOINT
    @GetMapping("/test")
    public String test() {
        return "WORKING";
    }
}