package com.ashinetech.bharatration;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TabHost;

import com.ashinetech.bharatration.adapter.ProductsAdapter;
import com.ashinetech.bharatration.constants.Constants;
import com.ashinetech.bharatration.model.Brand;
import com.ashinetech.bharatration.model.Product;
import com.ashinetech.bharatration.model.ProductDetail;
import com.ashinetech.bharatration.service.RestfulService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
/**
 * Created by ragavendran on 10-07-2015.
 */
public class Products extends Fragment
{
    private List<Product> products = new ArrayList<Product>();
    private List<ProductDetail> productDetails = new ArrayList<ProductDetail>();

    private int startIndex = 0;
    private final static int limit  = 10;
    String mdata;
    ProgressDialog progressDialog;
    private FragmentTabHost mTabHost;

    String[] productlist = new String[]{ "Tata Salt" , "Anna Poorna iodised" };

    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // loads the main list view(has no child views inside it)
        View view = inflater.inflate(R.layout.product_listview,container, false);


        //code to load Tab Products, which loads the tab content from TabFragment class

        //Error:java.lang.IllegalArgumentException: No view found for tabrealcontent

        // The below code is wrong because the tab layout is already set in TabProducts so we need to
        // call the TabProducts class and set the tab data using ProductsAdapter class.

       /* FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TabProducts tabFragment = new TabProducts();
        fragmentTransaction.add(R.id.tabrealcontent, tabFragment);
        fragmentTransaction.addToBackStack("T");
        fragmentTransaction.commit();
        */


        // Test code to bind the data for tabs but didn't work

     /*   mTabHost = new FragmentTabHost(getActivity());
        mTabHost = (FragmentTabHost)view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Fragment B"),
                ProductQuantity.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("Fragment C"),
                ProductWeight.class, null);
    */


        //This TabHost is used when this Product class is inherited from android.app.Fragment but now we have changed the
        // to android.support.v4.app.Fragment for backward compatibility so the below code is depreciated


        /*
        TabHost tabHost = (TabHost)view.findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabSpec tab2 = tabHost.newTabSpec("Second Tab");


        tab1.setIndicator("Tab1");
        tab1.setContent((TabHost.TabContentFactory) new ProductWeight());
        tab2.setIndicator("Tab2");
        tab2.setContent((TabHost.TabContentFactory) new ProductWeight());


        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        */
        new GetData(view).execute();

