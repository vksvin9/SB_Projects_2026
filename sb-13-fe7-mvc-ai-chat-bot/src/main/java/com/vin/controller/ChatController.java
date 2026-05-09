package com.vin.controller;

import com.vin.dto.ChatMessageDto;
import com.vin.dto.ChatRequestDto;
import com.vin.dto.ChatResponseDto;
import com.vin.service.ChatService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatResponseDto chat(@RequestBody ChatRequestDto request,
                                HttpSession session) {

        List<ChatMessageDto> chatHistory =
                (List<ChatMessageDto>) session.getAttribute("chatHistory");

        if (chatHistory == null) {
            chatHistory = new ArrayList<>();
            session.setAttribute("chatHistory", chatHistory);
        }

        chatHistory.add(new ChatMessageDto("user", request.getMessage()));

        String aiResponse = chatService.ask(request.getMessage());

        chatHistory.add(new ChatMessageDto("assistant", aiResponse));

        return new ChatResponseDto(aiResponse);
    }

    @GetMapping(value = "/stream", produces = "text/plain;charset=UTF-8")
    public Flux<String> stream(@RequestParam String message,
                               HttpSession session) {

        List<ChatMessageDto> chatHistory =
                (List<ChatMessageDto>) session.getAttribute("chatHistory");

        if (chatHistory == null) {
            chatHistory = new ArrayList<>();
            session.setAttribute("chatHistory", chatHistory);
        }

        chatHistory.add(new ChatMessageDto("user", message));

        List<String> chunks = new ArrayList<>();
        List<ChatMessageDto> finalChatHistory = chatHistory;

        return chatService.stream(message)
                .doOnNext(chunks::add)
                .doOnComplete(() -> {
                    String fullResponse = String.join("", chunks);
                    finalChatHistory.add(
                            new ChatMessageDto("assistant", fullResponse)
                    );
                });
    }

    @PostMapping("/clear")
    public void clearChat(HttpSession session) {
        session.removeAttribute("chatHistory");
    }

    @PostMapping("/exit")
    public void exitChat(HttpSession session) {
        session.invalidate();
    }
}