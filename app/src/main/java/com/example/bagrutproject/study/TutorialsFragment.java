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
import com.example.bagrutproject.instructions.InstructionActivity;

public class TutorialsFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    Study[] studyArr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studyArr = new Study[]{
                new Study("Introduction To Cubing", "Tutorials", R.drawable.notations_icon,  "introduction"),
                new Study("2x2 Beginner Method", "Tutorials", R.drawable.two_by_two_icon, "two_by_two_beginner"),
                new Study("3x3 Beginner Method", "Tutorials", R.drawable.three_by_three_icon, "three_by_three_beginner"),
                new Study("3x3 CFOP Method", "Tutorials", R.drawable.three_by_three_icon, "three_by_three_cfop"),
                new Study("4x4 Beginner Method", "Tutorials", R.drawable.four_by_four_icon, "four_by_four_beginner")
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
        intent.putExtra("title", studyArr[i].getType());
        intent.putExtra("fragmentIdentifier", studyArr[i].getFragmentIdentifier());
        startActivity(intent);
    }
}