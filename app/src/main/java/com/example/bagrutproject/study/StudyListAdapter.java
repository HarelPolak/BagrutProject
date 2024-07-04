package com.example.bagrutproject.study;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bagrutproject.R;

public class StudyListAdapter extends BaseAdapter {

    Context context;
    Study[] studyArr;
    LayoutInflater inflater;

    public StudyListAdapter(Context context, Study[] studyArr) {
        this.context = context;
        this.studyArr = studyArr;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return studyArr.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.study_item_layout, null);
        TextView tv = view.findViewById(R.id.item_text);
        ImageView iv = view.findViewById(R.id.item_image);
        tv.setText(studyArr[i].getName());
        iv.setImageResource(studyArr[i].getIcon());
        return view;
    }
}
