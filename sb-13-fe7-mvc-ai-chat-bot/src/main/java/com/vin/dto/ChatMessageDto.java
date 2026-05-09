package com.vin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatMessageDto {

    private String role;      // "user" or "assistant"
    private String content;
}