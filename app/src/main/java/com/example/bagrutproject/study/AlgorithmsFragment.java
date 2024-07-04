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

public class AlgorithmsFragment extends Fragment implements AdapterView.OnItemClickListener {


    ListView listView;
    Study[] studyArr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studyArr = new Study[]{
                new Study("3x3 CFOP OLL", "Algorithms", R.drawable.three_by_three_icon, R.drawable.oll_algorithms),
                new Study("3x3 CFOP PLL", "Algorithms", R.drawable.three_by_three_icon, R.drawable.pll_algorithms),
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