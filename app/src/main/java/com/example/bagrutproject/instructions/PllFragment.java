package com.example.bagrutproject.instructions;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bagrutproject.R;
import com.example.bagrutproject.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class PllFragment extends Fragment implements OnItemClickListener {
    List<Algorithm> algorithms;
    int[] images;
    String[] notations;
    RecyclerView algorithmsRv;
    AlgorithmListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pll, container, false);

        images = new int[] {R.drawable.pll_1, R.drawable.pll_2, R.drawable.pll_3, R.drawable.pll_4, R.drawable.pll_5, R.drawable.pll_6, R.drawable.pll_7, R.drawable.pll_8, R.drawable.pll_9, R.drawable.pll_10, R.drawable.pll_11, R.drawable.pll_12, R.drawable.pll_13, R.drawable.pll_14, R.drawable.pll_15, R.drawable.pll_16, R.drawable.pll_17, R.drawable.pll_18, R.drawable.pll_19, R.drawable.pll_20, R.drawable.pll_21};
        notations = new String[] {"x L2 D2 L' U' L D2 L' U L'", "x' L2 D2 L U L' D2 L U' L", "R' U' F' R U R' U' R' F R2 U' R' U' R U R' U R", "R2 U R' U R' U' R U' R2 U' D R' U R D'", "R' U' R U D' R2 U R' U R U' R U' R2 D", "R2 U' R U' R U R' U R2 U D' R U' R' D", "R U R' U' D R2 U' R U' R' U R' U R2 D'", "x R2 F R F' R U2 r' U r U2", "R U R' F' R U R' U' R' F R2 U' R'", "R U' R' U' R U R D R' U' R D' R' U2 R'", "R2 F R U R U' R' F' R U2 R' U2 R", "R U R' U' R' F R2 U' R' U' R U R' F'", "x' L' U L D' L' U' L D L' U' L D' L' U L D", "R U R' U R U R' F' R U R' U' R' F R2 U' R' U2 R U' R'", "R' U R U' R' F' U' F R U R' F R' F' R U' R", "R' U R' U' y R' F' R2 U' R' U R' F R F", "F R U' R' U' R U R' F' R U R' U' R' F R F'", "M2 U M2 U2 M2 U M2", "M2 U M U2 M' U M2", "M2 U' M U2 M' U' M2", "M' U M2 U M2 U M' U2 M2"};
        algorithms = new ArrayList<Algorithm>();

        for(int i=0; i<images.length; i++){
            algorithms.add(new Algorithm("pll "+(i+1), notations[i], images[i]));
        }

        algorithmsRv = view.findViewById(R.id.algorithmsRv);
        algorithmsRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new AlgorithmListAdapter(algorithms, this);
        algorithmsRv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Algorithm algorithm = algorithms.get(position);
        Dialog d = new Dialog(getActivity());
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.algorithm_dialog_layout);

        ImageView iv = d.findViewById(R.id.ivAlgorithm);
        TextView tv = d.findViewById(R.id.tvAlgorithm);
        TextView title = d.findViewById(R.id.tvTitle);
        title.setText(algorithm.getName());
        Glide.with(iv.getContext())
                .load(algorithm.getImage())
                .into(iv);
        tv.setText(algorithm.getNotations());

        d.setCancelable(true);
        d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d.show();
    }
}