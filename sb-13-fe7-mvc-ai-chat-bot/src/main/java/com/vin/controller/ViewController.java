package com.vin.controller;

import com.vin.dto.ChatMessageDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index(Model model, HttpSession session) {

        List<ChatMessageDto> chatHistory =
                (List<ChatMessageDto>) session.getAttribute("chatHistory");

        if (chatHistory == null) {
            chatHistory = new ArrayList<>();
            session.setAttribute("chatHistory", chatHistory);
        }

        model.addAttribute("chatHistory", chatHistory);
        return "index";
    }
}