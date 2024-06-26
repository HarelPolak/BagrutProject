package com.example.bagrutproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class TutorialsActivity extends AppCompatActivity {

    TextView tv;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tutorials");
        actionBar.setDisplayHomeAsUpEnabled(true);

        tv = findViewById(R.id.tv);
        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        if(index == 0){
            tv.setText("How to Read Notations");
        }
        else if(index == 1){
            tv.setText("2x2 Beginner Method");
        }
        else if(index == 2){
            tv.setText("3x3 Beginner Method");
        }
        else if(index == 3){
            tv.setText("3x3 CFOP Method");
        }
        else if(index == 4){
            tv.setText("4x4 Beginner Method");
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