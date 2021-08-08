package edu.example.blocktraceui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class homeActivity extends AppCompatActivity {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText contactName;
    private Button btn_createCancel, btn_createSave;

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

        Button editIcon = (Button) findViewById(R.id.btn_edit);
        editIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchToEditActivity();
            }
        });

        Button createContract = (Button) findViewById(R.id.btn_createContract);
        editIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                createContactDialog();
            }
        });

    }

    private void switchToContactsActivity() {
        Intent switchActivityIntent = new Intent(this, Contacts.class);
        startActivity(switchActivityIntent);
    }

    private void switchToEditActivity() {
        Intent switchActivityIntent = new Intent(this, EditProfile.class);
        startActivity(switchActivityIntent);
    }

    public void createContactDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View createContact = getLayoutInflater().inflate(R.layout.create_contact, null);

        btn_createCancel = (Button) createContact.findViewById(R.id.btn_createCancel);
        btn_createSave = (Button) createContact.findViewById(R.id.btn_createSave);

        dialogBuilder.setView(createContact);
        dialog = dialogBuilder.create();
        dialog.show();

        btn_createCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_createSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}