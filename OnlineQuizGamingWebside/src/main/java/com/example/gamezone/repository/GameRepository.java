package com.example.gamezone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamezone.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}