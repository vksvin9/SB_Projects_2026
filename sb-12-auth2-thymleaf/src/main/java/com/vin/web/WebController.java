package com.vin.web;

import com.vin.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final AuthService service;

    // ---------- LOGIN PAGE ----------
    @GetMapping("/")
    public String home() {
        return "login";
    }

    // ---------- PROCESS LOGIN ----------
    @PostMapping("/do-login")
    public String login(@RequestParam String user,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        try {
            String token = service.login(user, password);

            // store token in session
            session.setAttribute("token", token);

            // ✅ redirect to dashboard
            return "redirect:/dashboard";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    // ---------- DASHBOARD (NEW) ----------
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        // protect route
        if (session.getAttribute("token") == null) {
            return "redirect:/";
        }

        return "dashboard";
    }

    // ---------- AUTH-IN PAGE ----------
    @GetMapping("/auth-in")
    public String authInPage(HttpSession session) {

        if (session.getAttribute("token") == null) {
            return "redirect:/";
        }

        return "auth-in";
    }

    // ---------- AUTH-IN ACTION ----------
    @PostMapping("/do-auth-in")
    public String doAuthIn(@RequestParam String token,
                           @RequestParam String uuid,
                           @RequestParam String api,
                           @RequestParam String ip,
                           @RequestParam String input,
                           Model model) {

        try {
            service.authIn(token, uuid, api, ip, input);

            model.addAttribute("msg", "Auth-In Success. UUID: " + uuid);
            model.addAttribute("uuid", uuid);

            return "auth-out";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth-in";
        }
    }

    // ---------- AUTH-OUT PAGE ----------
    @GetMapping("/auth-out")
    public String authOutPage(HttpSession session) {

        if (session.getAttribute("token") == null) {
            return "redirect:/";
        }

        return "auth-out";
    }

    // ---------- AUTH-OUT ACTION ----------
    @PostMapping("/do-auth-out")
    public String doAuthOut(@RequestParam String uuid,
                            @RequestParam String output,
                            Model model) {

        try {
            service.authOut(uuid, "encryptedIn", output, 100);

            model.addAttribute("msg", "Auth-Out Success");

            return "auth-out";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth-out";
        }
    }

    // ---------- LOGOUT ----------
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}