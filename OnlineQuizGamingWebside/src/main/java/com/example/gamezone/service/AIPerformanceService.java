package com.example.gamezone.service;

import org.springframework.stereotype.Service;

@Service
public class AIPerformanceService {

    public String analyzePerformance(
            int quizScore,
            int ticTacToeScore,
            int rpsScore,
            int memoryScore) {

        int totalScore =
                quizScore
                + ticTacToeScore
                + rpsScore
                + memoryScore;

        StringBuilder feedback = new StringBuilder();

        feedback.append("🤖 AI Performance Analysis\n\n");

        feedback.append("🏆 Total Score: ")
                .append(totalScore)
                .append("\n\n");

        if (totalScore >= 300) {

            feedback.append(
                "🌟 Excellent performance! "
                + "You are performing very well across GameZone."
            );

        } else if (totalScore >= 150) {

            feedback.append(
                "👍 Good performance! "
                + "Keep playing and try to improve your scores."
            );

        } else {

            feedback.append(
                "💪 Keep practicing! "
                + "Regular gameplay can help improve your score."
            );
        }

        feedback.append("\n\n");

        if (quizScore >= 50) {
            feedback.append("🧠 Quiz: Strong performance\n");
        } else {
            feedback.append("🧠 Quiz: Needs more practice\n");
        }

        if (ticTacToeScore >= 50) {
            feedback.append("❌⭕ Tic-Tac-Toe: Strong performance\n");
        } else {
            feedback.append("❌⭕ Tic-Tac-Toe: Needs more practice\n");
        }

        if (rpsScore >= 50) {
            feedback.append("✊✋✌️ Rock Paper Scissors: Strong performance\n");
        } else {
            feedback.append("✊✋✌️ Rock Paper Scissors: Needs more practice\n");
        }

        if (memoryScore >= 50) {
            feedback.append("🧠 Memory Game: Strong performance\n");
        } else {
            feedback.append("🧠 Memory Game: Needs more practice\n");
        }

        return feedback.toString();
    }
}