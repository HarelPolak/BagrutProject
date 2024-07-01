package com.example.bagrutproject.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilText {
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
}
