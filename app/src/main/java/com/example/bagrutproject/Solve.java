package com.example.bagrutproject;

import java.util.Date;

public class Solve {
    private long solveId;
    private int cubeType;
    private int penalty;
    private double time;
    private String scramble;
    private String comment;
    private String date;

    public Solve(long solveId, int cubeType, int penalty, double time, String scramble, String comment, String date) {
        this.solveId = solveId;
        this.cubeType = cubeType;
        this.penalty = penalty;
        this.time = time;
        this.scramble = scramble;
        this.comment = comment;
        this.date = date;
    }

    public Solve(int cubeType, int penalty, double time, String scramble, String comment, String date) {
        this.solveId = solveId;
        this.cubeType = cubeType;
        this.penalty = penalty;
        this.time = time;
        this.scramble = scramble;
        this.comment = comment;
        this.date = date;
    }

    public long getSolveId() {
        return solveId;
    }

    public void setSolveId(long solveId) {
        this.solveId = solveId;
    }

    public int getCubeType() {
        return cubeType;
    }

    public void setCubeType(int cubeType) {
        this.cubeType = cubeType;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
