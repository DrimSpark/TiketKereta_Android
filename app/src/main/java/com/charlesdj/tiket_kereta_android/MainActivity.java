package com.charlesdj.tiket_kereta_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

import session.SessionManager;

public class MainActivity extends AppCompatActivity {

    private SessionManager session;
    private CircularRevealCardView keluar,input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        input = (CircularRevealCardView) findViewById(R.id.Card1);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, InputTiketPemesananActivity.class);
                startActivity(a);
                finish();
            }
        });

        session = new SessionManager(this);
        keluar = (CircularRevealCardView) findViewById(R.id.card3);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSkip(false);
                session.setSessid(0);

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
