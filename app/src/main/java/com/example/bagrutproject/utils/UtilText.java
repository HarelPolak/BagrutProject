package com.example.bagrutproject.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilText {
    public static String getDisplayText(long solveTime) {
        int milliseconds = ((int) (solveTime % 1000) / 10);
        int seconds = (int) (solveTime / 1000) % 60;
        int minutes = (int) (solveTime / (1000 * 60)) % 60;
        int hours = (int) (solveTime / (1000 * 60 * 60));

        if (hours > 0) {
            return String.format("%02d:%02d:%02d.%02d", hours, minutes, seconds, milliseconds);
        } else if (minutes > 0) {
            return String.format("%02d:%02d.%02d", minutes, seconds, milliseconds);
        } else {
            return String.format("%02d.%02d", seconds, milliseconds);
        }
    }


    public static String getDisplayEditText(long solveTime) {
        int milliseconds = ((int) (solveTime % 1000) / 10);
        int seconds = (int) (solveTime % 100000) / 1000;
        int minutes = (int) (solveTime % 10000000) / 100000;
        int hours = (int) (solveTime % 1000000000) / 10000000;

        if (hours > 0) {
            return String.format("%02d:%02d:%02d.%02d", hours, minutes, seconds, milliseconds);
        } else if (minutes > 0) {
            return String.format("%02d:%02d.%02d", minutes, seconds, milliseconds);
        } else {
            return String.format("%02d.%02d", seconds, milliseconds);
        }
    }

    public static long getTimeLong(String formattedTime) {
        String[] parts = formattedTime.split(":|\\.");
        int hours = 0, minutes = 0, seconds = 0, milliseconds = 0;

        if (parts.length == 2) {
            seconds = Integer.parseInt(parts[0]);
            milliseconds = Integer.parseInt(parts[1]) * 10;
        }
        else if (parts.length == 3) {
            minutes = Integer.parseInt(parts[0]);
            seconds = Integer.parseInt(parts[1]);
            milliseconds = Integer.parseInt(parts[2]) * 10;
        }
        else if (parts.length == 4) {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
            seconds = Integer.parseInt(parts[2]);
            milliseconds = Integer.parseInt(parts[3]) * 10;
        }
        else {
            return -1;
        }

        long totalMilliseconds = milliseconds + seconds * 1000L + minutes * 60L * 1000L + hours * 60L * 60L * 1000L;
        return totalMilliseconds;
    }

    public static String getTodaysDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = cal.getTime();
        String formattedDate = formatter.format(date);
        return formattedDate;
    }
}
