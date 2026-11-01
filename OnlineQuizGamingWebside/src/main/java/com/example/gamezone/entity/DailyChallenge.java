package com.example.gamezone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DailyChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String challengeDate;
    private String challengeText;
    private String gameName;
    private boolean completed;

    public DailyChallenge() {
    }

    public DailyChallenge(String challengeDate,
                          String challengeText,
                          String gameName) {
        this.challengeDate = challengeDate;
        this.challengeText = challengeText;
        this.gameName = gameName;
        this.completed = false;
    }

    public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
        return id;
    }

    public String getChallengeDate() {
        return challengeDate;
    }

    public void setChallengeDate(String challengeDate) {
        this.challengeDate = challengeDate;
    }

    public String getChallengeText() {
        return challengeText;
    }

    public void setChallengeText(String challengeText) {
        this.challengeText = challengeText;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}