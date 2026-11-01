package com.example.gamezone.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gamezone.entity.DailyChallenge;
import com.example.gamezone.repository.DailyChallengeRepository;

@Service
public class DailyChallengeService {

    @Autowired
    private DailyChallengeRepository challengeRepository;

    public DailyChallenge getTodayChallenge() {

        String today = LocalDate.now().toString();

        Optional<DailyChallenge> existing =
                challengeRepository.findByChallengeDate(today);

        if (existing.isPresent()) {
            return existing.get();
            
        }

        DailyChallenge challenge =
                new DailyChallenge(
                        today,
                        "Win one game today!",
                        "Any Game"
                );

        return challengeRepository.save(challenge);
    }
        public void completeTodayChallenge() {

            String today = java.time.LocalDate.now().toString();

            java.util.Optional<DailyChallenge> existing =
                    challengeRepository.findByChallengeDate(today);

            if (existing.isPresent()) {

                DailyChallenge challenge = existing.get();

                challenge.setCompleted(true);

                challengeRepository.save(challenge);
            }
        
    }
}