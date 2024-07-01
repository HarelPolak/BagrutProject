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
    String textArr[];
    int[] imageArr;
    LayoutInflater inflater;

    public StudyListAdapter(Context context, String[] textArr, int[] imageArr) {
        this.context = context;
        this.textArr = textArr;
        this.imageArr = imageArr;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return textArr.length;
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
        tv.setText(textArr[i]);
        iv.setImageResource(imageArr[i]);
        return view;
    }
}
