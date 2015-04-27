package com.ccproject.cloud.cloudclubbing;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.ccproject.test.myslidetest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class accountSettingFragment  extends android.support.v4.app.Fragment implements View.OnClickListener {

    View                rootview;


    public accountSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootview                = inflater.inflate(R.layout.fragment_account_setting,container, false);
        TextView usernameTitle  = (TextView) rootview.findViewById(R.id.userLogin_title);
        TextView userInfo       = (TextView)rootview.findViewById(R.id.username_accountsetting);
        ImageView   user_image  = (ImageView) rootview.findViewById(R.id.user_image);
        Button  diconect_button =   (Button) rootview.findViewById(R.id.button_disconect);


        usernameTitle.setText(Customer.getInstance().getLogin().toString());
        userInfo.setText(Customer.getInstance().getName());
        userInfo = (TextView)rootview.findViewById(R.id.email_accountsetting);
        userInfo.setText(Customer.getInstance().getEmail());
        userInfo = (TextView)rootview.findViewById(R.id.phone_accountsetting);
        userInfo.setText(null);
        userInfo = (TextView)rootview.findViewById(R.id.adress_accountsetting);
        userInfo.setText(null);
        user_image.setImageResource(R.drawable.login_user_default_icon);

        diconect_button.setOnClickListener(this);

        return rootview;
    }


    public void                             disconect() {

        //recupère et modifie le LoginPrefFile dans le but le formater
        SharedPreferences settings                  = getActivity().getSharedPreferences("LoginPrefFile", 0);
        android.support.v4.app.Fragment my_objFragment = new account_Fragment();

        //Reinitialise l'objet user
        Customer.getInstance().setId(0);
        Customer.getInstance().setEmail("");
        Customer.getInstance().setLogin("");
        Customer.getInstance().setPictureURL(null);
        Customer.getInstance().setCard(null);

        //formatage du fichier log
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", Customer.getInstance().getLogin());
        editor.putString("email", Customer.getInstance().getEmail());
        editor.putInt("Id", Customer.getInstance().getInstance().getId());
        editor.commit();

        //affichage du message
        Toast.makeText(getActivity(), getString(R.string.disconected), Toast.LENGTH_SHORT).show();

        //Changement de vue vers la connexion
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container,my_objFragment)
                .commit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_disconect:
                disconect();
                break;
        }
    }
}
