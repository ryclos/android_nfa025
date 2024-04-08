package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Switch;

public class ParamActivity extends AppCompatActivity {

    TextView paramTvTitle, paramTvTxtSize, paramTvTxtDarkMode, paramTvTxtTimeWeek;
    Button btnForgetPass;
    Switch switchDarkMode;

    private SharedPreferences sharedPreferencesTextSize;
    private SharedPreferences sharedPreferencesDarkSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param);

        switchDarkMode = findViewById(R.id.paramSwitchDark);
        btnForgetPass = findViewById(R.id.paramBtnPassForget);

        sharedPreferencesDarkSwitch = getSharedPreferences("darkmode", Context.MODE_PRIVATE);

        switchDarkMode.setChecked(sharedPreferencesDarkSwitch.getBoolean("darkModeEnabled", false));

        switchDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               SharedPreferences.Editor editor = sharedPreferencesDarkSwitch.edit();
               boolean isChecked = switchDarkMode.isChecked();
               editor.putBoolean("darkModeEnabled", isChecked);
               editor.apply();

               if (isChecked) {
                   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
               } else {
                   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
               }

            }
        });

        Log.d(switchDarkMode.toString() , "Le bouton switch a été cliqué");
    }
}