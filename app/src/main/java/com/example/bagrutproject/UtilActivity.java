package com.example.bagrutproject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UtilActivity {

    public static String getDisplayText(long solveTime) {
        int milliseconds = (int) ((solveTime % 1000) / 10);
        int seconds = (int) (solveTime / 1000) % 60;
        int minutes = (int) solveTime / 60000;

        if(minutes==0)
            return String.format("%02d", seconds) + "." + String.format("%02d", milliseconds);
        return String.format("%02d", minutes) + ":" + String.format("%02d", seconds) + "." + String.format("%02d", milliseconds);
    }
    public static String getTodaysDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = cal.getTime();
        String formattedDate = formatter.format(date);
        return formattedDate;
    }

    public static Solve getBestSolve(List<Solve> solves){
        Solve best = null, s;
        long min = Integer.MAX_VALUE;
        for(int i=0; i<solves.size(); i++){
            s = solves.get(i);
            if(s.getPenalty() != 2){
                if(s.getPenaltyTime() < min){
                    min = s.getPenaltyTime();
                    best = s;
                }
            }
        }
        return best;
    }

    public static long getTotalAverage(List<Solve> solves){
        Solve s;
        int count = 0;
        long sum = 0;
        for(int i=0; i<solves.size(); i++){
            s = solves.get(i);
            if(s.getPenalty() != 2){
                count++;
                sum += s.getPenaltyTime();
            }
        }
        if(count == 0)
            return -1;
        return sum/count;
    }

//    public static long getBest5Avg(List<Solve> solves){
//        Solve s;
//
//    }
}
