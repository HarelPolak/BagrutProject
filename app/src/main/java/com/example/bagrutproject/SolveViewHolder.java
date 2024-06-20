package com.example.bagrutproject;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class SolveViewHolder extends RecyclerView.ViewHolder {

    public TextView timeTextView;
    Solve solve;

    public SolveViewHolder(View itemView) {
        super(itemView);
        timeTextView = itemView.findViewById(R.id.time_text_view);
    }

    public void bindSolve(Solve solve) {
        this.solve = solve;
        timeTextView.setText(solve.getDisplayPenaltyText());
    }
}

