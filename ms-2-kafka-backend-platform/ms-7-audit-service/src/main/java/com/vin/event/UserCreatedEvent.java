package com.vin.event;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {

    private Long id;

    private String name;

    private String email;
}