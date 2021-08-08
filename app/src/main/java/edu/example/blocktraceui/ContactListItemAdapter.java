package edu.example.blocktraceui;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

public class ContactListItemAdapter extends ArrayAdapter<ContactListItem> {

    public LayoutInflater mInflater;

    public ContactListItemAdapter(Context context, int rid, List<ContactListItem> list) {

        super(context, rid, list);
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    public View getView(int position, View convesrtView, ViewGroup parent) {

        ContactListItem item = (ContactListItem) getItem(position);
        View view = mInflater.inflate(R.layout.activity_contacts, null);

        // set name
        TextView name;
        name = (TextView) view.findViewById(R.id.contact_name);
        name.setText(item.name);

        // set location
        TextView location;
        location = (TextView) view.findViewById(R.id.contact_location);
        location.setText(item.location);

        // set phone
        TextView phone;
        phone = (TextView) view.findViewById(R.id.contact_phone);
        phone.setText(item.phone);

        // set email
        TextView email;
        email = (TextView) view.findViewById(R.id.contact_email);
        email.setText(item.email);

        // set pathogen
        TextView pathogen;
        pathogen = (TextView) view.findViewById(R.id.contact_pathogen);
        pathogen.setText(item.pathogen);

        // set testResult
        TextView testResult;
        testResult = (TextView) view.findViewById(R.id.contact_testResult);
        testResult.setText(item.testResult);

        // set lastUpdated
        TextView lastUpdated;
        lastUpdated = (TextView) view.findViewById(R.id.contact_lastUpdated);
        lastUpdated.setText(item.lastUpdated;

        return view;

    }

}
