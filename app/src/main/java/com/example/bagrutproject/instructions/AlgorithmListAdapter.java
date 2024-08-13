package com.example.bagrutproject.instructions;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bagrutproject.R;
import com.example.bagrutproject.interfaces.OnItemClickListener;
import com.example.bagrutproject.interfaces.OnItemLongClickListener;
import com.example.bagrutproject.stats.Solve;
import com.example.bagrutproject.stats.SolveViewHolder;

import java.util.List;

public class AlgorithmListAdapter extends RecyclerView.Adapter<AlgorithmViewHolder>{

    private List<Algorithm> algorithms;
    OnItemClickListener listener;

    public AlgorithmListAdapter(List<Algorithm> algorithms, OnItemClickListener listener) {
        this.algorithms = algorithms;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlgorithmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.algorithm_item_layout, parent, false);
        return new AlgorithmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlgorithmViewHolder holder, int position) {
        Algorithm algorithm = algorithms.get(position);
        holder.bindAlgorithm(algorithm);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return algorithms.size();
    }
}
