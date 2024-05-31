package com.example.bagrutproject;

import java.util.Calendar;
import java.util.Date;

public class UtilActivity {

    public static String getTimerText(double solveTime) {
        double seconds = solveTime % 60;
        int minutes = (int) solveTime / 60;

        if(minutes==0)
            return String.format("%05.2f", seconds);
        return String.format("%02d", minutes) + ":" + String.format("%05.2f", seconds);
    }
}
