package com.example.bagrutproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class StatsFragment extends Fragment {

    SolveHelper sh;
    TextView tvBestSingle, tvBestAvg5, tvBestAvg12, tvTotalAvg;
    Solve bestSingle;
    long totalAvg;

    public StatsFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        sh = new SolveHelper(getContext());
        sh.open();
        bestSingle = sh.getBestSolve(MainActivity.cubeType);
        totalAvg = sh.getTotalAvg(MainActivity.cubeType);
        sh.close();

        tvBestSingle = view.findViewById(R.id.tvBestSingle);
        tvBestAvg5 = view.findViewById(R.id.tvBestAvg5);
        tvBestAvg12 = view.findViewById(R.id.tvBestAvg12);
        tvTotalAvg = view.findViewById(R.id.tvTotalAvg);

        if(bestSingle!=null){
            tvBestSingle.setText(UtilActivity.getDisplayPenaltyText(bestSingle.getTime(), bestSingle.getPenalty()));
        }
        if(totalAvg != -1)
            tvTotalAvg.setText(UtilActivity.getDisplayText(totalAvg));

        return view;
    }
}