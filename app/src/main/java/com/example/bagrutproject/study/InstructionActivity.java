package com.example.bagrutproject.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.bagrutproject.R;

public class InstructionActivity extends AppCompatActivity {

    ImageView iv;
    int image;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        iv = findViewById(R.id.iv);
        Intent intent = getIntent();
        ActionBar actionBar = getSupportActionBar();
        image = intent.getIntExtra("image", 0);
        iv.setImageResource(image);
        type = intent.getStringExtra("type");
        actionBar.setTitle(type);
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