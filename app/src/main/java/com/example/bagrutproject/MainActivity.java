package com.example.bagrutproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    public static int cubeType;

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
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) item.getActionView();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list_item_array, R.layout.layout_drop_title);
        adapter.setDropDownViewResource(R.layout.layout_drop_list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        cubeType = spinner.getSelectedItemPosition();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        cubeType = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}