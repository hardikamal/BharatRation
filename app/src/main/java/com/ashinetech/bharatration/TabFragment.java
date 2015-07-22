package com.ashinetech.bharatration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ragavendran on 10-07-2015.
 */
public class TabFragment extends Fragment
{
    private FragmentTabHost mTabHost;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // contains the v4 tabhost layout
        View view=inflater.inflate(R.layout.fragment_product_weight_qty_tabhost,container, false);


        // set the tab properties
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost = (FragmentTabHost)view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("quantity").setIndicator("Quantity"),
                ProductQuantity.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("weight").setIndicator("Weight"),
                ProductWeight.class, null);
        return view;
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }*/
}
