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

        // these are not where the methods should go but im putting them here as example usage of the simple API
        // so we might wanna create an account. Normally we would get all his datra fromt extviews but ill do it manually
        Person newPerson  =new Person("Michael","Berthaud",21,"M","11111111111","m@b.com","password");
        // annoyingly enough java makes all API calls have to get wrapped in Try catch blocks
        //itll auto generate it for you though.

        try {
            // feel free to swap this out for other start up methods like CreateAccount to get a feel
            RequestController.Login(new String[] {"m@b.com","password"},this::OnLogin);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    void OnAccountCreated(ResponseStatus status, Person newPerson)
    {
        // when yall run this method it will always be false because I've already ran it so the server will say "user already created with username blah blah blah"
        if (status.isValid) {
            Log.v("API", newPerson.firstName);
        }
        // server return error (which you can see in the console as well) if userName already exist
        else Log.v("API", status.errorMessage);
    }
    void OnLogin(ResponseStatus status, Person newPerson) {
        if (status.isValid)
        {
            UserProfile.SetActiveProfile(newPerson);
            Log.v("API","Valid");
            // ask server to get all Contacts
            try {
                RequestController.GetAllContacts(UserProfile.GetActiveLocalProfile().profileOwner.id,this::OnContactsRecieved);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        else Log.v("API", status.errorMessage);
    }
    void OnContactsRecieved(ResponseStatus status, Person[] allContacts)
    {
        if(status.isValid)
        {
            // update the active prole
            UserProfile.GetActiveLocalProfile().userContacts = allContacts;
            // just log to console just to see;
            for (int i =0; i<allContacts.length; ++i)
            {
             Log.v("API",allContacts[i].userName);
            }
        }
    }

}