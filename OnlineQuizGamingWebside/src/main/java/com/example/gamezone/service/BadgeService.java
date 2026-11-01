package com.example.gamezone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gamezone.entity.Badge;
import com.example.gamezone.repository.BadgeRepository;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    public List<Badge> getUserBadges(String username) {
        return badgeRepository.findByUsername(username);
    }
    public Badge saveBadge(Badge badge) {

        boolean exists =
                badgeRepository.existsByUsernameAndBadgeName(
                        badge.getUsername(),
                        badge.getBadgeName()
                );

        if (exists) {
            return null;
        }

        return badgeRepository.save(badge);
    }
}