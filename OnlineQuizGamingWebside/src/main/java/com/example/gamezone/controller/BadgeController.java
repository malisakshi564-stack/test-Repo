package com.example.gamezone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.gamezone.entity.Badge;
import com.example.gamezone.service.BadgeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @GetMapping("/badges")
    public String badges(HttpSession session, Model model) {

        String username =
                (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/login";
        }

        List<Badge> badges =
                badgeService.getUserBadges(username);

        model.addAttribute("badges", badges);

        return "badges";
    }
}