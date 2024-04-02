package com.example.bagrutproject;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TimerFragment extends Fragment implements View.OnTouchListener {

    TextView tvTimer;
    View view_timer_fragment;

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        public void run() {
            tvTimer.setTextColor(Color.GREEN);
        }
    };
    public TimerFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        view_timer_fragment = view.findViewById(R.id.view_timer_fragment);
        tvTimer = view.findViewById(R.id.tvTimer);
        view_timer_fragment.setOnTouchListener(this);
        return view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(view == view_timer_fragment){
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                tvTimer.setTextColor(Color.RED);
                handler.postDelayed(runnable, 500);
                return true;
            }
            else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                tvTimer.setTextColor(Color.WHITE);
                handler.removeCallbacks(runnable);
            }
        }
        return false;
    }

}