package edu.example.blocktraceui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button createAcctBttn = (Button) findViewById(R.id.btn_createacct);
        createAcctBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchToRegisterActivity();
            }
        });

        Button loginBttn = (Button) findViewById(R.id.btn_login);
        loginBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchToHomeActivity();
            }
        });
    }

    private void switchToRegisterActivity() {
        Intent switchActivityIntent = new Intent(this, RegisterActivity.class);
        startActivity(switchActivityIntent);
    }
    private void switchToHomeActivity() {
        Intent switchActivityIntent = new Intent(this, homeActivity.class);
        startActivity(switchActivityIntent);
    }
}