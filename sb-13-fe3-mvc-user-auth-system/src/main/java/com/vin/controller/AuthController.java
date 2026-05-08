package com.vin.controller;

import com.vin.entity.User;
import com.vin.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute User user,
            BindingResult result
    ) {

        if (result.hasErrors()) {

            return "register";
        }

        userService.register(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {

        User user =
                userService.login(username, password);

        if (user == null) {

            model.addAttribute(
                    "error",
                    "Invalid credentials"
            );

            return "login";
        }

        session.setAttribute("loggedInUser", user);

        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        if (
                session.getAttribute("loggedInUser") == null
        ) {
            return "redirect:/login";
        }

        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}