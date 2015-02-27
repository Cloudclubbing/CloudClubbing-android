package com.ccproject.cloud.cloudclubbing;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccproject.test.myslidetest.R;

/**
 * Created by Macbook on 24/02/15.
 */
public class products_Fragment extends Fragment
{
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootview = inflater.inflate(R.layout.products_layout,container, false);
        return rootview;
    }
}
