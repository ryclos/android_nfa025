package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    }
}