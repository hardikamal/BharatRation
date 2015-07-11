package com.ashinetech.bharatration;


import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.ashinetech.bharatration.adapter.NavigationDrawerAdapter;
import com.ashinetech.bharatration.adapter.ProductsAdapter;
import com.ashinetech.bharatration.model.NavigationDrawerModel;
import com.ashinetech.bharatration.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ragavendran on 10-07-2015.
 */
public class Products extends android.app.Fragment
{
    private FragmentTabHost mTabHost;
    private ListView productListView;
    List<Product> productList;
    private ProductsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        /*View rootView = inflater.inflate(R.layout.fragment_productlist, container,false);
        productList = new ArrayList<Product>();

        productListView = (ListView) rootView.findViewById(R.id.product_listview);

        productList.add(new Product());

        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());

        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());

        adapter = new ProductsAdapter(getActivity(), productList);

        productListView.setAdapter(adapter);

        setSpinner(productListView);
        setTabs(productListView);
        return productListView;*/

        View view = inflater.inflate(R.layout.products_main, container,false);
        setSpinner(view);
        setTabs(view);
        return view;
    }

    private void setTabs(View view)
    {
        FragmentActivity fragmentActivity = (FragmentActivity) getActivity();
        TabFragment tabFragment = new TabFragment();
        fragmentActivity.getSupportFragmentManager().beginTransaction().add(R.id.item_detail_container,tabFragment).commit();
    }

    private void setSpinner(View view)
    {
        Spinner spinner = (Spinner) view.findViewById(R.id.product_type);
        String[] spinnerData = new String[]{"Anna Poorna","Tata Iodised"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,spinnerData);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(stringArrayAdapter);
    }

}
