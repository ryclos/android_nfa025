package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {
    TextView description;
    TextView mail;
    TextView mdp;
    Button btnCo;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferencesDarkSwitch = getSharedPreferences("darkmode", Context.MODE_PRIVATE);
        boolean darkModeEnabled = sharedPreferencesDarkSwitch.getBoolean("darkModeEnabled", false);
        if (darkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        c = this;

        description = findViewById(R.id.tvDesc);
        mail = findViewById(R.id.editMAil);
        mdp = findViewById(R.id.editPass);
        btnCo = findViewById(R.id.btnConnect);


        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilisateur utilisateur = new Utilisateur(mail.getText().toString(), mdp.getText().toString());
                Intent intent = new Intent(c, DisplayActivity.class);
                intent.putExtra("utilisateur", utilisateur);
                startActivity(intent);
            }
        });

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE_ANNONCES, "Titre de l'annonce");
        values.put(DatabaseHelper.COLUMN_PRICE_ANNONCES, 100);
        values.put(DatabaseHelper.COLUMN_DESCRIPTION_ANNONCES, "Description de l'annonce");
        //values.put(DatabaseHelper.COLUMN_DATE_PUBLICATION, "2022-04-15");
        //values.put(DatabaseHelper.COLUMN_DATE_FIN_PUBLICATION, "2022-04-30");

        //Date date = new

        long newRowId = db.insert(DatabaseHelper.TABLE_ANNONCES, null, values);

        Log.d("INSERTDB", "onCreate: "+values);
        //SQLiteDatabase db = databaseHelper.getReadableDatabase();
    }
}