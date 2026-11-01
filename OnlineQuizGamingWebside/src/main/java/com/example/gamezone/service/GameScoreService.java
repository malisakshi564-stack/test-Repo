package com.example.gamezone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gamezone.entity.GameScore;
import com.example.gamezone.repository.GameScoreRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameScoreService {
	

    @Autowired
    private GameScoreRepository gameScoreRepository;

    public GameScore saveScore(GameScore gameScore) {
        return gameScoreRepository.save(gameScore);
    }

    public List<GameScore> getAllScores() {
        return gameScoreRepository.findAllByOrderByScoreDesc();
    }
    @Transactional
  public void deleteScoresByUsername(String username) {
        gameScoreRepository.deleteByUsername(username);
    }
   
	
}