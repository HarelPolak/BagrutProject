package com.example.bagrutproject.timer;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bagrutproject.core.MainActivity;
import com.example.bagrutproject.R;
import com.example.bagrutproject.stats.Solve;
import com.example.bagrutproject.stats.SolveHelper;
import com.example.bagrutproject.utils.UtilDialogs;
import com.example.bagrutproject.utils.UtilScrambles;
import com.example.bagrutproject.utils.UtilText;

import java.util.Timer;
import java.util.TimerTask;

public class TimerFragment extends Fragment implements View.OnTouchListener, View.OnClickListener, DialogInterface.OnDismissListener {

    View view_timer_fragment;
    Dialog editDialog, addDialog;
    Animation celebrateAnimation, spinAnimation, tapAnimation;
    TextView tvTimer, tvScramble;
    ImageButton ibEdit, ibDelete, ibReroll, ibAdd;
    Timer timer;
    TimerTask timerTask;
    Solve currentSolve;
    SolveHelper sh;
    String scramble;
    boolean timerShouldStart, timerIsRunning, holdToStart, changeColor, celebrate;
    long solveTime = 0;

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        public void run() {
            if(changeColor)
                tvTimer.setTextColor(ContextCompat.getColor(getActivity(), R.color.start_color));
            tvTimer.setText("00.00");
            solveTime = 0;
            timerShouldStart = true;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        view_timer_fragment = view.findViewById(R.id.view_timer_fragment);
        tvTimer = view.findViewById(R.id.tvTimer);
        view_timer_fragment.setOnTouchListener(this);

        ibEdit = view.findViewById(R.id.ibEdit);
        ibDelete = view.findViewById(R.id.ibDelete);
        ibReroll = view.findViewById(R.id.ibReroll);
        ibAdd = view.findViewById(R.id.ibAdd);
        ibEdit.setOnClickListener(this);
        ibDelete.setOnClickListener(this);
        ibReroll.setOnClickListener(this);
        ibAdd.setOnClickListener(this);

        sh = new SolveHelper(getContext());

        celebrateAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.celecrate);
        spinAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.spin);
        tapAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.tap);
        timer = new Timer();
        timerIsRunning = false;
        timerShouldStart = false;
        tvTimer.setText(UtilText.getDisplayText(solveTime));
        tvScramble = view.findViewById(R.id.tvScramble);
        scramble = UtilScrambles.generateScramble();
        tvScramble.setText(scramble);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        holdToStart = preferences.getBoolean("hold_to_start", true);
        changeColor = preferences.getBoolean("change_color", true);
        celebrate = preferences.getBoolean("celebrate", false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timerIsRunning){
            stopTimer();
        }
    }

    @Override
    public void onResume() {
        updateData();
        super.onResume();
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
                    if(solveTime >= 10)
                        stopTimer();
                }
                else{
                    if(holdToStart){
                        if(changeColor)
                            tvTimer.setTextColor(ContextCompat.getColor(getActivity(), R.color.wait_color));
                        handler.postDelayed(runnable, 300);
                    }
                    else{
                        solveTime = 0;
                        startTimer();
                        timerIsRunning = true;
                    }
                }
                return true;
            }
            else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                tvTimer.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray_100));
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
        currentSolve = new Solve(-1, MainActivity.cubeType, 0, 0, scramble, "", UtilText.getTodaysDate());
        timerTask = new TimerTask() {
            @Override
            public void run() {
                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(timerIsRunning){
                            solveTime += 10;
                            tvTimer.setText(UtilText.getDisplayText(solveTime));
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
        if(celebrate){
            sh.open();
            if(sh.isBestTime(MainActivity.cubeType, solveTime)){
                tvTimer.startAnimation(celebrateAnimation);
                Toast.makeText(this.getContext(), "new PB", Toast.LENGTH_LONG).show();
            }
            sh.close();
        }
        addSolve();
        scramble = UtilScrambles.generateScramble();
        tvScramble.setText(scramble);
    }

    public void refresh(){
        if(timerIsRunning){
            timerTask.cancel();
            timerIsRunning = false;
        }
        scramble = UtilScrambles.generateScramble();
        tvScramble.setText(scramble);
        tvTimer.setText("00.00");
        solveTime = 0;
        currentSolve = null;
    }

    public void updateData(){
        if(currentSolve != null){
            sh.open();
            if(sh.getSolveById(currentSolve.getSolveId()) == null){
                currentSolve = null;
                tvTimer.setText("00.00");
                solveTime = 0;
            }
            else{
                currentSolve = sh.getSolveById(currentSolve.getSolveId());
                tvTimer.setText(currentSolve.getDisplayPenaltyText());
            }
            sh.close();
        }
    }

    @Override
    public void onClick(View view) {
        if(view == ibEdit){
            if(currentSolve!=null && currentSolve.getSolveId()!=-1){
                ibEdit.startAnimation(tapAnimation);
                editDialog = UtilDialogs.showEditDialog(getActivity(), currentSolve);
                editDialog.setOnDismissListener(this);
            }
        }
        else if(view == ibDelete){
            if(currentSolve!=null && currentSolve.getSolveId()!=-1){
                ibDelete.startAnimation(tapAnimation);
                deleteRecentSolve();
            }
        }
        else if(view == ibReroll){
            if(!timerIsRunning && !timerShouldStart){
                ibReroll.startAnimation(spinAnimation);
                scramble = UtilScrambles.generateScramble();
                tvScramble.setText(scramble);
            }
        }
        else if (view == ibAdd){
            if(!timerIsRunning && !timerShouldStart){
                ibAdd.startAnimation(tapAnimation);
                scramble = UtilScrambles.generateScramble();
                tvScramble.setText(scramble);
                addDialog = UtilDialogs.showAddDialog(getActivity(), MainActivity.cubeType, scramble, UtilText.getTodaysDate());
            }
        }
    }

    private void addSolve(){
        currentSolve.setSolveTime(solveTime);
        sh.open();
        currentSolve = sh.createSolve(currentSolve);
        sh.close();

    }

    private void deleteRecentSolve(){
        UtilDialogs.showConfirmationDialog(getActivity(), new Runnable() {
            @Override
            public void run() {
                sh.open();
                sh.deleteByRow(currentSolve.getSolveId());
                sh.close();
                currentSolve = null;
                tvTimer.setText("00.00");
                solveTime = 0;
            }
        }, "Are you sure you want to delete this solve?");
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        updateData();
    }
}