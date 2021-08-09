package edu.example.blockTraceData;
import com.android.volley.*;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.content.Context;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestController
{
    static Context currentContext;
    static RequestQueue requestQueue;
    static Gson gson;
    static  boolean  isInitialized;
    public static synchronized void Initialize(Context context)
    {
        if (!isInitialized)
        {
            currentContext = context;
            isInitialized=true;
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            gson = new Gson();
        }
    }
    // get a user by thier ID
    public static void GetPerson(String userName ,BiConsumer<ResponseStatus,Person> callback) throws JSONException,UnsupportedOperationException
    {
        String url ="https://bchainhealth.azurewebsites.net/People/Find/"+userName;
        JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Person p=null;
                    p= gson.fromJson(response.toString(),Person.class);
                    callback.accept(new ResponseStatus(true,""),p);
                },
                error -> {
                    String errorMessage ="";
                    try {
                        errorMessage = new String(error.networkResponse.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    callback.accept(new ResponseStatus(false,errorMessage),null);
                });
        requestQueue.add(jRequest);
    }
    // self-explanatory, if you ever need to get a person by their ID
    public static void GetPerson(int userID ,BiConsumer<ResponseStatus,Person> callback) throws JSONException,UnsupportedOperationException
    {
        String url ="https://bchainhealth.azurewebsites.net/People/"+userID;
        JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Person p=null;
                    p= gson.fromJson(response.toString(),Person.class);
                    callback.accept(new ResponseStatus(true,""),p);
                },
                error -> {
                    String errorMessage ="";
                    try {
                        errorMessage = new String(error.networkResponse.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    callback.accept(new ResponseStatus(false,errorMessage),null);
                });
        requestQueue.add(jRequest);
    }
    // should be a 2 element array [0]->username [1]->password
    // callback will give you back the Person in the database
    public static void Login(String[] loginData ,BiConsumer<ResponseStatus,Person> callback) throws JSONException,UnsupportedOperationException
    {
        String loginJson=gson.toJson((loginData));
        String url ="https://bchainhealth.azurewebsites.net/People/Login";
        JsonArrayRequest jRequest = new JsonArrayRequest(Request.Method.POST, url, new JSONArray(loginJson),
                response -> {
                    Person p=null;
                    try {
                        p= gson.fromJson(response.get(0).toString(),Person.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                   callback.accept(new ResponseStatus(true,""),p);
                },
                error -> {
                    String errorMessage ="";
                    try {
                        errorMessage = new String(error.networkResponse.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    callback.accept(new ResponseStatus(false,errorMessage),null);
                });
        requestQueue.add(jRequest);
    }
    // In the callback, the server will return back the Person instance you passed it and if the account creation succeeded
    // then it will have automatically populated the ID field of the person instance
    public static void CreateNewAccount(Person newPerson , BiConsumer<ResponseStatus,Person> callback) throws JSONException,UnsupportedOperationException
    {
        String creationJson=gson.toJson((newPerson));
        String url ="https://bchainhealth.azurewebsites.net/People/Create";
        JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(creationJson),
                response ->
                {
                    Person p=null;
                        p= gson.fromJson(response.toString(),Person.class);
                        newPerson.id = p.id;
                    callback.accept(new ResponseStatus(true,""),p);
                },
                error ->
                {
                    String errorMessage ="";
                    try {
                        errorMessage = new String(error.networkResponse.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    callback.accept(new ResponseStatus(false,errorMessage),null);
                });
        requestQueue.add(jRequest);
    }
    // the local users ID, int[] the id of the user he you wish to add
    // example usage would be (5, [6,8] , callback). the effect would be adding the people with ids 6 & 8 to the contact list of person with ID 5
    public static void AddNewContacts(int CurrentUserId, int[] IdsToAdd , Consumer<ResponseStatus> callback) throws JSONException,UnsupportedOperationException
    {
        String newIdsAsJson=gson.toJson(IdsToAdd);
        Log.v("API",newIdsAsJson);
        String url ="https://bchainhealth.azurewebsites.net/People/AddContacts/"+ String.valueOf(CurrentUserId);
        JsonArrayRequest jRequest = new JsonArrayRequest(Request.Method.POST, url, new JSONArray(newIdsAsJson),
                response -> {
                    callback.accept(new ResponseStatus(true,""));
                },
                error -> {
                    String errorMessage ="";
                    try {
                        if(error.networkResponse!=null)
                        errorMessage = new String(error.networkResponse.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Log.v("API",error.getMessage());
                    callback.accept(new ResponseStatus(false,errorMessage));
                });
        requestQueue.add(jRequest);
    }
    // the local users ID, int[] the id of the user he you wish to add
    public static void RemoveContacts(int CurrentUserId, int[] IdsToAdd , Consumer<ResponseStatus> callback) throws JSONException,UnsupportedOperationException
    {
        String newIdsAsJson=gson.toJson(IdsToAdd);
        Log.v("API",newIdsAsJson);
        String url ="https://bchainhealth.azurewebsites.net/People/RemoveContacts/"+ String.valueOf(CurrentUserId);
        JsonArrayRequest jRequest = new JsonArrayRequest(Request.Method.POST, url, new JSONArray(newIdsAsJson),
                response -> {
                    callback.accept(new ResponseStatus(true,""));
                },
                error -> {
                    String errorMessage ="";
                    try {
                        if(error.networkResponse!=null)
                            errorMessage = new String(error.networkResponse.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Log.v("API",error.getMessage());
                    callback.accept(new ResponseStatus(false,errorMessage));
                });
        requestQueue.add(jRequest);
    }
    // just a nice convenience method that wraps the method above.
    public static void RemoveContacts(Person person, int[] IdsToAdd , Consumer<ResponseStatus> callback) throws JSONException,UnsupportedOperationException
    {
        RemoveContacts(person.id,IdsToAdd,callback);
    }
    public static void GetAllContacts(int CurrentUserId, BiConsumer<ResponseStatus,Person[]> callback) throws JSONException,UnsupportedOperationException
    {
        String url ="https://bchainhealth.azurewebsites.net/People/Contacts/"+ CurrentUserId;
        JsonArrayRequest jRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response ->
                {
                    Person[] people=null;
                    people= gson.fromJson(response.toString(),Person[].class);
                    callback.accept(new ResponseStatus(true,""),people);
                },
                error -> {
                    String errorMessage ="";
                    try {
                        if(error.networkResponse.data!=null)
                        errorMessage = new String(error.networkResponse.data,"UTF-8");
                        Log.v("API",String.valueOf(error.networkResponse.statusCode));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    callback.accept(new ResponseStatus(false,errorMessage),null);
                });
        requestQueue.add(jRequest);
    }
    public static void GetAllContacts(Person person, BiConsumer<ResponseStatus,Person[]> callback) throws JSONException,UnsupportedOperationException
    {
        GetAllContacts(person.id,callback);
    }
    // call this method if you want to update a person in the DB
    public static void UpdatePerson(Person p, Consumer<ResponseStatus> callback) throws UnsupportedOperationException, JSONException {
        String personAsJson= gson.toJson(p);
        String url ="https://bchainhealth.azurewebsites.net/People/Update/"+ p.id;
        JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(personAsJson),
                response ->
                {
                    callback.accept(new ResponseStatus(true,""));
                },
                error -> {
                    String errorMessage ="";
                    try {
                        if(error.networkResponse!=null)
                            errorMessage = new String(error.networkResponse.data,"UTF-8");
                        Log.v("API",error.getMessage());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    callback.accept(new ResponseStatus(false,errorMessage));
                });
        requestQueue.add(jRequest);
    }
}
