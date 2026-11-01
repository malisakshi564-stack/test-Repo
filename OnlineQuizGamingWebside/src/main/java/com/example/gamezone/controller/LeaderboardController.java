package com.example.gamezone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gamezone.entity.GameScore;
import com.example.gamezone.service.GameScoreService;

import jakarta.servlet.http.HttpSession;
import java.util.Map;
@Controller
public class LeaderboardController {

    @Autowired
    private GameScoreService gameScoreService;


    // Leaderboard page
    @GetMapping("/leaderboard")
    public String leaderboard(Model model) {

        List<GameScore> scores =
                gameScoreService.getAllScores();

        List<com.example.gamezone
.LeaderboardRow> leaderboardRows =
                new java.util.ArrayList<>();

        java.util.Map<String,  com.example.gamezone
.LeaderboardRow> map =
                new java.util.LinkedHashMap<>();

        for (GameScore score : scores) {

            String username = score.getUsername();

            com.example.gamezone
.LeaderboardRow row =
                    map.get(username);

            if (row == null) {
                row = new com.example.gamezone
.LeaderboardRow();
                row.setUsername(username);
                map.put(username, row);
            }

            if ("Quiz Game".equals(score.getGameName())) {
                row.setQuizScore(
                        row.getQuizScore() + score.getScore()
                );
            }

            if ("Tic-Tac-Toe".equals(score.getGameName())) {
                row.setTicTacToeScore(
                        row.getTicTacToeScore() + score.getScore()
                );
            }

            if ("Rock Paper Scissors".equals(score.getGameName())) {

                row.setRpsScore(
                        row.getRpsScore() + score.getScore()
                );
            }

            if ("Memory Game".equals(score.getGameName())) {

                row.setMemoryScore(
                        row.getMemoryScore() + score.getScore()
                );
            }
        }

        leaderboardRows.addAll(map.values());

        model.addAttribute("leaderboardRows", leaderboardRows);

        return "leaderboard";
    }
    @GetMapping("/delete-score/{username}")
    public String deleteUserScores(@PathVariable String username) {

        gameScoreService.deleteScoresByUsername(username);

        return "redirect:/leaderboard";
    }

    // Save Quiz Score
    @PostMapping("/save-score")
    @ResponseBody
    public String saveScore(@RequestBody GameScore gameScore,
                            HttpSession session) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "User not logged in!";
        }

        gameScore.setUsername(username);

        gameScoreService.saveScore(gameScore);

        return "Score Saved Successfully!";
    }
}