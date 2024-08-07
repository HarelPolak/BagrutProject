package com.example.bagrutproject.stats;

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

import com.example.bagrutproject.dialogs.EditDialogClass;
import com.example.bagrutproject.core.MainActivity;
import com.example.bagrutproject.R;
import com.example.bagrutproject.interfaces.OnItemClickListener;
import com.example.bagrutproject.interfaces.OnItemLongClickListener;
import com.example.bagrutproject.utils.UtilDialogs;
import com.example.bagrutproject.utils.UtilStats;
import com.example.bagrutproject.utils.UtilText;

import java.util.List;

public class StatsFragment extends Fragment implements OnItemClickListener, OnItemLongClickListener,DialogInterface.OnDismissListener {

    SolveHelper sh;
    TextView tvCurrentSingle, tvBestSingle, tvCurrentAvg5, tvBestAvg5, tvCurrentAvg12, tvBestAvg12, tvTotalAvg;
    Solve bestSingle, currentSingle;
    List<Solve> solves;
    Dialog editDialog;
    SolveListAdapter adapter;
    long totalAvg, currentAvg5, bestAvg5, currentAvg12, bestAvg12;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        sh = new SolveHelper(getContext());

        tvCurrentSingle = view.findViewById(R.id.tvCurrentSingle);
        tvBestSingle = view.findViewById(R.id.tvBestSingle);
        tvCurrentAvg5 = view.findViewById(R.id.tvCurrentAvg5);
        tvBestAvg5 = view.findViewById(R.id.tvBestAvg5);
        tvCurrentAvg12 = view.findViewById(R.id.tvCurrentAvg12);
        tvBestAvg12 = view.findViewById(R.id.tvBestAvg12);
        tvTotalAvg = view.findViewById(R.id.tvTotalAvg);

        sh.open();
        solves = sh.getAllSolvesByType(MainActivity.cubeType);
        sh.close();
        currentSingle = UtilStats.getCurrentSolve(solves);
        bestSingle = UtilStats.getBestSolve(solves);
        currentAvg5 = UtilStats.getCurrentAvg5(solves);
        bestAvg5 = UtilStats.getBestAvg5(solves);
        currentAvg12 = UtilStats.getCurrentAvg12(solves);
        bestAvg12 = UtilStats.getBestAvg12(solves);
        totalAvg = UtilStats.getTotalAverage(solves);
        if(currentSingle != null)
            tvCurrentSingle.setText(currentSingle.getDisplayPenaltyText());
        if(bestSingle != null)
            tvBestSingle.setText(bestSingle.getDisplayPenaltyText());
        if(currentAvg5 != -1)
            tvCurrentAvg5.setText(UtilText.getDisplayText(currentAvg5));
        if(bestAvg5 != -1)
            tvBestAvg5.setText(UtilText.getDisplayText(bestAvg5));
        if(currentAvg12 != -1)
            tvCurrentAvg12.setText(UtilText.getDisplayText(currentAvg12));
        if(bestAvg12 != -1)
            tvBestAvg12.setText(UtilText.getDisplayText(bestAvg12));
        if(totalAvg != -1)
            tvTotalAvg.setText(UtilText.getDisplayText(totalAvg));

        RecyclerView solveRecyclerView = view.findViewById(R.id.solveRecyclerView);
        solveRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SolveListAdapter(solves, this, this);
        solveRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(int position) {
        editDialog = UtilDialogs.showEditDialog((Activity) getContext(), solves.get(position));
        editDialog.setOnDismissListener(this);
    }

    @Override
    public void onItemLongClicked(int position) {
        UtilDialogs.showConfirmationDialog(getActivity(), new Runnable() {
            @Override
            public void run() {
                sh.open();
                sh.deleteByRow(solves.get(position).getSolveId());
                sh.close();
                updateData();
            }
        }, "Are you sure you want to delete this solve?");
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        updateData();
    }

    public void updateData(){
        sh.open();
        solves = sh.getAllSolvesByType(MainActivity.cubeType);
        sh.close();
        currentSingle = UtilStats.getCurrentSolve(solves);
        bestSingle = UtilStats.getBestSolve(solves);
        currentAvg5 = UtilStats.getCurrentAvg5(solves);
        bestAvg5 = UtilStats.getBestAvg5(solves);
        currentAvg12 = UtilStats.getCurrentAvg12(solves);
        bestAvg12 = UtilStats.getBestAvg12(solves);
        totalAvg = UtilStats.getTotalAverage(solves);
        if(currentSingle != null)
            tvCurrentSingle.setText(currentSingle.getDisplayPenaltyText());
        else
            tvCurrentSingle.setText("00:00.00");
        if(bestSingle!=null)
            tvBestSingle.setText(bestSingle.getDisplayPenaltyText());
        else
            tvBestSingle.setText("00:00.00");
        if(currentAvg5 != -1)
            tvCurrentAvg5.setText(UtilText.getDisplayText(currentAvg5));
        else
            tvCurrentAvg5.setText("00:00.00");
        if(bestAvg5 != -1)
            tvBestAvg5.setText(UtilText.getDisplayText(bestAvg5));
        else
            tvBestAvg5.setText("00:00.00");
        if(currentAvg12 != -1)
            tvCurrentAvg12.setText(UtilText.getDisplayText(currentAvg12));
        else
            tvCurrentAvg12.setText("00:00.00");
        if(bestAvg12 != -1)
            tvBestAvg12.setText(UtilText.getDisplayText(bestAvg12));
        else
            tvBestAvg12.setText("00:00.00");
        if(totalAvg != -1)
            tvTotalAvg.setText(UtilText.getDisplayText(totalAvg));
        else
            tvTotalAvg.setText("00:00.00");
        adapter.updateData(solves);
    }
}