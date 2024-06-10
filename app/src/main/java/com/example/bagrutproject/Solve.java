package com.example.bagrutproject;

import java.util.Date;

public class Solve {
    private long solveId;
    private int cubeType;
    private int penalty;
    private long time;
    private String scramble;
    private String comment;
    private String date;

    public Solve(long solveId, int cubeType, int penalty, long time, String scramble, String comment, String date) {
        this.solveId = solveId;
        this.cubeType = cubeType;
        this.penalty = penalty;
        this.time = time;
        this.scramble = scramble;
        this.comment = comment;
        this.date = date;
    }

    public Solve(int cubeType, int penalty, long time, String scramble, String comment, String date) {
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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

    public long getPenaltyTime(){
        if(penalty == 1)
            return time + 2000;
        return time;
    }

    public String getDisplayPenaltyText(){
        if(penalty == 0){
            return UtilActivity.getDisplayText(time);
        }
        if(penalty == 1){
            return UtilActivity.getDisplayText(time+2000)+"+";
        }
        if(penalty == 2){
            return "DNF";
        }
        return null;
    }

}
