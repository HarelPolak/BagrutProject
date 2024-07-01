package com.example.bagrutproject.settings;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.example.bagrutproject.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.prefernces , rootKey);

        Preference theme = findPreference("theme");
        if(theme!=null){
            theme.setOnPreferenceChangeListener((preference, newValue) -> {
                Toast.makeText(this.getContext(), "Theme selected: " + newValue, Toast.LENGTH_LONG).show();
                return true;
            });
        }
    }


}
