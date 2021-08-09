package edu.example.blocktraceui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import edu.example.blockTraceData.*;
import java.util.List;


public class ContactListItemAdapter extends ArrayAdapter<Person> {

    public LayoutInflater mInflater;

    public ContactListItemAdapter(Context context, int rid, List<Person> list) {

        super(context, rid, list);
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Person item = (Person) getItem(position);
        View view = mInflater.inflate(R.layout.contact_list_item, null);
        // set name
        TextView name;
        name = (TextView) view.findViewById(R.id.contact_name);
        name.setText(item.firstName);

        // set location
        TextView location;
        location = (TextView) view.findViewById(R.id.contact_location);
        location.setText(null);

        // set phone
        TextView phone;
        phone = (TextView) view.findViewById(R.id.contact_phone);
        phone.setText(item.phone);

        // set email
        TextView email;
        email = (TextView) view.findViewById(R.id.contact_email);
        email.setText(item.userName);

        // set pathogen
        TextView pathogen;
        pathogen = (TextView) view.findViewById(R.id.contact_pathogen);
        pathogen.setText("COVID");

        // set testResult
        TextView testResult;
        testResult = (TextView) view.findViewById(R.id.contact_testResult);
        testResult.setText(boolToString(item.isHealthy));

        // set lastUpdated
        TextView lastUpdated;
        lastUpdated = (TextView) view.findViewById(R.id.contact_lastUpdated);
        lastUpdated.setText(null);

        Button deleteButton = (Button) view.findViewById(R.id.delete_contact);
        deleteButton.setOnClickListener(v-> OnDeletePressed(item.id));
        return view;
    }
    String boolToString(boolean isHealth)
    {
        if(isHealth)
            return "POSITIVE";
        else return "NEGATIVE";
    }
    void OnDeletePressed(int personId)
    {
        try
        {
            RequestController.RemoveContacts(UserProfile.GetActivePofile().profileOwner, new int[]{personId}, s->
            {
                    if(s.isValid)
                    {
                        UserProfile.RefreshContacts(this::OnContactsRefreshed);
                    }
            });
        }
        catch (JSONException ex){}
    }
    void OnContactsRefreshed(ResponseStatus status,Person[] contacts)
    {
        if(status.isValid)
        {
            this.clear();
            this.addAll(contacts);
            notifyDataSetChanged();
        }
    }
}

