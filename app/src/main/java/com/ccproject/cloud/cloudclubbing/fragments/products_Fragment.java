package com.ccproject.cloud.cloudclubbing.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ccproject.cloud.cloudclubbing.models.Products;
import com.ccproject.test.myslidetest.R;

import java.util.ArrayList;

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

        ArrayList<Products> tab_product = get_tab_product();

        LinearLayout    my_productList = (LinearLayout) rootview.findViewById(R.id.Product_list);

        for (int i = 0; i < tab_product.size(); i++) {
            TextView textView = new TextView(getActivity().getApplicationContext());
            textView.setText(tab_product.get(i).getName() + " " + tab_product.get(i).getPrice() + "$");
            textView.setTextColor(Color.parseColor("#000000"));
            textView.setTextSize(30);
            my_productList.addView(textView);
        }

        return rootview;
    }

    public ArrayList<Products> get_tab_product() {
        ArrayList<Products> tab_product = new ArrayList<Products>();

        tab_product.add(new Products("Vodka", 15));
        tab_product.add(new Products("Champagne", 25));
        tab_product.add(new Products("Vin rouge", 15));
        tab_product.add(new Products("Vin blanc", 35));
        tab_product.add(new Products("Muscat", 5));
        tab_product.add(new Products("Coca Cola", 3));
        tab_product.add(new Products("Long Island", 15));


        return tab_product;
    }

    public void Refresh(){


    }

}
