package com.ashinetech.bharatration;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ashinetech.bharatration.adapter.Demo_Products_Adapter;

import java.util.List;

/**
 * Created by ragavendran on 24-Jul-2015.
 */
public class DemoProducts extends Fragment
{
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.demo_products_listview,container,false);
        String[] spinnerdata = {"one","two","three"};

     /*   Demo_Products_Adapter adapter = new Demo_Products_Adapter(this.getActivity(),spinnerdata);
        list = (ListView) view.findViewById(R.id.demo_products_list);
        list.setAdapter(adapter);
*/

        return view;
    }

}
