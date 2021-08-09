package edu.example.blocktraceui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

import edu.example.blockTraceData.Person;
import edu.example.blockTraceData.RequestController;
import edu.example.blockTraceData.ResponseStatus;
import edu.example.blockTraceData.UserProfile;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Leaving this call here because the controller needs to get intialized when the app starts
        // so since this is the first acticity we can just call it here and never have to do it again for the app life cycle.
        RequestController.Initialize(this);

        setContentView(R.layout.launch_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}