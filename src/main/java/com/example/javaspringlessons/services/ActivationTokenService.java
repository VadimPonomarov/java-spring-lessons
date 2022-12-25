package com.example.javaspringlessons.services;

import com.example.javaspringlessons.dao.ActivationTokenDAO;
import com.example.javaspringlessons.models.ActivationToken;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivationTokenService {
    private ActivationTokenDAO activationTokenDAO;

    /*@Scheduled(fixedDelay = 1000*60*60)*/
    public void deleteTokenIfExpired() {
        System.out.println("work");
        List<ActivationToken> tokenList = activationTokenDAO.findAll();
        tokenList.forEach(activationToken -> {
            if (LocalDateTime.now().isAfter(activationToken.getExpire())) {
                activationTokenDAO.deleteById(activationToken.getId());
            }
        });

    }
}
