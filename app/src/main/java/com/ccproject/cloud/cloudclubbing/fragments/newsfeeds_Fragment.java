package com.ccproject.cloud.cloudclubbing.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccproject.cloud.cloudclubbing.activities.ApplicationController;
import com.ccproject.cloud.cloudclubbing.models.NightClub;
import com.ccproject.test.myslidetest.R;
import com.dexafree.materialList.cards.BigImageCard;
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
        MaterialListView mListView = (MaterialListView) rootview.findViewById(R.id.material_listview2);

        for (int i = 0; i < ApplicationController.getInstance().getNewsfeedsList().size(); i++) {
            if (ApplicationController.getInstance().getNewsfeedsList().get(i).getContent_type().equals("Status")) {
                Log.d("TestNEWSFEED", "OK!!!");
                SmallImageCard status_card = new SmallImageCard(getActivity());
                status_card.setTitle(ApplicationController.getInstance().getNewsfeedsList().get(i).getContent_type());
                status_card.setDescription(ApplicationController.getInstance().getNewsfeedsList().get(i).getContent());
                status_card.setDrawable(R.drawable.facebook_icon);
                mListView.add(status_card);

            }
            if (ApplicationController.getInstance().getNewsfeedsList().get(i).getContent_type().equals("Photos")) {
                BigImageCard card = new BigImageCard(getActivity());
                card.setTitle(ApplicationController.getInstance().getNewsfeedsList().get(i).getContent_type());
                card.setDescription(ApplicationController.getInstance().getNewsfeedsList().get(i).getContent());
                card.setDrawable(R.drawable.image_media);
                mListView.add(card);
            }
            if (ApplicationController.getInstance().getNewsfeedsList().get(i).getContent_type().equals("Event")) {
                SmallImageCard event_card = new SmallImageCard(getActivity());
                event_card.setTitle(ApplicationController.getInstance().getNewsfeedsList().get(i).getContent_type());
                event_card.setDescription(ApplicationController.getInstance().getNewsfeedsList().get(i).getContent());
                event_card.setDrawable(R.drawable.facebook_icon);
                mListView.add(event_card);

            }

        }
        return rootview;
    }
}
