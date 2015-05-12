package com.ccproject.cloud.cloudclubbing.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ccproject.cloud.cloudclubbing.model.Customer;
import com.ccproject.test.myslidetest.R;


public class AccountSetting extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView usernameTitle = (TextView)findViewById(R.id.textViewUsername);
        usernameTitle.setText(Customer.getInstance().getLogin());
        TextView userInfo = (TextView)findViewById(R.id.textViewUsername);
        userInfo.setText("Name: " + Customer.getInstance().getName() + "\nEmail: " + Customer.getInstance().getEmail() +
                "\nId: " + Customer.getInstance().getId());

        setContentView(R.layout.activity_account_setting);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account_setting, menu);
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
}
