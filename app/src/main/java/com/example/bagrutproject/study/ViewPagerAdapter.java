package com.example.bagrutproject.study;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bagrutproject.study.AlgorithmsFragment;
import com.example.bagrutproject.study.TutorialsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new TutorialsFragment();
        }
        else if(position == 1){
            return new AlgorithmsFragment();
        }
        else {
            return new TutorialsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

