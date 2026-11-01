package com.example.gamezone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemoryGameController {

    @GetMapping("/memory-game")
    public String memoryGame() {
        return "memory-game";
    }
}