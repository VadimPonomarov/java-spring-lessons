package com.example.javaspringlessons.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ActivationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token = UUID.randomUUID().toString();
    private LocalDateTime expire = LocalDateTime.now(ZoneId.of("Europe/Kiev")).plusHours(1);
}
