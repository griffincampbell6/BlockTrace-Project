package edu.example.blocktraceui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

public class homeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private AlertDialog.Builder dialogBuilderCreate, dialogBuilderReport;
    private AlertDialog dialog;
    private EditText contactName;
    private Button btn_createCancel, btn_createSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Button settingIcon = (Button) findViewById(R.id.btn_settings);
        settingIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                settingsPopup(view);
            }
        });

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

        Button createContact = (Button) findViewById(R.id.btn_createContact);
        createContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                createContactDialog();
            }
        });

        Button btnReport = (Button) findViewById(R.id.btnReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilderReport = new AlertDialog.Builder(homeActivity.this);
                View reportView = getLayoutInflater().inflate(R.layout.report_spinner, null);
                dialogBuilderReport.setCancelable(true);
                dialogBuilderReport.setTitle("Report Status");
                dialogBuilderReport.setMessage("Please select your latest test result status.");
                Spinner reportSpin = (Spinner) reportView.findViewById(R.id.spn_Report);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(homeActivity.this,
                        android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.results));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                reportSpin.setAdapter(adapter);
                dialogBuilderReport.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!reportSpin.getSelectedItem().toString().equalsIgnoreCase("Please select a test result...")){
                            Toast.makeText(homeActivity.this,
                                    reportSpin.getSelectedItem().toString() + "Result Reported", Toast.LENGTH_SHORT).show();
                            //GET TEST RESULTS
                            dialogInterface.dismiss();
                        }
                    }
                });
                dialogBuilderReport.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialogBuilderReport.setView(reportView);
                dialog = dialogBuilderReport.create();
                dialog.show();
            }
        });

    }

    private void settingsPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.settings_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                switchToLoginActivity();
                return true;
        }
        return false;
    }

    private void switchToLoginActivity() {
        Intent switchActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchActivityIntent);
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
        dialogBuilderCreate = new AlertDialog.Builder(this);
        final View createContact = getLayoutInflater().inflate(R.layout.create_contact, null);

        btn_createCancel = (Button) createContact.findViewById(R.id.btn_createCancel);
        btn_createSave = (Button) createContact.findViewById(R.id.btn_createSave);

        dialogBuilderCreate.setView(createContact);
        dialog = dialogBuilderCreate.create();
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