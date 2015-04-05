package com.ccproject.cloud.cloudclubbing;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.ccproject.test.myslidetest.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Macbook on 24/02/15.
 */
public class account_Fragment extends DialogFragment implements View.OnClickListener
{
    View                rootview;
    HttpRequest         myHttpRequest = new HttpRequest();
    String              requestResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootview = inflater.inflate(R.layout.account_layout,container, false);

        Button my_cancelbtn = (Button) rootview.findViewById(R.id.cancelbtn);
        my_cancelbtn.setOnClickListener(this);
        Button my_signInbtn = (Button) rootview.findViewById(R.id.email_sign_in_button);
        my_signInbtn.setOnClickListener(this);
        return rootview;
    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.email_sign_in_button:
                signIn();
                break;
            case R.id.cancelbtn:
                cancelbtn();
                break;
        }

    }

    private void signIn() {

        //myHttpRequest.getRequest();
        //RequestQueue    my_requestQueue     =   ApplicationController.getInstance().getRequestQueue();
        String          response;

        response = "";


        EditText text = (EditText)getView().findViewById(R.id.username);
        String username = text.getText().toString();
        text = (EditText)getView().findViewById(R.id.password);
        String password = text.getText().toString();
        //myHttpRequest.postRequest(username, password);
        getRequest(username, password);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Connexion");
        alertDialog.setMessage("Result :" + requestResult);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
//            alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();

    }

    public void cancelbtn()
    {
        RequestQueue    my_requestQueue     =   ApplicationController.getInstance().getRequestQueue();

        /*Intent intent = new Intent(this, Home.class);
        startActivity(intent);*/
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Connexion");
        alertDialog.setMessage("Result :" + my_requestQueue);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
//            alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();

    }

    public void         getStringRequest(String username, String password)
    {
        String                      finalurl        =   getString(R.string.IP) + "login.php";
        //String                      finalurl        =   "http://37.187.126.169/api/login.php";

        finalurl = finalurl + "?login=";
        finalurl = finalurl + username;
        finalurl = finalurl + "&password=";
        finalurl = finalurl + password;
        Log.d("URL= ", finalurl);
       // HashMap<String, String> params = new HashMap<String, String>();

        StringRequest req = new StringRequest(Request.Method.GET, finalurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        requestResult = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

            }
        });

        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req, "Login");

    }

    public void                 getRequest(String username, String password) {

        String                      finalurl        =   getString(R.string.IP) + "login.php";
        //String                      finalurl        =   "http://37.187.126.169/api/login.php";

        finalurl = finalurl + "?login=";
        finalurl = finalurl + username;
        finalurl = finalurl + "&password=";
        finalurl = finalurl + password;
        Log.d("URL= ", finalurl);
        // pass second argument as "null" for GET requests

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, finalurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            requestResult = response.toString();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req, "Login");
    }
}

