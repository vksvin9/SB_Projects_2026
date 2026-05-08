package com.vin.controller;

import com.vin.entity.User;

import com.vin.service.UserService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    // Register Page
    @GetMapping("/register")
    public String registerForm(Model model) {

        model.addAttribute(
                "user",
                new User()
        );

        return "register";
    }

    // Register User
    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute User user,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            return "register";
        }

        service.registerUser(user);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Registration successful. Please login."
        );

        return "redirect:/login";
    }

    // Login Page
    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }
}