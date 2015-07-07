package com.ashinetech.bharatration;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ashinetech.bharatration.adapter.InfiniteScrollListAdapter;
import com.ashinetech.bharatration.adapter.NavigationDrawerAdapter;
import com.ashinetech.bharatration.model.Content;
import com.ashinetech.bharatration.model.NavigationDrawerModel;
import com.ashinetech.bharatration.model.InfiniteModel;
import com.ashinetech.bharatration.constants.Constants;
import com.ashinetech.bharatration.service.FragmentOne;
import com.ashinetech.bharatration.service.FragmentThree;
import com.ashinetech.bharatration.service.FragmentTwo;
import com.ashinetech.bharatration.service.RestfulService;


public class MainActivity extends Activity
{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    AlertDialog alertDialogStores;
    private HashMap<String, ArrayList<Content>> stringContentHashMap = new HashMap<>();
    private int startIndex = 0;
    private final static int limit  = 10;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    NavigationDrawerAdapter adapter;

    List<NavigationDrawerModel> dataList;

    private CharSequence mTitle;
    Context context;
    ProgressDialog progressDialog;
    String mdata;
    ArrayList<InfiniteModel> modelClasses = new ArrayList<InfiniteModel>();
    android.app.ActionBar actionBar;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);


        /* Ragav Code Starts */
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //new GetData().execute();
        /* Ragav Code Ends */

        /* Navigation Drawer Starts */
        dataList = new ArrayList<NavigationDrawerModel>();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        dataList.add(new NavigationDrawerModel(R.drawable.logoheader));

        dataList.add(new NavigationDrawerModel("General"));
        dataList.add(new NavigationDrawerModel("Home", R.drawable.ic_home));
        dataList.add(new NavigationDrawerModel("About US", R.drawable.ic_people));
        dataList.add(new NavigationDrawerModel("Delievery Policy", R.drawable.ic_whats_hot));

        dataList.add(new NavigationDrawerModel("Shop"));
        dataList.add(new NavigationDrawerModel("Essentails", R.drawable.ic_communities));
        dataList.add(new NavigationDrawerModel("Kids", R.drawable.ic_photos));

        adapter = new NavigationDrawerAdapter(this, R.layout.navigation_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        actionBar = getActionBar();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(false);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open,R.string.drawer_close)
        {
            public void onDrawerClosed(View view)
            {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView)
            {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null)
        {
                if(dataList.get(1).getTitle() != null) {
                SelectItem(2);
            } else if (dataList.get(0).getTitle() != null) {
                SelectItem(1);
            } else {
                SelectItem(0);
            }
        }


    }

    /* Functions for naigational drawer starts */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void SelectItem(int possition) {

        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            case 0:
                fragment = new FragmentOne();

                break;
            case 1:
                fragment = new FragmentTwo();
                args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 2:
                fragment = new FragmentThree();
                args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 3:
                fragment = new FragmentOne();

                break;
            case 4:
                fragment = new FragmentTwo();
                args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 5:
                fragment = new FragmentThree();
                args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 6:
                fragment = new FragmentOne();

                break;
            case 7:
                fragment = new FragmentTwo();
                args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 8:
                fragment = new FragmentThree();
                args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 9:
                fragment = new FragmentOne();

                break;
            case 10:
                fragment = new FragmentTwo();
                args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 11:
                fragment = new FragmentThree();
                args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 12:
                fragment = new FragmentOne();

                break;
            default:
                break;
        }

        fragment.setArguments(args);
        android.app.FragmentManager frgManager = getFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        mDrawerList.setItemChecked(possition, true);
//        setTitle(dataList.get(possition).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    /* Function for navigational drawer ends */





    private class GetData extends AsyncTask<String , String , String>
    {
        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading Please Wait...");
            progressDialog.show();
        }

        protected String doInBackground(String... arg0)
        {
            mdata = RestfulService.source(Constants.SERVICE_URL+"?startIndex="+startIndex+"&limit="+Constants.INFINITE_SCROLL_BATCH_LIMIT);
            System.out.println("DTDTTD"+mdata);
            return mdata;
        }

        protected void onPostExecute(String data)
        {
            if(progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
            processJson(data);
        }

        public void processJson(String data)
        {
            try
            {
                JSONArray jsonArray = new JSONArray(data);
                for(int i = 0 ; i < jsonArray.length() ; i++)
                {
                    InfiniteModel modelClass = new InfiniteModel();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    modelClass.setTitle(title);
                    modelClasses.add(modelClass);
                }


                InfiniteScrollListAdapter infiniteScrollListAdapter = new InfiniteScrollListAdapter(MainActivity.this,modelClasses);
                list  = (ListView) findViewById(R.id.list);
                list.setAdapter(infiniteScrollListAdapter);

                list.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        int threshold = 1;
                        int count = list.getCount();

                        if (scrollState == SCROLL_STATE_IDLE) {
                            if (list.getLastVisiblePosition() >= count
                                    - threshold) {
                                // Execute LoadMoreDataTask AsyncTask
                                new LoadMoreDataTask().execute();
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
    }


    private class LoadMoreDataTask extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(MainActivity.this);
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
                processJson(data);
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

        public void processJson(String data)
        {
            try
            {
                JSONArray jsonArray = new JSONArray(data);
                for(int i = 0 ; i < jsonArray.length() ; i++)
                {
                    InfiniteModel modelClass = new InfiniteModel();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    modelClass.setTitle(title);
                    modelClasses.add(modelClass);
                }


                InfiniteScrollListAdapter infiniteScrollListAdapter = new InfiniteScrollListAdapter(MainActivity.this,modelClasses);
                list  = (ListView) findViewById(R.id.list);
                int position = list.getLastVisiblePosition();
                list.setAdapter(infiniteScrollListAdapter);
                list.setSelectionFromTop(position, 0);


               }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            SelectItem(position);
        }
    }

}
