package com.example.bagrutproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class TutorialsFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    String[] textArr = {"How to Read Notations","2x2 Beginner Method", "3x3 Beginner Method", "3x3 CFOP Method", "4x4 Beginner Method"};
    int[] imageArr = {R.drawable.notations, R.drawable.two_by_two, R.drawable.three_by_three, R.drawable.three_by_three, R.drawable.four_by_four};

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
        intent.putExtra("tabIndex", 0);
        startActivity(intent);
    }
}