package edu.example.blocktraceui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import edu.example.blockTraceData.*;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner spinner = (Spinner) findViewById(R.id.spn_genderReg);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        Button cancelBttn = (Button) findViewById(R.id.btn_goBackToLogin);
        cancelBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchToLoginActivity();
            }
        });

        Button registerBttn = (Button) findViewById(R.id.btn_register);
        registerBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TryCreateAccount();
            }
        });
    }

    private void switchToHomeActivity()
    {
        Intent switchActivityIntent = new Intent(this, homeActivity.class);
        startActivity(switchActivityIntent);
    }
    void TryCreateAccount()
    {
        // Get user inputted values from registration form
        EditText input_UserFirstName = (EditText) findViewById(R.id.editTextfName);
        EditText input_UserLastName = (EditText) findViewById(R.id.editTextlName);
        EditText input_UserName = (EditText) findViewById(R.id.editTextUsernameReg);
        EditText input_UserPassword = (EditText) findViewById(R.id.editTextPasswordReg);
        Spinner input_UserGender = (Spinner) findViewById(R.id.spn_genderReg);
        EditText input_UserPhone = (EditText) findViewById(R.id.editTextPhoneReg);
        EditText input_UserAge = (EditText) findViewById(R.id.editTextAgeReg);

        //Populate newPerson object to register with API
        Person newPerson = new Person();
        newPerson.firstName = input_UserFirstName.getText().toString();
        newPerson.lastName = input_UserLastName.getText().toString();
        newPerson.userName= input_UserName.getText().toString();
        newPerson.password = input_UserPassword.getText().toString();
        newPerson.gender = input_UserGender.getSelectedItem().toString();
        newPerson.phone = input_UserPhone.getText().toString();
        newPerson.age = Integer.parseInt(input_UserAge.getText().toString());
        try {
            RequestController.CreateNewAccount(newPerson,this::AccountCreationResponse);
        }
        catch (JSONException ex)
        {

        }
        //Person newPerson  =new Person("Michael","Berthaud",21,"M","11111111111","m@b.com","password");
    }
    void AccountCreationResponse(ResponseStatus status, Person newPerson)
    {
        if(status.isValid)
        {
            UserProfile.SetActivePerson(newPerson);
            switchToHomeActivity();
        }
        else // user name already exist
        {
            Log.v("API",status.errorMessage);
            Toast.makeText(RegisterActivity.this,
                    "Error - Username is already taken", Toast.LENGTH_SHORT).show();
        }
    }
    private void switchToLoginActivity() {
        Intent switchActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchActivityIntent);
    }
}