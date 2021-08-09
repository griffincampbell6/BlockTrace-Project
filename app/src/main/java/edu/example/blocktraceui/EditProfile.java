package edu.example.blocktraceui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.example.blockTraceData.UserProfile;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getCurrentInfo();

        Spinner spinner = (Spinner) findViewById(R.id.spn_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        Button closeIcon = (Button) findViewById(R.id.btn_close);
        closeIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchToHomeActivity();
            }
        });
    }

    private void switchToHomeActivity() {
        Intent switchActivityIntent = new Intent(this, homeActivity.class);
        startActivity(switchActivityIntent);
    }

    void getCurrentInfo(){
        EditText name = (EditText) findViewById(R.id.txt_name);
        EditText age = (EditText) findViewById(R.id.editTextNumber);
        Spinner gender = (Spinner) findViewById(R.id.spn_gender);
        EditText location = (EditText) findViewById(R.id.editTextTextPostalAddress);
        EditText phone = (EditText) findViewById(R.id.editTextPhone);

        name.setText(UserProfile.GetActivePofile().profileOwner.firstName + " " + UserProfile.GetActivePofile().profileOwner.lastName);
        age.setText(String.valueOf(UserProfile.GetActivePofile().profileOwner.age));
        location.setText(UserProfile.GetActivePofile().profileOwner.location);
        phone.setText(UserProfile.GetActivePofile().profileOwner.phone);
    }
}