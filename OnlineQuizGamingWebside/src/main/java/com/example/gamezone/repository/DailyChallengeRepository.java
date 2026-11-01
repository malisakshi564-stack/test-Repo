package com.example.gamezone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamezone.entity.DailyChallenge;

public interface DailyChallengeRepository
        extends JpaRepository<DailyChallenge, Long> {

    Optional<DailyChallenge> findByChallengeDate(String challengeDate);
}