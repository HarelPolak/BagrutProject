package com.example.bagrutproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class TimerFragment extends Fragment implements View.OnTouchListener, View.OnClickListener {

    TextView tvTimer;
    TextView tvScramble;
    View view_timer_fragment;
    Timer timer;
    TimerTask timerTask;
    ImageButton ibEdit, ibDelete;
    boolean timerShouldStart;
    boolean timerIsRunning;
    double time = 0.0;

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        public void run() {
            tvTimer.setTextColor(Color.GREEN);
            tvTimer.setText("00.00");
            time = 0.0;
            timerShouldStart = true;
        }
    };

    public TimerFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        view_timer_fragment = view.findViewById(R.id.view_timer_fragment);
        tvTimer = view.findViewById(R.id.tvTimer);
        view_timer_fragment.setOnTouchListener(this);

        ibEdit = view.findViewById(R.id.ibEdit);
        ibDelete = view.findViewById(R.id.ibDelete);
        ibEdit.setOnClickListener(this);
        ibDelete.setOnClickListener(this);

        timer = new Timer();
        timerIsRunning = false;
        timerShouldStart = false;
        tvTimer.setText(getTimerText());
        tvScramble = view.findViewById(R.id.tvScramble);
        tvScramble.setText(ScrambleGenerator.generateScramble());
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timerIsRunning){
            stopTimer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(timerIsRunning){
            stopTimer();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view == view_timer_fragment) {
            if(timerIsRunning){
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    stopTimer();
                    return true;
                }
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                tvTimer.setTextColor(Color.RED);
                handler.postDelayed(runnable, 500);
                return true;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                tvTimer.setTextColor(Color.WHITE);
                handler.removeCallbacks(runnable);
                if (timerShouldStart) {
                    startTimer();
                    timerShouldStart = false;
                    timerIsRunning = true;
                }
            }
        }
        return false;
    }

    private void startTimer() {
        timerTask = new TimerTask() {

            @Override
            public void run() {
                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time += 0.01;
                        tvTimer.setText(getTimerText());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 10);
    }

    private void stopTimer(){
        timerTask.cancel();
        timerIsRunning = false;
        tvScramble.setText(ScrambleGenerator.generateScramble());
    }

    private String getTimerText() {
        double seconds = time % 60;
        int minutes = (int)time / 60;

        if(minutes==0)
            return String.format("%05.2f", seconds);
        return String.format("%02d", minutes) + ":" + String.format("%05.2f", seconds);
    }

    public void refresh(){
        if(timerIsRunning)
            stopTimer();
        else
            tvScramble.setText(ScrambleGenerator.generateScramble());
    }

    @Override
    public void onClick(View view) {
        if(view == ibEdit){
            Toast.makeText(this.getContext(), "Edit", Toast.LENGTH_LONG).show();
        }
        else if(view == ibDelete){
            Toast.makeText(this.getContext(), "Delete", Toast.LENGTH_LONG).show();
        }
    }
}