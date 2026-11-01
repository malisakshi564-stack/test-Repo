package com.example.gamezone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.gamezone.entity.DailyChallenge;
import com.example.gamezone.service.DailyChallengeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DailyChallengeController {

    @Autowired
    private DailyChallengeService challengeService;

    @GetMapping("/daily-challenge")
    public String dailyChallenge(
            HttpSession session,
            Model model) {

        String username =
                (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/login";
        }

        DailyChallenge challenge =
                challengeService.getTodayChallenge();

        model.addAttribute("challenge", challenge);

        return "daily-challenge";
    }
    @PostMapping("/complete-daily-challenge")
    public String completeDailyChallenge(
            HttpSession session) {

        String username =
                (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/login";
        }

        challengeService.completeTodayChallenge();

        return "redirect:/daily-challenge";
    }
}