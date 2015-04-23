package com.ccproject.cloud.cloudclubbing;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.Toast;

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
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ccproject.cloud.cloudclubbing.Tools.SHA1;

/**
 * Created by Macbook on 24/02/15.
 */
public class account_Fragment extends DialogFragment implements View.OnClickListener
{
    View                rootview;
    String              requestResult;
    public static final String PREFS_NAME = "LoginPrefFile";


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

    private void                signIn() {
        EditText text = (EditText)getView().findViewById(R.id.username);
        String username = text.getText().toString();
        text = (EditText)getView().findViewById(R.id.password);
        String password = text.getText().toString();
        loginRequest(username, password);
       /* AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Connexion");
        alertDialog.setMessage("Result :" + requestResult);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
//            alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();*/

    }

    public void                 cancelbtn()
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


    public void                 loginRequest(final String username, String password) {

        String          finalurl    =   getString(R.string.IP) + "user/login.php";
        finalurl                    =   finalurl + "?login=" + username + "&password=";
        try {
            finalurl = finalurl + SHA1(password);
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(getActivity(), getText(R.string.error_general), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            Toast.makeText(getActivity(), getText(R.string.error_general), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        Log.d("URL= ", finalurl);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, finalurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            //si le serveur renvoie les informations:
                            if (response.getString("request").equals("OK")) {
                                Log.d("RESULT OF THE REQUEST:", "OK");
                                Toast.makeText(getActivity(), "Conection succeful", Toast.LENGTH_SHORT).show();
                                Customer.getInstance().setId(response.getInt("id"));
                                Customer.getInstance().setEmail(response.getString("email"));
                                Customer.getInstance().setName(response.getString("name"));
                                Customer.getInstance().setLogin(response.getString("login"));
                                Customer.getInstance().setPictureURL(response.getString("picURL"));
                                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString("username", Customer.getInstance().getLogin());
                                editor.putString("email", Customer.getInstance().getEmail());
                                editor.putInt("Id", Customer.getInstance().getInstance().getId());
                                editor.commit();
                            }
                            else
                                Toast.makeText(getActivity(), getText(R.string.error_badParam), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Conection error", Toast.LENGTH_SHORT).show();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req, "Login");
    }

}

