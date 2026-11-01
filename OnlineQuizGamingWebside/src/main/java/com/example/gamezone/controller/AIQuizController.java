package com.example.gamezone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamezone.service.AIQuizService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AIQuizController {

    @Autowired
    private AIQuizService aiQuizService;

    @GetMapping("/ai-quiz")
    public String aiQuiz() {
        return "ai-quiz";
    }
    @PostMapping("/generate-ai-quiz")
    public String generateQuiz(
            @RequestParam String topic,
            RedirectAttributes redirectAttributes) {

        String result = aiQuizService.generateQuiz(topic);

        redirectAttributes.addFlashAttribute("result", result);

        return "redirect:/ai-quiz";
    }
}