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
        View view = inflater.inflate(R.layout.fragment_product_weight_qty_tabhost,container, false);
        mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.product_tabhost_container);

        //mTabHost = new FragmentTabHost(getActivity());
        //mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.product_weight_qty_container);

        mTabHost.addTab(mTabHost.newTabSpec("Weight").setIndicator("Weight (KG)"),
                ProductWeight.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Quantity").setIndicator("Quantity"),
                ProductQuantity.class, null);

        return view;
        //return mTabHost;
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }*/
}
