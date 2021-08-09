package edu.example.blocktraceui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import edu.example.blockTraceData.RequestController;
import edu.example.blockTraceData.ResponseStatus;
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
        Button saveButton = (Button) findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setcurrentInfo();
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
        gender.post(()->gender.setSelection(stringToSpinnerLocation(UserProfile.GetActivePofile().profileOwner.gender)));
        name.setText(UserProfile.GetActivePofile().profileOwner.firstName + " " + UserProfile.GetActivePofile().profileOwner.lastName);
        age.setText(String.valueOf(UserProfile.GetActivePofile().profileOwner.age));
        location.setText(UserProfile.GetActivePofile().profileOwner.location);
        phone.setText(UserProfile.GetActivePofile().profileOwner.phone);
    }
    void setcurrentInfo()
    {
        EditText name = (EditText) findViewById(R.id.txt_name);
        EditText age = (EditText) findViewById(R.id.editTextNumber);
        Spinner gender = (Spinner) findViewById(R.id.spn_gender);
        EditText location = (EditText) findViewById(R.id.editTextTextPostalAddress);
        EditText phone = (EditText) findViewById(R.id.editTextPhone);
        UserProfile.GetActivePofile().profileOwner.gender = gender.getSelectedItem().toString();
        String[] names=name.getText().toString().split(" ");
        String firstname=names[0];
        String lastname="";
        for(int i=1; i<names.length; i++)
        {
            if(i==1)
                lastname+=names[i];
            else lastname+= "-"+names[i];
        }
        UserProfile.GetActivePofile().profileOwner.firstName = firstname;
        UserProfile.GetActivePofile().profileOwner.lastName = lastname;
        UserProfile.GetActivePofile().profileOwner.age = Integer.parseInt(age.getText().toString());
        UserProfile.GetActivePofile().profileOwner.location= location.getText().toString();
        UserProfile.GetActivePofile().profileOwner.phone= phone.getText().toString();
        try {
            RequestController.UpdatePerson(UserProfile.GetActivePofile().profileOwner,this::onPersonUpdated);
        }
        catch (JSONException ex){}
    }
    void onPersonUpdated(ResponseStatus status)
    {
        if(status.isValid)
        {
            Toast.makeText(EditProfile.this,
                    "Updated Profile", Toast.LENGTH_SHORT).show();
        }
    }
    int stringToSpinnerLocation(String genderSelection)
    {
        switch (genderSelection) {
            case "Male":
                return 0;
            case "Female":
                    return 1;
            case "Trans":
                return 2;
            case "Non-Binary":
                return 3;
            case "Gender Non-Conforming":
                return 4;
            default:
                return 5;
        }
    }
}