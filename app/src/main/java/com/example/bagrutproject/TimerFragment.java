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

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerFragment extends Fragment implements View.OnTouchListener, View.OnClickListener {

    View view_timer_fragment;
    EditDialogClass editDialogClass;
    TextView tvTimer, tvScramble;
    ImageButton ibEdit, ibDelete;
    Timer timer;
    TimerTask timerTask;
    Solve currentSolve;
    SolveHelper sh;
    String scramble;
    boolean timerShouldStart, timerIsRunning;
    long solveTime = 0;

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        public void run() {
            tvTimer.setTextColor(Color.GREEN);
            tvTimer.setText("00.00");
            solveTime = 0;
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

        sh = new SolveHelper(getContext());

        timer = new Timer();
        timerIsRunning = false;
        timerShouldStart = false;
        tvTimer.setText(UtilActivity.getDisplayText(solveTime));
        tvScramble = view.findViewById(R.id.tvScramble);
        scramble = ScrambleGenerator.generateScramble();
        tvScramble.setText(scramble);
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
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if(timerIsRunning){
                    stopTimer();
                }
                else{
                    tvTimer.setTextColor(Color.RED);
                    handler.postDelayed(runnable, 500);
                }
                return true;
            }
            else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
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
        currentSolve = new Solve(-1, MainActivity.cubeType, 0, 0, scramble, "", Calendar.getInstance().getTime().toString());
        timerTask = new TimerTask() {
            @Override
            public void run() {
                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(timerIsRunning){
                            solveTime += 10;
                            tvTimer.setText(UtilActivity.getDisplayText(solveTime));
                        }
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 10);
    }

    private void stopTimer(){
        timerTask.cancel();
        timerIsRunning = false;
        addSolve();
        scramble = ScrambleGenerator.generateScramble();
        tvScramble.setText(scramble);
    }

    public void refresh(){
        if(timerIsRunning)
            stopTimer();
        else{
            scramble = ScrambleGenerator.generateScramble();
            tvScramble.setText(scramble);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == ibEdit){
            if(currentSolve!=null && currentSolve.getSolveId()!=-1){
                editDialogClass = new EditDialogClass();
                editDialogClass.showEditDialog((Activity) getContext(), currentSolve);
            }
        }
        else if(view == ibDelete){
            if(currentSolve!=null && currentSolve.getSolveId()!=-1){
                deleteRecentSolve();
            }
        }
    }

    private void addSolve(){
        currentSolve.setTime(solveTime);
        sh.open();
        currentSolve = sh.createSolve(currentSolve);
        sh.close();
        Toast.makeText(this.getContext(), currentSolve.getSolveId()+"", Toast.LENGTH_LONG).show();

    }

    private void deleteRecentSolve(){
        sh.open();
        sh.deleteByRow(currentSolve.getSolveId());
        sh.close();
        currentSolve = null;
        tvTimer.setText("00.00");
        solveTime = 0;
        Toast.makeText(this.getContext(), "deleted", Toast.LENGTH_LONG).show();
    }
}