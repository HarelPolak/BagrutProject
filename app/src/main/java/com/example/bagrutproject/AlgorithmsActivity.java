package com.example.bagrutproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class AlgorithmsActivity extends AppCompatActivity {
    TextView tv;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithms);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Algorithms");
        actionBar.setDisplayHomeAsUpEnabled(true);

        tv = findViewById(R.id.tv);
        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        if(index == 0){
            tv.setText("3x3 CFOP OLL");
        }
        else if(index == 1){
            tv.setText("3x3 CFOP PLL");
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