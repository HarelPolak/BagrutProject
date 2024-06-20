package com.example.bagrutproject;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StatsFragment extends Fragment implements OnItemClickListener{

    SolveHelper sh;
    TextView tvBestSingle, tvBestAvg5, tvBestAvg12, tvTotalAvg;
    Solve bestSingle;
    List<Solve> solves;
    Dialog editDialog;
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
        solves = sh.getAllSolvesByType(MainActivity.cubeType);
        sh.close();

        RecyclerView solveRecyclerView = view.findViewById(R.id.solveRecyclerView);
        solveRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SolveAdapter adapter = new SolveAdapter(solves, this);
        solveRecyclerView.setAdapter(adapter);

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

    @Override
    public void onItemClick(int position) {
        EditDialogClass editDialogClass = new EditDialogClass();
        editDialog = editDialogClass.showEditDialog((Activity) getContext(), solves.get(position));
    }
}