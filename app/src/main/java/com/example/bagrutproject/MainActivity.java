package com.example.bagrutproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.timer);

    }
    StudyFragment studyFragment = new StudyFragment();
    TimerFragment timerFragment = new TimerFragment();
    StatsFragment statsFragment = new StatsFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.study){
            return replaceFragment(studyFragment);
        }
        else if(item.getItemId() == R.id.timer){
            return replaceFragment(timerFragment);
        }
        else if(item.getItemId() == R.id.stats){
            return replaceFragment(statsFragment);
        }
        return false;
    }

    public boolean replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, fragment)
                .commit();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.share){
            Toast.makeText(this, "Share", Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId() == R.id.about){
            Toast.makeText(this, "About", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}