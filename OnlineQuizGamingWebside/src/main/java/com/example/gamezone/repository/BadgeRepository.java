package com.example.gamezone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamezone.entity.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

    List<Badge> findByUsername(String username);
    boolean existsByUsernameAndBadgeName(String username, String badgeName);
}