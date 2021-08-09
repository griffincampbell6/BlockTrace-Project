package edu.example.blocktraceui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import edu.example.blockTraceData.UserProfile;

public class Contacts extends AppCompatActivity {

    private List<ContactListItem> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        contactList = new ArrayList<ContactListItem>();

        // item adapter
        ContactListItemAdapter adapter;
        adapter = new ContactListItemAdapter(this, 0, contactList);

        ListView listView = (ListView) findViewById(R.id.contactsListView);
        listView.setAdapter(adapter);

        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToHomeActivity();
            }
        });

        Button refreshButton = (Button) findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
            }
        });

        addListItem("John Doe", "Boston, MA", "617-939-7468", "johndoe@gmail.com", "COVID", "POSITIVE", "8/6/2021");
        addListItem("John Doe", "Boston, MA", "617-939-7468", "johndoe@gmail.com", "COVID", "POSITIVE", "8/6/2021");

    }

    public void addListItem(String name,
                            String location,
                            String phone,
                            String email,
                            String pathogen,
                            String testResult,
                            String lastUpdated) {

        ContactListItem contact = new ContactListItem();
        contact.name = name;
        contact.email = email;
        contact.phone = phone;
        contact.location = location;
        contact.pathogen = pathogen;
        contact.testResult = testResult;
        contact.lastUpdated = lastUpdated;

        contactList.add(contact);

    }

    private void switchToHomeActivity() {
        Intent switchActivityIntent = new Intent(this, homeActivity.class);
        startActivity(switchActivityIntent);
    }

}