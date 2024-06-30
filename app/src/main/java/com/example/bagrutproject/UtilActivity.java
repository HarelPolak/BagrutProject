package com.example.bagrutproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
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

    public static Solve getCurrentSolve(List<Solve> solves){
        if(solves.size()!=0){
            if(solves.get(0).getPenalty()!=2)
                return solves.get(0);
        }
        return null;
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

    public static Solve[] sortSolves(List<Solve> solves) {
        Solve[] solvesArray = solves.toArray(new Solve[0]);
        Arrays.sort(solvesArray, new Comparator<Solve>() {
            @Override
            public int compare(Solve solve1, Solve solve2) {
                return Long.compare(solve1.getPenaltyTime(), solve2.getPenaltyTime());
            }
        });
        return solvesArray;
    }

    public static long getAvg5(List<Solve> solves, int index){
        Solve s;
        Solve[] sortedArr;
        List<Solve> l = new ArrayList<>();
        int sum = 0;
        for(int i=index; i<index+5; i++){
            s = solves.get(i);
            if(s.getPenalty() != 2)
                l.add(s);
        }
        sortedArr = sortSolves(l);
        if(l.size()<4)
            return -1;
        for(int i=1; i<4; i++){
            sum += sortedArr[i].getPenaltyTime();
        }
        return sum/3;
    }

    public static long getCurrentAvg5(List<Solve> solves){
        if(solves.size() >= 5)
            return getAvg5(solves, 0);
        return -1;
    }

    public static long getBestAvg5(List<Solve> solves){
        long min = Integer.MAX_VALUE;
        long avg5;
        if(solves.size() >= 5){
            for(int i=0; i<solves.size()-4; i++){
                avg5 = getAvg5(solves, i);
                if(avg5 < min)
                    min = avg5;
            }
            return min;
        }
        return -1;
    }

    public static long getAvg12(List<Solve> solves, int index){
        Solve s;
        Solve[] sortedArr;
        List<Solve> l = new ArrayList<>();
        int sum = 0;
        for(int i=index; i<index+12; i++){
            s = solves.get(i);
            if(s.getPenalty() != 2)
                l.add(s);
        }
        sortedArr = sortSolves(l);
        if(l.size()<11)
            return -1;
        for(int i=1; i<11; i++){
            sum += sortedArr[i].getPenaltyTime();
        }
        return sum/10;
    }

    public static long getCurrentAvg12(List<Solve> solves){
        if(solves.size() >= 12)
            return getAvg12(solves, 0);
        return -1;
    }

    public static long getBestAvg12(List<Solve> solves){
        long min = Integer.MAX_VALUE;
        long avg12;
        if(solves.size() >= 12){
            for(int i=0; i<solves.size()-11; i++){
                avg12 = getAvg12(solves, i);
                if(avg12 < min)
                    min = avg12;
            }
            return min;
        }
        return -1;
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

    public static void showDeleteConfirmationDialog(Context context, final Runnable onDeleteConfirmed) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete this solve?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onDeleteConfirmed.run();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
