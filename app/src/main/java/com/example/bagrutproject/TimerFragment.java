package com.example.bagrutproject;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TimerFragment extends Fragment implements View.OnTouchListener {

    TextView tvTimer;

    public TimerFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        tvTimer = getView().findViewById(R.id.tvTimer);
        tvTimer.setOnTouchListener(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(view == tvTimer){
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                tvTimer.setTextColor(Color.GREEN);
            }
            else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                tvTimer.setTextColor(Color.WHITE);
            }
        }
        return false;
    }
}