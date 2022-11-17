package com.matchingscoring.model;

public class MyMatch {

    private String match;

    private String matchedWith;

    private double score;

    public MyMatch(String match, String matchedWith, double score) {
        this.match = match;
        this.matchedWith = matchedWith;
        this.score = score;
    }

    public MyMatch() {

    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getMatchedWith() {
        return matchedWith;
    }

    public void setMatchedWith(String matchedWith) {
        this.matchedWith = matchedWith;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
