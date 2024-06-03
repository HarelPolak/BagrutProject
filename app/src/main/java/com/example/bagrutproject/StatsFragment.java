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

public class StatsFragment extends Fragment implements View.OnClickListener, DialogInterface.OnDismissListener {

    SolveHelper sh;
    View viewBestSingle;
    Dialog editDialog;
    TextView tvBestSingle, tvBestAvg5, tvBestAvg12, tvTotalAvg;
    Solve bestSingle;

    public StatsFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        viewBestSingle = view.findViewById(R.id.viewBestSingle);
        viewBestSingle.setOnClickListener(this);
        sh = new SolveHelper(getContext());
        sh.open();
        bestSingle = sh.getBestSolve(MainActivity.cubeType);
        sh.close();
        tvBestSingle = view.findViewById(R.id.tvBestSingle);
        if(bestSingle!=null){
            tvBestSingle.setText(UtilActivity.getDisplayPenaltyText(bestSingle.getTime(), bestSingle.getPenalty()));
        }
        tvBestAvg5 = view.findViewById(R.id.tvBestAvg5);
        tvBestAvg12 = view.findViewById(R.id.tvBestAvg12);
        tvTotalAvg = view.findViewById(R.id.tvTotalAvg);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == viewBestSingle){
            if(bestSingle != null){
                EditDialogClass editDialogClass = new EditDialogClass();
                editDialog = editDialogClass.showEditDialog((Activity) getContext(), bestSingle);
                editDialog.setOnDismissListener(this);
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }
}