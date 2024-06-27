package com.example.bagrutproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class AlgorithmsFragment extends Fragment implements AdapterView.OnItemClickListener {


    ListView listView;
    String[] textArr = {"3x3 CFOP OLL", "3x3 CFOP PLL"};
    int[] imageArr = {R.drawable.three_by_three, R.drawable.three_by_three};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorials, container, false);
        listView = view.findViewById(R.id.listView);
        StudyAdapter studyAdapter = new StudyAdapter(getActivity(), textArr, imageArr);
        listView.setAdapter(studyAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), StudyActivity.class);
        intent.putExtra("itemIndex", i);
        intent.putExtra("tabIndex", 1);
        startActivity(intent);
    }
}