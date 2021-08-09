package edu.example.blocktraceui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonIOException;

import org.json.JSONException;

import edu.example.blockTraceData.Person;
import edu.example.blockTraceData.RequestController;
import edu.example.blockTraceData.ResponseStatus;
import edu.example.blockTraceData.UserProfile;

public class homeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private AlertDialog.Builder dialogBuilderCreate, dialogBuilderReport;
    private AlertDialog dialog;
    private EditText contactName;
    private Button btn_createCancel, btn_createSave;
    boolean hasGottenResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        PopulateFields();
        GetUserContacts();


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
                        if(!reportSpin.getSelectedItem().toString().equalsIgnoreCase("Please select a test result..."))
                        {
                            String reportedStatus=reportSpin.getSelectedItem().toString();
                            boolean newStatus= StringToBool(reportedStatus);
                            UserProfile.GetActivePofile().profileOwner.isHealthy =newStatus;
                            try {
                                RequestController.ReportHealthStatus(UserProfile.GetActivePofile().profileOwner,homeActivity.this::OnPersonUpdated);
                            }
                            catch (JSONException ex){}
                            Toast.makeText(homeActivity.this,
                                     reportedStatus + " Result Reported", Toast.LENGTH_SHORT).show();
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
        EditText txt = (EditText) createContact.findViewById(R.id.contactName);
        btn_createSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String userinputName =txt.getText().toString();
                if(userinputName!=null&&!userinputName.equals("")) {
                    try {
                        RequestController.AddByUserName(UserProfile.GetActivePofile().profileOwner, userinputName, homeActivity.this::OnContactAdded);
                    } catch (JSONException ex) {
                    }
                }
                dialog.dismiss();
            }
        });
    }

    public void PopulateFields()
    {
        TextView user = (TextView) findViewById(R.id.txt_username);
        TextView name = (TextView) findViewById(R.id.txt_name);
        TextView age = (TextView) findViewById(R.id.txt_age);
        TextView gender = (TextView) findViewById(R.id.txt_gender);
        TextView location = (TextView) findViewById(R.id.txt_location);
        TextView phone = (TextView) findViewById(R.id.txt_phone);
        TextView result = (TextView) findViewById(R.id.txt_result);

        user.setText(UserProfile.GetActivePofile().profileOwner.userName);
        name.setText(UserProfile.GetActivePofile().profileOwner.firstName + " " + UserProfile.GetActivePofile().profileOwner.lastName);
        age.setText(String.valueOf(UserProfile.GetActivePofile().profileOwner.age));
        gender.setText(UserProfile.GetActivePofile().profileOwner.gender);
        location.setText(UserProfile.GetActivePofile().profileOwner.location);
        phone.setText(UserProfile.GetActivePofile().profileOwner.phone);
        result.setText(boolToString(UserProfile.GetActivePofile().profileOwner.isHealthy));

    }
    String boolToString(boolean isHealth)
    {
        if(isHealth)
            return "NEGATIVE";
        else return "POSITIVE";
    }
    public void GetUserContacts()
    {
        Person currentPerson= UserProfile.GetActivePofile().profileOwner;
        try {
            RequestController.GetAllContacts(currentPerson,this::OnContactsRecieved);
        }
        catch (JSONException ex){}

    }
    void OnContactsRecieved(ResponseStatus status, Person[] allContacts)
    {
        hasGottenResponse=true;
        if(status.isValid)
        {
            int len = allContacts.length;
            for(int i=0; i<len; ++i)
                Log.v("API",  allContacts[i].userName);
            UserProfile.SetContactsList(allContacts);
        }
        else
            Log.v("API",  status.errorMessage);
    }
    void OnContactAdded(ResponseStatus status)
    {
        if(status.isValid)
        {
            Toast.makeText(homeActivity.this,
                    "Contact Added", Toast.LENGTH_SHORT).show();
            UserProfile.RefreshContacts(null);
            Log.v("API",  "contact add success");
        }
        else
            Log.v("API",  status.errorMessage);
    }
    boolean StringToBool(String reportedStatus)
    {
        String[] statusOptions=getResources().getStringArray(R.array.results);
        if(statusOptions[1].equals(reportedStatus))
            return true;
        else return false;
    }
    void OnPersonUpdated(ResponseStatus status)
    {
        if(status.isValid)
        {

            Log.v("API", "Update worked");
        }
        else
        {
            Log.v("API",status.errorMessage);
        }
    }
}