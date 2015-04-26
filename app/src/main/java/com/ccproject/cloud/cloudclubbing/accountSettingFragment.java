package com.ccproject.cloud.cloudclubbing;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ccproject.test.myslidetest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class accountSettingFragment extends android.support.v4.app.Fragment {

    View                rootview;


    public accountSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_account_setting,container, false);


        TextView usernameTitle = (TextView) rootview.findViewById(R.id.textViewUsername);
        usernameTitle.setText(Customer.getInstance().getLogin().toString());
        TextView userInfo = (TextView)rootview.findViewById(R.id.textViewUserinfo);
        userInfo.setText("Name: " + Customer.getInstance().getName() + "\nEmail: " + Customer.getInstance().getEmail() +
                "\nId: " + Customer.getInstance().getId());

        // Inflate the layout for this fragment
        return rootview;
    }


}
