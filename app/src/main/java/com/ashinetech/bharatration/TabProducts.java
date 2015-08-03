package com.ashinetech.bharatration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ragavendran on 22-Jul-2015.
 */
public class TabProducts extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.product_list_item,container, false);

        // Load the child fragment(TabFragment) into tabrealcontent(Frame Layout for loading entire tabs)
     /*   FragmentManager childmanager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = childmanager.beginTransaction();
        TabFragment tabFragment = new TabFragment();
        fragmentTransaction.add(R.id.tabrealcontent, tabFragment);
        fragmentTransaction.addToBackStack("T");
        fragmentTransaction.commit();
    */
        TabFragment tabFragment = new TabFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.tabrealcontent,tabFragment);

        fragmentTransaction.commit();

        return view;
    }


}
