package com.example.bagrutproject.instructions;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bagrutproject.R;
import com.example.bagrutproject.stats.Solve;

public class AlgorithmViewHolder extends RecyclerView.ViewHolder{

    public ImageView iv;
    public TextView tv;
    Algorithm algorithm;

    public AlgorithmViewHolder(View itemView){
        super(itemView);
        iv = itemView.findViewById(R.id.ivAlgorithm);
        tv = itemView.findViewById(R.id.tvAlgorithm);
    }

    public void bindAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;

        Glide.with(iv.getContext())
                .load(algorithm.getImage())
                .into(iv);


        tv.setText(algorithm.getNotations());
    }

}
