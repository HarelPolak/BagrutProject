package com.example.bagrutproject.instructions;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bagrutproject.R;
import com.example.bagrutproject.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class OllFragment extends Fragment implements OnItemClickListener {
    List<Algorithm> algorithms;
    int[] images;
    String[] notations;
    RecyclerView algorithmsRv;
    AlgorithmListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oll, container, false);

        images = new int[] {R.drawable.oll_1, R.drawable.oll_2, R.drawable.oll_3, R.drawable.oll_4, R.drawable.oll_5, R.drawable.oll_6, R.drawable.oll_7, R.drawable.oll_8, R.drawable.oll_9, R.drawable.oll_10, R.drawable.oll_11, R.drawable.oll_12, R.drawable.oll_13, R.drawable.oll_14, R.drawable.oll_15, R.drawable.oll_16, R.drawable.oll_17, R.drawable.oll_18, R.drawable.oll_19, R.drawable.oll_20, R.drawable.oll_21, R.drawable.oll_22, R.drawable.oll_23, R.drawable.oll_24, R.drawable.oll_25, R.drawable.oll_26, R.drawable.oll_27, R.drawable.oll_28, R.drawable.oll_29, R.drawable.oll_30, R.drawable.oll_31, R.drawable.oll_32, R.drawable.oll_33, R.drawable.oll_34, R.drawable.oll_35, R.drawable.oll_36, R.drawable.oll_37, R.drawable.oll_38, R.drawable.oll_39, R.drawable.oll_40, R.drawable.oll_41, R.drawable.oll_42, R.drawable.oll_43, R.drawable.oll_44, R.drawable.oll_45, R.drawable.oll_46, R.drawable.oll_47, R.drawable.oll_48, R.drawable.oll_49, R.drawable.oll_50, R.drawable.oll_51, R.drawable.oll_52, R.drawable.oll_53, R.drawable.oll_54, R.drawable.oll_55, R.drawable.oll_56, R.drawable.oll_57};
        notations = new String[] {"R U2 R2 F R F' U2 R' F R F'", "r U r' U2 r U2 R' U2 R U' r'", "r' R2 U R' U r U2 r' U M'", "M U' r U2 r' U' R U' R' M'", "l' U2 L U L' U l", "r U2 R' U' R U' r'", "r U R' U R U2 r'", "l' U' L U' L' U2 l", "R U R' U' R' F R2 U R' U' F'", "R U R' U R' F R F' R U2 R'", "r U R' U R' F R F' R U2 r'", "M' R' U' R U' R' U2 R U' R r'", "F U R U' R2 F' R U R U' R'", "R' F R U R' F' R F U' F'", "l' U' l L' U' L U l' U l", "r U r' R U R' U' r U' r'", "F R' F' R2 r' U R U' R' U' M'", "r U R' U R U2 r2 U' R U' R' U2 r", "r' R U R U R' U' M' R' F R F'", "r U R' U' M2 U R U' R' U' M'", "R U2 R' U' R U R' U' R U' R'", "R U2 R2 U' R2 U' R2 U2 R", "R2 D' R U2 R' D R U2 R", "r U R' U' r' F R F'", "F' r U R' U' r' F R", "R U2 R' U' R U' R'", "R U R' U R U2 R'", "r U R' U' r' R U R U' R'", "R U R' U' R U' R' F' U' F R U R'", "F R' F R2 U' R' U' R U R' F2", "R' U' F U R U' R' F' R", "L U F' U' L' U L F L'", "R U R' U' R' F R F'", "R U R2 U' R' F R U R U' F'", "R U2 R2 F R F' R U2 R'", "L' U' L U' L' U L U L F' L' F", "F R' F' R U R U' R'", "R U R' U R U' R' U' R' F R F'", "L F' L' U' L U F U' L'", "R' F R U R' U' F' U R", "R U R' U R U2 R' F R U R' U' F'", "R' U' R U' R' U2 R F R U R' U' F'", "F' U' L' U L F", "F U R U' R' F'", "F R U R' U' F'", "R' U' R' F R F' U R", "R' U' R' F R F' R' F R F' U R", "F R U R' U' R U R' U' F'", "r U' r2 U r2 U r2 U' r", "r' U r2 U' r2 U' r2 U r'", "F U R U' R' U R U' R' F'", "R U R' U R U' B U' B' R'", "l' U2 L U L' U' L U L' U l", "r U2 R' U' R U R' U' R U' r'", "R' F R U R U' R2 F' R2 U' R' U R U R'", "r' U' r U' R' U R U' R' U R r' U r", "R U R' U' M' U R U' r'"};
        algorithms = new ArrayList<Algorithm>();

        for(int i=0; i<images.length; i++){
            algorithms.add(new Algorithm("oll "+(i+1), notations[i], images[i]));
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