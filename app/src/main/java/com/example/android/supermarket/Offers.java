package com.example.android.supermarket;

import android.content.Intent;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Offers extends PreferenceActivity {
    public CheckBoxPreference pref1;
    public CheckBoxPreference pref2;
    public  Boolean offAll;
    public  Boolean offAbove500;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.offers);
         pref1= (CheckBoxPreference) findPreference("mypreference_checkbox1");
         pref2= (CheckBoxPreference) findPreference("mypreference_checkbox2");
         offAbove500=pref2.isChecked();

        pref1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                offAll=pref1.isChecked();
                return offAll;
            }
        });
        pref2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                offAbove500=pref1.isChecked();return offAbove500;
            }
        });

    }
}
