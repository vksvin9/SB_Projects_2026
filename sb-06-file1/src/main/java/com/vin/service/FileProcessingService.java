package com.vin.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileProcessingService {

    public String extractText(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();

            if (fileName == null) {
                throw new RuntimeException("Invalid file name");
            }

            fileName = fileName.toLowerCase();

            // TXT
            if (fileName.endsWith(".txt")) {
                return new String(file.getBytes());
            }

            // PDF
            if (fileName.endsWith(".pdf")) {
                return extractFromPdf(file);
            }

            throw new RuntimeException("Only TXT and PDF supported");

        } catch (Exception e) {
            throw new RuntimeException("Error processing file", e);
        }
    }

    private String extractFromPdf(MultipartFile file) {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);

            return stripper.getText(document);

        } catch (Exception e) {
            throw new RuntimeException("PDF parsing failed", e);
        }
    }
}