package com.example.bagrutproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class TutorialsFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    String[] textArr = {"2x2", "3x3", "4x4"};
    int[] imageArr = {R.drawable.rubiks_cube, R.drawable.rubiks_cube, R.drawable.rubiks_cube};

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
        if(i == 0){
            Toast.makeText(this.getContext(), "2x2", Toast.LENGTH_LONG).show();
        }
        else if(i == 1){
            Toast.makeText(this.getContext(), "3x3", Toast.LENGTH_LONG).show();
        }
        if(i == 2){
            Toast.makeText(this.getContext(), "4x4", Toast.LENGTH_LONG).show();
        }
    }
}