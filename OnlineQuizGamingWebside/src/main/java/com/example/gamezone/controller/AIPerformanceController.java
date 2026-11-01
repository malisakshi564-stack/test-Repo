package com.example.gamezone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.gamezone.service.AIPerformanceService;

@Controller
public class AIPerformanceController {

    @Autowired
    private AIPerformanceService aiPerformanceService;

    @GetMapping("/ai-performance")
    public String aiPerformance(Model model) {

        String analysis =
                aiPerformanceService.analyzePerformance(
                        0, 0, 0, 0);

        model.addAttribute("analysis", analysis);

        return "ai-performance";
    }
}