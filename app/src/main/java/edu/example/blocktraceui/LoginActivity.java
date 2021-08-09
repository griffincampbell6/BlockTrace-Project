package edu.example.blocktraceui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

import edu.example.blockTraceData.Person;
import edu.example.blockTraceData.RequestController;
import edu.example.blockTraceData.ResponseStatus;
import edu.example.blockTraceData.UserProfile;

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
                TryLogin();
            }
        });
    }

    private void switchToRegisterActivity() {
        Intent switchActivityIntent = new Intent(this, RegisterActivity.class);
        startActivity(switchActivityIntent);
    }
    private void TryLogin()
    {
        // first element -> username;
        // second element -> password
        String[] loginData = new String[2];
        loginData[0]="m@b.com";
        loginData[1]="password";
        // api call;
        try {
            RequestController.Login(loginData,this::OnLoginResponse);
        }
        catch (JSONException ex){}

    }
    private void OnLoginResponse(ResponseStatus status, Person loggedInPerson)
    {
        if(status.isValid)
        {
            UserProfile.SetActivePerson(loggedInPerson);
            Intent switchActivityIntent = new Intent(this, homeActivity.class);
            startActivity(switchActivityIntent);
        }
        else // either password is wrong or user name does not exist the data base will tell you in the message
        {
            Log.v("API",status.errorMessage);
        }
    }
}