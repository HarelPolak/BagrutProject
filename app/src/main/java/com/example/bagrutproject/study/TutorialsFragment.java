package com.example.bagrutproject.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bagrutproject.R;

public class TutorialsFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    Study[] studyArr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studyArr = new Study[]{
                new Study("How to Read Notations", "Tutorials", R.drawable.notations_icon, R.drawable.notations_tutorial),
                new Study("2x2 Beginner Method", "Tutorials", R.drawable.two_by_two_icon, R.drawable.two_by_two_beginner_tutorial),
                new Study("3x3 Beginner Method", "Tutorials", R.drawable.three_by_three_icon, R.drawable.three_by_three_beginner_tutorial),
                new Study("3x3 CFOP Method", "Tutorials", R.drawable.three_by_three_icon, R.drawable.three_by_three_cfop_tutorial),
                new Study("4x4 Beginner Method", "Tutorials", R.drawable.four_by_four_icon, R.drawable.four_by_four_beginner_tutorial)
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorials, container, false);
        listView = view.findViewById(R.id.listView);
        StudyListAdapter studyAdapter = new StudyListAdapter(getActivity(), studyArr);
        listView.setAdapter(studyAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), InstructionActivity.class);
        intent.putExtra("type", studyArr[i].getType());
        intent.putExtra("image", studyArr[i].getImage());
        startActivity(intent);
    }
}