        return view;

    }


    private class GetData extends AsyncTask<String , String , String>
    {
        View view;
        GetData(View view)
        {
            this.view = view;
        }
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        protected String doInBackground(String... arg0) {
            mdata = RestfulService.source(Constants.SERVICE_URL + "?startIndex=" + startIndex + "&limit=" + Constants.INFINITE_SCROLL_BATCH_LIMIT);
            System.out.println( mdata);
            return mdata;
        }

        protected void onPostExecute(String data)
        {
            customJsonParser(data, view);
            if (progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
        }
    }


    public void customJsonParser(String data,View view)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(data); // through string output
            JSONArray jsonArray   = jsonObject.getJSONArray(Constants.ALL_PRODUCTS); // through result
            for(int i = 0 ; i < jsonArray.length() ; i++)
            {
                JSONObject resultJson = jsonArray.getJSONObject(i); //through result array

                JSONObject productJson = resultJson.getJSONObject(Constants.PRODUCT); // through product

                int product_id    = productJson.getInt(Constants.PRODUCT_ID);
                String product_name  = productJson.getString(Constants.PRODUCT_NAME);

                ProductDetail productDetail = new ProductDetail(); //ProductDetail Object
                productDetail.setProduct_id(product_id);
                productDetail.setProduct_name(product_name);

                List<Brand> brands = new ArrayList<>();

                JSONArray brandJson = resultJson.getJSONArray(Constants.BRAND); //through brand array
                for(int j = 0 ; j < brandJson.length() ; j++)
                {
                    JSONObject brandInnerJson = brandJson.getJSONObject(j);
                    JSONObject brandDataJson  = brandInnerJson.getJSONObject(Constants.BRAND_DATA);

                    int brand_id              = brandDataJson.getInt(Constants.BRAND_ID);
                    String brand_name         = brandDataJson.getString(Constants.BRAND_NAME);
                    String brand_image        = brandDataJson.getString(Constants.BRAND_IMAGE);

                    Brand brand = new Brand();
                    brand.setBrand_id(brand_id);
                    brand.setBrand_name(brand_name);
                    brand.setBrand_image(brand_image);

                    brands.add(brand);
                }
                productDetail.setBrand(brands);

                productDetails.add(productDetail);
            }

            ProductsAdapter productsAdapter = new ProductsAdapter(this.getActivity(),productDetails);
            list = (ListView) view.findViewById(R.id.product_listview);
            list.setAdapter(productsAdapter);

             list.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    int threshold = 1;
                    int count = list.getCount();

                    if (scrollState == SCROLL_STATE_IDLE) {
                        if (list.getLastVisiblePosition() >= count
                                - threshold) {
                            // Execute LoadMoreDataTask AsyncTask
                            new LoadMoreDataTask(view).execute();
                        }
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                }
            });
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }



    private class LoadMoreDataTask extends AsyncTask<String , String , String>
    {
        View view;
        LoadMoreDataTask(View view)
        {
            this.view = view;
        }
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading Please Wait...");
            progressDialog.show();
        }

        protected String doInBackground(String... arg0)
        {
            startIndex += Constants.INFINITE_SCROLL_BATCH_LIMIT;
            String url = Constants.SERVICE_URL+"?startIndex="+startIndex+"&limit="+Constants.INFINITE_SCROLL_BATCH_LIMIT;
            mdata = RestfulService.source(url);
            System.out.println("Scroll" + mdata);
            return mdata;
        }

        protected void onPostExecute(String data)
        {
            if(data != null && data != "")
            {
                customJsonParserNew(data,view);
            }
            else
            {
                // Go to error page
            }
            if(progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }

        }

    }


    public void customJsonParserNew(String data,View view)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(data); // through string output
            JSONArray jsonArray   = jsonObject.getJSONArray(Constants.ALL_PRODUCTS); // through result
            for(int i = 0 ; i < jsonArray.length() ; i++)
            {
                JSONObject resultJson = jsonArray.getJSONObject(i); //through result array

                JSONObject productJson = resultJson.getJSONObject(Constants.PRODUCT); // through product

                int product_id    = productJson.getInt(Constants.PRODUCT_ID);
                String product_name  = productJson.getString(Constants.PRODUCT_NAME);

                ProductDetail productDetail = new ProductDetail(); //ProductDetail Object
                productDetail.setProduct_id(product_id);
                productDetail.setProduct_name(product_name);

                List<Brand> brands = new ArrayList<>();

                JSONArray brandJson = resultJson.getJSONArray(Constants.BRAND); //through brand array
                for(int j = 0 ; j < brandJson.length() ; j++)
                {
                    JSONObject brandInnerJson = brandJson.getJSONObject(j);
                    JSONObject brandDataJson  = brandInnerJson.getJSONObject(Constants.BRAND_DATA);

                    int brand_id              = brandDataJson.getInt(Constants.BRAND_ID);
                    String brand_name         = brandDataJson.getString(Constants.BRAND_NAME);
                    String brand_image        = brandDataJson.getString(Constants.BRAND_IMAGE);

                    Brand brand = new Brand();
                    brand.setBrand_id(brand_id);
                    brand.setBrand_name(brand_name);
                    brand.setBrand_image(brand_image);

                    brands.add(brand);
                }
                productDetail.setBrand(brands);

                productDetails.add(productDetail);
            }

            ProductsAdapter productsAdapter = new ProductsAdapter(this.getActivity(),productDetails);
            list = (ListView) view.findViewById(R.id.product_listview);
            int position = list.getLastVisiblePosition();
            list.setAdapter(productsAdapter);
            list.setSelectionFromTop(position, 0);


        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }



}
