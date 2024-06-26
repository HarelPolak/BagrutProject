package com.example.bagrutproject.stats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bagrutproject.R;
import com.example.bagrutproject.interfaces.OnItemClickListener;
import com.example.bagrutproject.interfaces.OnItemLongClickListener;

import java.util.List;

public class SolveListAdapter extends RecyclerView.Adapter<SolveViewHolder> {

    private List<Solve> solves;
    OnItemClickListener listener;
    OnItemLongClickListener longClickListener;

    public SolveListAdapter(List<Solve> solves, OnItemClickListener listener, OnItemLongClickListener longClickListener) {
        this.solves = solves;
        this.listener = listener;
        this.longClickListener = longClickListener;

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
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null) {
                    longClickListener.onItemLongClicked(position);
                }
                return true;
            }
        });
        Solve solve = solves.get(position);
        holder.bindSolve(solve);
    }


    @Override
    public int getItemCount() {
        return solves.size();
    }

    public void updateData(List<Solve> newSolves) {
        this.solves.clear();
        this.solves.addAll(newSolves);
        notifyDataSetChanged();
    }
}
