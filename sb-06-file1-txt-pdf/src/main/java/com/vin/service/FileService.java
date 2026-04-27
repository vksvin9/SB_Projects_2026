package com.vin.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vin.dto.FileResponse;

@Service
public class FileService {

    private static final String BASE_DIR = "files/";

    // WRITE FILE
    public FileResponse writeToFile(String content, String baseUrl) {
        try {
            String fileName = "file_" + System.currentTimeMillis() + ".txt";

            Path path = Paths.get(BASE_DIR).resolve(fileName).normalize();

            Files.createDirectories(path.getParent());
            Files.write(path, content.getBytes());

            String downloadUrl = baseUrl + "/files/download/" + fileName;

            return new FileResponse(fileName, downloadUrl);

        } catch (IOException e) {
            throw new RuntimeException("Error writing file", e);
        }
    }

    // DOWNLOAD FILE
    public ResponseEntity<Resource> downloadFile(String fileName) {
        try {
            Path basePath = Paths.get(BASE_DIR).toAbsolutePath().normalize();
            Path filePath = basePath.resolve(fileName).normalize();

            // Security check (path traversal)
            if (!filePath.startsWith(basePath)) {
                throw new RuntimeException("Invalid file path");
            }

            System.out.println("FILE PATH: " + filePath);

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new RuntimeException("File not found: " + filePath);
            }

            String contentType = Files.probeContentType(filePath);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE,
                            contentType != null ? contentType : "application/octet-stream")
                    .body(resource);

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error reading file", e);
        } catch (IOException e) {
            throw new RuntimeException("Error determining file type", e);
        }
    }
}