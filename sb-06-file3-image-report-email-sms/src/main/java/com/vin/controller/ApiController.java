package com.vin.controller;

import com.vin.entity.User;
import com.vin.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final PhotoService photoService;
    private final PdfService pdfService;
    private final MailService mailService;
    private final OtpService otpService;

    @PostMapping("/upload/{id}")
    public String upload(@PathVariable Long id,
                         @RequestParam MultipartFile file) throws Exception {
        photoService.upload(id, file);
        return "uploaded";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {

        User u = photoService.get(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(u.getPhotoType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + u.getPhotoName())
                .body(u.getPhoto());
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> pdf() throws Exception {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfService.generate());
    }

    @GetMapping("/mail")
    public String mail(@RequestParam String to) throws Exception {
        mailService.send(to);
        return "mail sent";
    }
    
    @PostMapping("/otp/send")
    public String sendOtp(@RequestParam String mobile) {
        return otpService.sendOtp(mobile);
    }

    @PostMapping("/otp/verify")
    public String verifyOtp(@RequestParam String mobile,
                            @RequestParam String otp) {
        return otpService.verifyOtp(mobile, otp);
    }
}