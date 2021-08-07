package edu.example.blockTraceData;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.util.JsonReader;

import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.gson.*;
public class RequestController
{
    private static RequestController mainController;
    Context currentContext;
    static RequestQueue requestQueue;
    static Gson gson;
    static  boolean  isInitialized;
    private RequestController(Context context)
    {
        currentContext = context;
        requestQueue = Volley.newRequestQueue(currentContext.getApplicationContext());
        gson = new Gson();
    }

    public static synchronized void Initialize(Context context)
    {
        if (!isInitialized)
        {
            mainController = new RequestController(context);
            isInitialized=true;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void GetNum(int num, BiConsumer<Boolean,String> callback)
    {
        String url ="https://bchainhealth.azurewebsites.net/api/values/"+String.valueOf(num);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.accept(true,response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.accept(false,error.getLocalizedMessage());
            }
        });
        requestQueue.add(stringRequest);
    }
    public static void AddPerson()
    {

    }

}
