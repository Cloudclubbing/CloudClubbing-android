package com.ccproject.cloud.cloudclubbing.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccproject.test.myslidetest.R;
import com.kogitune.activity_transition.fragment.ExitFragmentTransition;
import com.kogitune.activity_transition.fragment.FragmentTransition;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class eventDetail_fragment extends Fragment {

    ImageView       iv_event_image;
    TextView        tv_event_Name;
    TextView        tv_event_Descritpion;
    String      event_name;
    String      event_description;
    //String      event_date;
    String      pictureUrl;
    View        rootview;

    public eventDetail_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_event_detail, container, false);
        iv_event_image =   (ImageView) rootview.findViewById(R.id.iv_event_image2);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            event_name = bundle.getString("eventName", "Event Name");
            event_description = bundle.getString("eventDescription", "Event Description");
            //event_date = bundle.getString("eventName", "Event Name");
            pictureUrl = bundle.getString("evenPictureUrl", "PictureUrl");
            Picasso.with(getActivity().getApplicationContext()).load(pictureUrl).into(iv_event_image);

        }
        tv_event_Name           =   (TextView) rootview.findViewById(R.id.tv_event_title);
        tv_event_Descritpion    =   (TextView) rootview.findViewById(R.id.tv_event_description);

        tv_event_Name.setText(event_name);
        tv_event_Descritpion.setText(event_description);
        tv_event_Descritpion.setTextColor(Color.parseColor("#000000"));
        return rootview;
    }


}
