package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Switch;

public class ParamActivity extends AppCompatActivity {

    TextView paramTvTitle, paramTvTxtSize, paramTvTxtDarkMode, paramTvTxtTimeWeek, paramTvTaileTxt;
    RadioGroup radioGroupTxtSize;
    RadioButton radioButtonTxtSizeDefault;
    RadioButton radioButtonTxtSizeLarge;
    RadioButton radioButtonTxtSizeSmall;
    Button btnForgetPass;
    Switch switchDarkMode;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param);

        switchDarkMode = findViewById(R.id.paramSwitchDark);
        btnForgetPass = findViewById(R.id.paramBtnPassForget);
        radioGroupTxtSize = findViewById(R.id.paramRadioGroup);
        radioButtonTxtSizeDefault = findViewById(R.id.paramRadioButtonMedium);
        radioButtonTxtSizeLarge = findViewById(R.id.paramRadioButtonLarge);
        radioButtonTxtSizeSmall = findViewById(R.id.paramRadioButtonSmall);

        sharedPreferences = getSharedPreferences("darkmode", Context.MODE_PRIVATE);
        int textSizePref = sharedPreferences.getInt("txtSize", R.id.paramRadioButtonMedium);


        switchDarkMode.setChecked(sharedPreferences.getBoolean("darkModeEnabled", false));
        radioGroupTxtSize.check(textSizePref);

        radioGroupTxtSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("txtSize", checkedId);
                editor.apply();
                AppManager.updateTextSize(ParamActivity.this, checkedId, paramTvTaileTxt);
            }
        });

        AppManager.updateTextSize(this, textSizePref, paramTvTaileTxt);

        switchDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
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