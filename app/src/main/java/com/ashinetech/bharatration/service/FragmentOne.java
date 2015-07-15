package com.ashinetech.bharatration.service;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashinetech.bharatration.R;

public class FragmentOne extends Fragment {

    ImageView ivIcon;
    TextView tvItemName;

    public FragmentOne()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.product_list_item, container,
                false);

        return view;
    }

}