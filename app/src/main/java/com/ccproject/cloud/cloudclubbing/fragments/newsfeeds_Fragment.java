package com.ccproject.cloud.cloudclubbing.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccproject.cloud.cloudclubbing.activities.ApplicationController;
import com.ccproject.cloud.cloudclubbing.models.NightClub;
import com.ccproject.test.myslidetest.R;
import com.dexafree.materialList.cards.SmallImageCard;
import com.dexafree.materialList.view.MaterialListView;

/**
 * Created by Macbook on 24/02/15.
 */
public class newsfeeds_Fragment extends Fragment
{
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootview = inflater.inflate(R.layout.newsfeeds_layout,container, false);
        MaterialListView mListView = (MaterialListView) rootview.findViewById(R.id.material_listview);

        for (int i = 0; i < ApplicationController.getInstance().getNewsfeedsList().size(); i++) {
            if (ApplicationController.getInstance().getNewsfeedsList().get(i).getId() == 1) {
                SmallImageCard phoneInfo_card = new SmallImageCard(getActivity());
                phoneInfo_card.setTitle("Contact us");
                phoneInfo_card.setDescription(ApplicationController.getInstance().getNewsfeedsList().get(i).getFlowMessage());
                phoneInfo_card.setDrawable(R.drawable.phone_icon);
                mListView.add(phoneInfo_card);

            }

        }
        return rootview;
    }
}
