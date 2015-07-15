package com.ashinetech.bharatration;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ashinetech.bharatration.adapter.ProductsAdapter;
import com.ashinetech.bharatration.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ragavendran on 10-07-2015.
 */
public class Products extends android.app.Fragment
{
    private List<Product> products = new ArrayList<Product>();
    String[] productlist = new String[]{ "Tata Salt" , "Anna Poorna iodised" };
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.product_listview,container, false);


        for(int i = 0 ; i < 100 ; i++)
        {
            Product product = new Product();
            product.setCategory_name("Salt");
            product.setBrp("Rs 100");
            product.setProduct_names(productlist);
            products.add(product);
        }

        ProductsAdapter productsAdapter = new ProductsAdapter(this.getActivity(),products);
        list = (ListView) view.findViewById(R.id.product_listview);
        list.setAdapter(productsAdapter);
        //((ListView)view).setAdapter(productsAdapter);
        return view;

    }
}
