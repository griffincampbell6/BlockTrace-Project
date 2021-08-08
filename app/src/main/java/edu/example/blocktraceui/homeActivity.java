package edu.example.blocktraceui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Button contactIcon = (Button) findViewById(R.id.btn_contacts);
        contactIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchToContactsActivity();
            }
        });

        Button editIcon = (Button) findViewById(R.id.btn_edit);
        editIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchToEditActivity();
            }
        });

        Button createContract = (Button) findViewById(R.id.btn_createContract);
        editIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchToCreateActivity();
            }
        });

    }

    private void switchToContactsActivity() {
        Intent switchActivityIntent = new Intent(this, Contacts.class);
        startActivity(switchActivityIntent);
    }

    private void switchToEditActivity() {
        Intent switchActivityIntent = new Intent(this, EditProfile.class);
        startActivity(switchActivityIntent);
    }

    private void switchToCreateActivity() {
        Intent switchActivityIntent = new Intent(this, EditProfile.class);
        startActivity(switchActivityIntent);
    }
}