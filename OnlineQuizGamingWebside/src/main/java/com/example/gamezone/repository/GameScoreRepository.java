package com.example.gamezone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gamezone.entity.GameScore;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface GameScoreRepository extends JpaRepository<GameScore, Long> {
	 List<GameScore> findAllByOrderByScoreDesc();
	 @Modifying
	 @Transactional
	 void deleteByUsername(String username);
}