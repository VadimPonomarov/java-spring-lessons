package com.example.javaspringlessons.dao;

import com.example.javaspringlessons.models.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationTokenDAO extends JpaRepository<ActivationToken, Integer> {
}
