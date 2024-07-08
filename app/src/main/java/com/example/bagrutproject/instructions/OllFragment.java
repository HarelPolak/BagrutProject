package com.example.bagrutproject.instructions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bagrutproject.R;

public class OllFragment extends Fragment {
    ImageView iv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oll, container, false);

        iv = view.findViewById(R.id.iv);
        iv.setImageResource(R.drawable.oll_algorithms);

        return view;
    }
}