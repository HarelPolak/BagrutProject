package com.example.bagrutproject.instructions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bagrutproject.R;

public class InstructionActivity extends AppCompatActivity {

    Fragment fragment;
    String fragmentIdentifier;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);

        fragmentIdentifier = intent.getStringExtra("fragmentIdentifier");
        if("oll".equals(fragmentIdentifier))
            fragment = new OllFragment();
        else if("pll".equals(fragmentIdentifier))
            fragment = new PllFragment();
        else if("introduction".equals(fragmentIdentifier))
            fragment = new IntroductionFragment();
        else if("two_by_two_beginner".equals(fragmentIdentifier))
            fragment = new TwoByTwoBeginnerFragment();
        else if("three_by_three_beginner".equals(fragmentIdentifier))
            fragment = new ThreeByThreeBeginnerFragment();
        else if("three_by_three_cfop".equals(fragmentIdentifier))
            fragment = new ThreeByThreeCfopFragment();
        else if("four_by_four_beginner".equals(fragmentIdentifier))
            fragment = new FourByFourBeginnerFragment();
        else
            fragment = null;

        if(fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}