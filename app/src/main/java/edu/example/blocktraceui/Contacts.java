package edu.example.blocktraceui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        fillContactList();
    }

    public void fillContactList() {

        List<ContactListItem> list = new ArrayList<ContactListItem>();

        ContactListItem contact1 = new ContactListItem();
        contact1.name = "NULL";
        contact1.email = "NULL";
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

}