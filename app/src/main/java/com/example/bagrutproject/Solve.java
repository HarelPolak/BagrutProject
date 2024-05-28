package com.example.bagrutproject;

import java.util.Date;

public class Solve {
    private long solveId;
    private int cubeType;
    private double time;
    private String scramble;
    private String comment;
    private String date;

    public Solve(long solveId, int cubeType, double time, String scramble, String comment, String date) {
        this.solveId = solveId;
        this.cubeType = cubeType;
        this.time = time;
        this.scramble = scramble;
        this.comment = comment;
        this.date = date;
    }

    public Solve(int cubeType, double time, String scramble, String comment, String date) {
        this.solveId = solveId;
        this.cubeType = cubeType;
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

    public void setDate(String date) {
        this.date = date;
    }

}
