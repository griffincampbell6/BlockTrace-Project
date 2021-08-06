package edu.example.blocktraceui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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

    }

    private void switchToContactsActivity() {
        Intent switchActivityIntent = new Intent(this, Contacts.class);
        startActivity(switchActivityIntent);
    }
}