package com.example.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

public class AppManager {

    private static final String PREF_NAME = "preferences";
    private static final String TEXT_SIZE = "text_size_preference";
    private static final String BACK_GROUND = "switch";

    public static void updateTextSize(Context context, int checkedId, TextView textView) {
        float textSize;

        if (checkedId == R.id.paramRadioButtonSmall) {
            textSize = context.getResources().getDimension(R.dimen.text_size_small);
        } else if (checkedId == R.id.paramRadioButtonLarge) {
            textSize = context.getResources().getDimension(R.dimen.text_size_large);
        } else {
            textSize = context.getResources().getDimension(R.dimen.text_size_default);
        }

        textView.setTextSize(textSize);

        // Sauvegarder la taille du texte sélectionnée dans les préférences partagées
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TEXT_SIZE, checkedId);
        editor.apply();
    }

    public static int getTextSizePreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(TEXT_SIZE, R.id.paramRadioButtonMedium);
    }

    public static void updateBackgroundColor(Context context, boolean switchState) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(BACK_GROUND, switchState);
        editor.apply();

        if (switchState) {
            // Mode sombre activé
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            // Mode sombre désactivé
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public static boolean getBackgroundColor(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(BACK_GROUND, false);
    }
}