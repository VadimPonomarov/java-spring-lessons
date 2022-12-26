package com.example.javaspringlessons.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ActivationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token = UUID.randomUUID().toString();
    private LocalDateTime expire = LocalDateTime.now(ZoneId.of("Europe/Kiev")).plusHours(1);
}
