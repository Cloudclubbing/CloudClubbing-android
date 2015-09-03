package com.ccproject.cloud.cloudclubbing.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ccproject.cloud.cloudclubbing.activities.ApplicationController;
import com.ccproject.cloud.cloudclubbing.models.Customer;
import com.ccproject.cloud.cloudclubbing.activities.registerActivity;
import com.ccproject.test.myslidetest.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


import static com.ccproject.cloud.cloudclubbing.tools.Tools.SHA1;

/**
 * Created by priteshasvine on 24/02/15.
 * Last update by priteshasvine on 09/05/2015 22h 15mn.
 */

public class account_Fragment extends DialogFragment implements View.OnClickListener
{
    View                        rootview;
    public static final String  PREFS_NAME = "LoginPrefFile";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
            rootview = inflater.inflate(R.layout.account_layout,container, false);
        //Récuperation des boutons depuis le layout dans le but de définir leur actions
        Button my_registerbtn = (Button) rootview.findViewById(R.id.registerbtn);
        my_registerbtn.setOnClickListener(this);
        Button my_signInbtn = (Button) rootview.findViewById(R.id.email_sign_in_button);
        my_signInbtn.setOnClickListener(this);
        return rootview;
    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.email_sign_in_button:
                signIn();
                break;
            case R.id.registerbtn:
                registerbtn();
                break;
        }

    }

    /*
        Récupère les paramètres de connexion et execute la requête
    */
    private void                signIn() {

        //récuperation de s paramètres entrés par l'utilisateur
        EditText text = (EditText)getView().findViewById(R.id.username);
        String username = text.getText().toString();
        text = (EditText)getView().findViewById(R.id.password);
        String password = text.getText().toString();
        //execute la requête de l'utilisateur
        loginRequest(username, password);

    }

    /*
        Ouvre la page d'enregistrement
    */
    public void                 registerbtn()
    {
        Intent appel = new Intent(getActivity(), registerActivity.class);
        startActivity(appel);
    }

    /*
        Execute la requête http de connection
    */
    public void                 loginRequest(final String username, String password) {

        String          finalurl    =   getString(R.string.IP) + "user/login.php";
        finalurl                    =   finalurl + "?login=" + username + "&password=";
        try {
            finalurl = finalurl + SHA1(password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            Toast.makeText(getActivity(), getText(R.string.error_general), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        Log.d("URL= ", finalurl);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, finalurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //si le serveur renvoie les informations:
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            if (response.getJSONObject("request").getString("result").equals("OK")) {
                                Log.d("RESULT OF THE REQUEST:", "OK");
                                Toast.makeText(getActivity(), getString(R.string.conection_succed), Toast.LENGTH_SHORT).show();
                                Customer.getInstance().setId(response.getJSONObject("user").getInt("id"));
                                Customer.getInstance().setEmail(response.getJSONObject("user").getString("email"));
                                Customer.getInstance().setName(response.getJSONObject("user").getString("name"));
                                Customer.getInstance().setLogin(response.getJSONObject("user").getString("login"));
                                Customer.getInstance().setPictureURL(response.getJSONObject("user").getString("picURL"));
                                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString("username", Customer.getInstance().getLogin());
                                editor.putString("email", Customer.getInstance().getEmail());
                                editor.putInt("Id", Customer.getInstance().getId());
                                editor.commit();
                                Fragment objFragment = new accountSettingFragment();

                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .replace(R.id.container,objFragment)
                                        .commit();
                            }
                            else
                                Toast.makeText(getActivity(), getString(R.string.error_bad_param), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //En cas d'erreur de connexion au serveur
                Toast.makeText(getActivity(), getString(R.string.error_unable_access_server), Toast.LENGTH_SHORT).show();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req, "Login");
    }

}

