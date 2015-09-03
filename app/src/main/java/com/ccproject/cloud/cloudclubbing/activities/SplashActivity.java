package com.ccproject.cloud.cloudclubbing.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ccproject.cloud.cloudclubbing.models.Customer;
import com.ccproject.cloud.cloudclubbing.models.Events;
import com.ccproject.cloud.cloudclubbing.models.Newsfeeds;
import com.ccproject.cloud.cloudclubbing.models.NightClub;
import com.ccproject.test.myslidetest.R;

import org.json.JSONException;
import org.json.JSONObject;


public class SplashActivity extends Activity {

    private final int                   SPLASH_DISPLAY_LENGTH = 2000;
    public static final String          PREFS_NAME = "ContactPrefFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getNightClubInfoRequest(1);
        getNewsfeedRequest();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
        SharedPreferences settings = this.getSharedPreferences("LoginPrefFile", 0);
        Customer.getInstance().setId(settings.getInt("Id", 0));
        Customer.getInstance().setLogin(settings.getString("username", null));
        Customer.getInstance().setEmail(settings.getString("email", null));
        Log.d("UserINFO: ", "ID: " + Customer.getInstance().getId() + " username: " + Customer.getInstance().getLogin());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void                getNightClubInfoRequest(int id) {

        String  Url = this.getString(R.string.IP) + "nightclub/fb_profile.php?id=" + id;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //si le serveur renvoie les informations:
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            if (response.getJSONObject("request").getString("result").equals("OK")) {
                                Log.d("RESULT OF THE REQUEST:", "OK");
                                NightClub.getInstance().setAdress(response.getJSONObject("Nightclub").getString("Street") + ", " + response.getJSONObject("Nightclub").getString("City") + " " + response.getJSONObject("Nightclub").getString("ZIP"));
                                NightClub.getInstance().setName(response.getJSONObject("Nightclub").getString("Name"));
                                NightClub.getInstance().setDescription(response.getJSONObject("Nightclub").getString("Description"));
                                NightClub.getInstance().setPhone(response.getJSONObject("Nightclub").getString("Phone"));
                                NightClub.getInstance().setWebsite(response.getJSONObject("Nightclub").getString("Website"));
                                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString("Name", NightClub.getInstance().getName());
                                editor.putString("Description", NightClub.getInstance().getDescription());
                                editor.putString("Adress", NightClub.getInstance().getAdress());
                                editor.putString("Phone", NightClub.getInstance().getPhone());
                                editor.commit();

                            }
                            else
                                Toast.makeText(getApplicationContext(), getString(R.string.error_general), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //En cas d'erreur de connexion au serveur
                Toast.makeText(getApplicationContext(), getString(R.string.error_unable_access_server), Toast.LENGTH_SHORT).show();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req, "Contact");
    }

    void getNewsfeedRequest() {

        String Url = this.getString(R.string.IP) + "nightclub/fb_feed.php?id=1&max=";

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //si le serveur renvoie les informations:
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            if (response.getJSONObject("request").getString("result").equals("OK")) {

                                if (response.getJSONArray("feed") != null) {
                                    Log.d("RESULT OF THE REQUEST:", "OK2");

                                    ApplicationController.getInstance().getEventsList().add(new Events("Test1", null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "http://flyers.movetonight.com/_1373299509.jpg"));
                                    ApplicationController.getInstance().getEventsList().add(new Events("Test12", null, "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un peintre anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.", "http://www.ibiza-voice.com/media/calendar/2013/Miami/Ultra-Music-Festival-flyer-image.jpg"));
                               /* for (int j = 0; j < response.getJSONArray("feed").length(); j++) {
                                    if (response.getJSONArray("feed").get(j).equals("content") == "Henesis created an event.") {
                                        ApplicationController.getInstance().getNewsfeedsList().add(new Newsfeeds(1, "event");
                                    }*/
                                }
                            } else
                                Toast.makeText(getApplicationContext(), getString(R.string.error_general), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //En cas d'erreur de connexion au serveur
                Toast.makeText(getApplicationContext(), getString(R.string.error_unable_access_server), Toast.LENGTH_SHORT).show();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req, "NewFeed");

    }

}
