package com.ashinetech.bharatration.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ashinetech.bharatration.R;
import com.ashinetech.bharatration.TabFragment;
import com.ashinetech.bharatration.model.Brand;
import com.ashinetech.bharatration.model.Product;
import com.ashinetech.bharatration.model.ProductDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ragavendran on 07-07-2015.
 */
public class ProductsAdapter extends ArrayAdapter<ProductDetail>
{
    private List<ProductDetail> productArrayAdapter = null;
    private final Activity context;

    public ProductsAdapter(Activity context, List<ProductDetail> productArrayAdapter)
    {
        super(context, R.layout.product_list_item, productArrayAdapter);
        this.context = context;
        this.productArrayAdapter = productArrayAdapter;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.product_list_item, null, true);

        TextView product_brp = (TextView) rowView.findViewById(R.id.product_brp);
        TextView product_name = (TextView) rowView.findViewById(R.id.product_name);

        Spinner spinner = (Spinner) rowView.findViewById(R.id.product_brand);

        product_name.setText(productArrayAdapter.get(position).getProduct_name());

        List<Brand> brands = productArrayAdapter.get(position).getBrand();

        List<String> brandlist = new ArrayList<String>();
        for(Brand brand : brands) {
            brandlist.add(brand.getBrand_name());
        }



       ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this.context,android.R.layout.simple_spinner_item,brandlist);
       // ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this.context,android.R.layout.simple_spinner_item,productArrayAdapter.get(position).getProduct_names());
       stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(stringArrayAdapter);

        return rowView;
    }


    private void setTabs()
    {
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        if(fragmentActivity != null) {
            TabFragment tabFragment = new TabFragment();
            fragmentActivity.getSupportFragmentManager().beginTransaction().add(R.id.product_weight_qty_container, tabFragment).commit();
            System.out.println("Tabs printed");
        }
    }

    private void setSpinner(View view)
    {
        Spinner spinner = (Spinner) view.findViewById(R.id.product_brand);
        String[] spinnerData = new String[]{"Anna Poorna","Tata Iodised"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this.context,android.R.layout.simple_spinner_item,spinnerData);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(stringArrayAdapter);
    }

}
