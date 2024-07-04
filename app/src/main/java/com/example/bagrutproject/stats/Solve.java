package com.example.bagrutproject.stats;

import com.example.bagrutproject.utils.UtilText;

public class Solve {
    private long solveId;
    private int cubeType;
    private int penalty;
    private long solveTime;
    private String scramble;
    private String comment;
    private String date;

    public Solve(long solveId, int cubeType, int penalty, long solveTime, String scramble, String comment, String date) {
        this.solveId = solveId;
        this.cubeType = cubeType;
        this.penalty = penalty;
        this.solveTime = solveTime;
        this.scramble = scramble;
        this.comment = comment;
        this.date = date;
    }

    public Solve(int cubeType, int penalty, long solveTime, String scramble, String comment, String date) {
        this.solveId = solveId;
        this.cubeType = cubeType;
        this.penalty = penalty;
        this.solveTime = solveTime;
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

    public long getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(long solveTime) {
        this.solveTime = solveTime;
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
        if (penalty == 1)
            return solveTime + 2000;
        return solveTime;
    }

    public String getDisplayPenaltyText(){
        if(penalty == 0){
            return UtilText.getDisplayText(solveTime);
        }
        if(penalty == 1){
            return UtilText.getDisplayText(solveTime +2000)+"+";
        }
        if(penalty == 2){
            return "DNF";
        }
        return null;
    }
}
