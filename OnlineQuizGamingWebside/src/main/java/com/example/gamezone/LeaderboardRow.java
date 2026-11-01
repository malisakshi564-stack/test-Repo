package com.example.gamezone;

public class LeaderboardRow {

    private String username;
    private int quizScore;
    private int ticTacToeScore;
    private int rpsScore;
    private int totalScore;
    private int memoryScore;

    public LeaderboardRow() {
    }

    public LeaderboardRow(String username, int quizScore,
                          int ticTacToeScore, int rpsScore) {

        this.username = username;
        this.quizScore = quizScore;
        this.ticTacToeScore = ticTacToeScore;
        this.rpsScore = rpsScore;

        this.totalScore =
                quizScore + ticTacToeScore + rpsScore;
    }

    public String getUsername() {
        return username;
    }

    public int getQuizScore() {
        return quizScore;
    }

    public int getTicTacToeScore() {
        return ticTacToeScore;
    }

    public int getRpsScore() {
        return rpsScore;
    }
    public int getMemoryScore() {
        return memoryScore;
    }
    public void setMemoryScore(int memoryScore) {
        this.memoryScore = memoryScore;
        calculateTotal();
    }

    public int getTotalScore() {
        return totalScore;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setQuizScore(int quizScore) {
        this.quizScore = quizScore;
        calculateTotal();
    }

    public void setTicTacToeScore(int ticTacToeScore) {
        this.ticTacToeScore = ticTacToeScore;
        calculateTotal();
    }

    public void setRpsScore(int rpsScore) {
        this.rpsScore = rpsScore;
        calculateTotal();
    }

    private void calculateTotal() {
    	this.totalScore =
    	        quizScore + ticTacToeScore + rpsScore + memoryScore;
    }
}