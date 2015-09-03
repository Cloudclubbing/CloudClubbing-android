package com.ccproject.cloud.cloudclubbing.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ccproject.cloud.cloudclubbing.activities.ApplicationController;
import com.ccproject.test.myslidetest.R;
import com.kogitune.activity_transition.fragment.FragmentTransitionLauncher;


/**
 * Created by Macbook on 24/02/15.
 */
public class events_Fragment extends Fragment {
    View                    rootview;
    private ListView        lv_events;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.events_layout, container, false);
        lv_events = (ListView)  rootview.findViewById(R.id.lv_event_fragment);

        EventAdapter    adapter =   new EventAdapter();
        lv_events.setAdapter(adapter);
        lv_events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final eventDetail_fragment      my_fragment = new eventDetail_fragment();
                Bundle bundle = new Bundle();

                bundle.putString("eventName", ApplicationController.getInstance().getEventsList().get(position).getName());
               // bundle.putString("eventDate", ApplicationController.getInstance().getEventsList().get(position).getEventDate().toString());
                bundle.putString("eventDescription", ApplicationController.getInstance().getEventsList().get(position).getDescription());
                bundle.putString("evenPictureUrl", ApplicationController.getInstance().getEventsList().get(position).getPictureUrl());
                my_fragment.setArguments(bundle);

               FragmentTransitionLauncher
                        .with(view.getContext());
                getFragmentManager().beginTransaction().replace(R.id.container, my_fragment).addToBackStack(null).commit();
            }

        });

        return rootview;
    }

    public class EventAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ApplicationController.getInstance().getEventsList().size();
        }

        @Override
        public Object getItem(int position) {
            return ApplicationController.getInstance().getEventsList().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder              my_holder = new ViewHolder();
            View                    my_view;
            ImageLoader             mImageLoader = ApplicationController.getInstance().getImageLoader();


            if (convertView == null) {
                my_view = View.inflate(getActivity(), R.layout.item_event,
                        null);
                my_holder = new ViewHolder();
            }
            else
                my_view = convertView;

            my_holder.iv_event_image    =   (NetworkImageView) my_view.findViewById(R.id.iv_event_image);
            my_holder.tv_event_name     =   (TextView) my_view.findViewById(R.id.tv_event_name);
            my_holder.iv_event_image.setImageUrl(ApplicationController.getInstance().getEventsList().get(position).getPictureUrl(), mImageLoader);
            my_holder.tv_event_name.setText(ApplicationController.getInstance().getEventsList().get(position).getName());

            return my_view;
        }
    }

    class ViewHolder {
        NetworkImageView        iv_event_image;
        TextView                tv_event_name;
    }

}

