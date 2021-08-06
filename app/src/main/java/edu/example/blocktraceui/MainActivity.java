package edu.example.blocktraceui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, homeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}