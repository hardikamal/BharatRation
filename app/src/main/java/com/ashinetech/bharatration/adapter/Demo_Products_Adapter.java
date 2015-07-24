package com.ashinetech.bharatration.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ArrayAdapter;

import com.ashinetech.bharatration.R;
import com.ashinetech.bharatration.model.ProductDetail;

import java.util.List;

/**
 * Created by ragavendran on 24-Jul-2015.
 */
public class Demo_Products_Adapter extends ArrayAdapter<ProductDetail>
{
    private List<ProductDetail> productArrayAdapter = null;
    private final Activity context;
    Bitmap bitmap;

    public Demo_Products_Adapter(Activity context, List<ProductDetail> productArrayAdapter)
    {
        super(context, R.layout.product_list_item, productArrayAdapter);
        this.context = context;
        this.productArrayAdapter = productArrayAdapter;
    }
}
