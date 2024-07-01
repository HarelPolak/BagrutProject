package com.example.bagrutproject.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bagrutproject.R;

public class StudyActivity extends AppCompatActivity {

    TextView tv;
    int itemIndex, tabIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        tv = findViewById(R.id.tv);
        Intent intent = getIntent();
        itemIndex = intent.getIntExtra("itemIndex", 0);
        tabIndex = intent.getIntExtra("tabIndex", 0);
        ActionBar actionBar = getSupportActionBar();

        if(tabIndex == 0){
            actionBar.setTitle("Tutorials");
            if(itemIndex == 0){
                tv.setText("How to Read Notations");
            }
            else if(itemIndex == 1){
                tv.setText("2x2 Beginner Method");
            }
            else if(itemIndex == 2){
                tv.setText("3x3 Beginner Method");
            }
            else if(itemIndex == 3){
                tv.setText("3x3 CFOP Method");
            }
            else if(itemIndex == 4){
                tv.setText("4x4 Beginner Method");
            }
        }
        else if(tabIndex == 1){
            actionBar.setTitle("Algorithms");
            if(itemIndex == 0){
                tv.setText("3x3 CFOP OLL");
            }
            else if(itemIndex == 1){
                tv.setText("3x3 CFOP PLL");
            }
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
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