package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TextView viewMail;
    Button settings;

    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        c = this;

        viewMail = findViewById(R.id.viewMail);
        settings = findViewById(R.id.settings);

        Intent intent = getIntent();
        Utilisateur utilisateur = (Utilisateur) intent.getParcelableExtra("utilisateur");
        assert utilisateur != null;
        setTitle(utilisateur.mail);
        viewMail.setText(utilisateur.mail);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, ParamActivity.class);
                startActivity(intent);
            }
        });

    }
}