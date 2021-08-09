package edu.example.blocktraceui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import edu.example.blockTraceData.Person;
import edu.example.blockTraceData.RequestController;
import edu.example.blockTraceData.ResponseStatus;
import edu.example.blockTraceData.UserProfile;

public class Contacts extends AppCompatActivity {

    private List<Person> contactList;

    ContactListItemAdapter currentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        contactList = new ArrayList<Person>();

        // item adapter
        currentAdapter = new ContactListItemAdapter(this, 0, contactList);

        ListView listView = (ListView) findViewById(R.id.contactsListView);
        listView.setAdapter(currentAdapter);

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
                UserProfile.RefreshContacts(null);
            }
        });
        SetContactsToAdapter();
    }
    void SetContactsToAdapter()
    {
        currentAdapter.clear();
        int len = UserProfile.GetActivePofile().userContacts.length;
        for(int i=0; i<len; ++i)
        {
            contactList.add(UserProfile.GetActivePofile().userContacts[i]);
        }
    }

    private void switchToHomeActivity() {
        Intent switchActivityIntent = new Intent(this, homeActivity.class);
        startActivity(switchActivityIntent);
    }
}