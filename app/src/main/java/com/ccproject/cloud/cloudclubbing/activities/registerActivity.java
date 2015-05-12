package com.ccproject.cloud.cloudclubbing.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ccproject.test.myslidetest.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static com.ccproject.cloud.cloudclubbing.tools.Tools.SHA1;


public class registerActivity extends ActionBarActivity {

            registerActivity        my_obj      =   this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        final Button button = (Button) findViewById(R.id.register_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<String> params = getParams();
                registerRequest(params.get(0), params.get(1), params.get(2));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.registerbtn) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    List<String>                getParams() {

        List<String>    params  =   new ArrayList<>();

        EditText        text    = (EditText) findViewById(R.id.username_register);
        params.add(text.getText().toString());
        text                    = (EditText) findViewById(R.id.email_register);
        params.add(text.getText().toString());
        text                    = (EditText) findViewById(R.id.password_register);
        params.add(text.getText().toString());

        return params;
    }

    public void                 registerRequest(String username, String email, String password) {

        String          finalurl    =   getString(R.string.IP) + "user/register.php";
        finalurl                    =   finalurl + "?login=" + username + "&password=";

        try {
            finalurl = finalurl + SHA1(password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            Toast.makeText(this, getText(R.string.error_general), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        finalurl                    =   finalurl + "&email=" + email;
        Log.d("URL= ", finalurl);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, finalurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            if (response.getString("request").equals("OK")) {
                                //Display message and change of view after the register succed
                                Toast.makeText(my_obj, getString(R.string.register_succed), Toast.LENGTH_SHORT).show();
                                my_obj.finish();
                            }
                            if (response.getString("request").equals("KO")) {
                                if (response.getInt("errno") == 1)
                                    Toast.makeText(my_obj, getString(R.string.account_exists), Toast.LENGTH_SHORT).show();
                                if (response.getInt("errno") == 2)
                                    Toast.makeText(my_obj, getString(R.string.error_general), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(my_obj, getString(R.string.error_unable_access_server), Toast.LENGTH_SHORT).show();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req, "Login");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
