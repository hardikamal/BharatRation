package com.ashinetech.bharatration.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ashinetech.bharatration.Products;
import com.ashinetech.bharatration.R;
import com.ashinetech.bharatration.TabFragment;
import com.ashinetech.bharatration.model.InfiniteModel;
import com.ashinetech.bharatration.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ragavendran on 07-07-2015.
 */
public class ProductsAdapter extends ArrayAdapter<Product>
{
    private List<Product> productArrayAdapter = null;
    private final Activity context;
    private int currentVisibleItemCount;
    private int currentScrollState;
    private int currentFirstVisibleItem;
    private boolean isLoading;

    ArrayAdapter<Product> adapter;
    public ProductsAdapter(Activity context, List<Product> productArrayAdapter)
    {
        super(context, R.layout.products_main, productArrayAdapter);
        this.context = context;
        this.productArrayAdapter = productArrayAdapter;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.products_main, null, true);

        setSpinner(rowView);
        setTabs(rowView);

        //TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        //txtTitle.setText(productArrayAdapter.get(position).getTitle());
        return rowView;
    }

    private void setTabs(View view)
    {
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        TabFragment tabFragment = new TabFragment();
        fragmentActivity.getSupportFragmentManager().beginTransaction().add(R.id.item_detail_container,tabFragment).commit();
    }

    private void setSpinner(View view)
    {
        Spinner spinner = (Spinner) view.findViewById(R.id.product_type);
        String[] spinnerData = new String[]{"Anna Poorna","Tata Iodised"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this.context,android.R.layout.simple_spinner_item,spinnerData);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(stringArrayAdapter);
    }

}
