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


    // for testing
    public void fillContactList() {

        List<ContactListItem> list = new ArrayList<ContactListItem>();

        ContactListItem contact1 = new ContactListItem();
        contact1.name = "John Doe";
        contact1.email = "NULLs";
        contact1.phone = "NULL";
        contact1.location = "NULL";
        contact1.pathogen = "NULL";
        contact1.testResult = "NULL";
        contact1.lastUpdated = "NULL";
        list.add(contact1);

        ContactListItem contact2 = new ContactListItem();
        contact2.name = "NULL";
        contact2.email = "NULL";
        contact2.phone = "NULL";
        contact2.location = "NULL";
        contact2.pathogen = "NULL";
        contact2.testResult = "NULL";
        contact2.lastUpdated = "NULL";
        list.add(contact2);

        ContactListItem contact3 = new ContactListItem();
        contact3.name = "NULL";
        contact3.email = "NULL";
        contact3.phone = "NULL";
        contact3.location = "NULL";
        contact3.pathogen = "NULL";
        contact3.testResult = "NULL";
        contact3.lastUpdated = "NULL";
        list.add(contact3);

        // item adapter
        ContactListItemAdapter adapter;
        adapter = new ContactListItemAdapter(this, 0, list);

        ListView listView = (ListView) findViewById(R.id.contactsListView);
        listView.setAdapter(adapter);
    }

    private void switchToHomeActivity() {
        Intent switchActivityIntent = new Intent(this, homeActivity.class);
        startActivity(switchActivityIntent);
    }

}