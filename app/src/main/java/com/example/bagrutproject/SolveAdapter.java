package com.example.bagrutproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SolveAdapter extends RecyclerView.Adapter<SolveViewHolder> {

    private List<Solve> solves;
    OnItemClickListener listener;

    public SolveAdapter(List<Solve> solves, OnItemClickListener listener) {
        this.solves = solves;
        this.listener = listener;
    }

    @Override
    public SolveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.solve_item_layout, parent, false);
        return new SolveViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SolveViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
        Solve solve = solves.get(position);
        holder.bindSolve(solve);
    }


    @Override
    public int getItemCount() {
        return solves.size();
    }
}
