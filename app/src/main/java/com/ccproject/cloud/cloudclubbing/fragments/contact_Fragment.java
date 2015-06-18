package com.ccproject.cloud.cloudclubbing.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebBackForwardList;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ccproject.cloud.cloudclubbing.activities.ApplicationController;
import com.ccproject.cloud.cloudclubbing.models.Customer;
import com.ccproject.cloud.cloudclubbing.models.NightClub;
import com.ccproject.test.myslidetest.R;
import com.dexafree.materialList.cards.BigImageCard;
import com.dexafree.materialList.cards.SmallImageCard;
import com.dexafree.materialList.view.MaterialListView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Macbook on 24/02/15.
 */
public class contact_Fragment extends Fragment
{
    View                                rootview;
    public static final String          PREFS_NAME = "ContactPrefFile";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootview = inflater.inflate(R.layout.contact_layout,container, false);
            MaterialListView mListView = (MaterialListView) rootview.findViewById(R.id.material_listview);
            //creation de la carte titre contact
            SmallImageCard title_card = new SmallImageCard(getActivity());
            title_card.setDescription(NightClub.getInstance().getDescription());
            title_card.setTitle(NightClub.getInstance().getName());
            title_card.setDrawable(R.drawable.henesisclub150px);
            mListView.add(title_card);

        BigImageCard card = new BigImageCard(getActivity());
        card.setTitle("Adresse");
        card.setDescription(NightClub.getInstance().getAdress());
        card.setDrawable(R.drawable.map_image);
        mListView.add(card);

        //creation de la carte info contact
        SmallImageCard phoneInfo_card = new SmallImageCard(getActivity());
        phoneInfo_card.setTitle("Contact us");
        phoneInfo_card.setDescription(NightClub.getInstance().getPhone());
        phoneInfo_card.setDrawable(R.drawable.phone_icon);
        mListView.add(phoneInfo_card);

        SmallImageCard webSiteInfo_card = new SmallImageCard(getActivity());
        webSiteInfo_card.setTitle("Vistit our Web Site");
        webSiteInfo_card.setDescription(NightClub.getInstance().getWebsite());
        webSiteInfo_card.setDrawable(R.drawable.mail_icon);
        mListView.add(webSiteInfo_card);


        return rootview;
    }



}
