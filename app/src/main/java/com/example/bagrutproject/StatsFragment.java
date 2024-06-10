package com.example.bagrutproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StatsFragment extends Fragment {

    SolveHelper sh;
    RecyclerView recyclerView;
    ListAdapter adapter;
    private ArrayList<String> dataList;

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

        dataList = new ArrayList<>();
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 1");
        dataList.add("Item 2");
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ListAdapter(dataList);
        recyclerView.setAdapter(adapter);

        tvBestSingle = view.findViewById(R.id.tvBestSingle);
        tvBestAvg5 = view.findViewById(R.id.tvBestAvg5);
        tvBestAvg12 = view.findViewById(R.id.tvBestAvg12);
        tvTotalAvg = view.findViewById(R.id.tvTotalAvg);

        if(bestSingle!=null){
            tvBestSingle.setText(bestSingle.getDisplayPenaltyText());
        }
        if(totalAvg != -1)
            tvTotalAvg.setText(UtilActivity.getDisplayText(totalAvg));

        return view;
    }
}