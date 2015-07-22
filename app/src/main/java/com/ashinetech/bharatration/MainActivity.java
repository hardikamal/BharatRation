package com.ashinetech.bharatration;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.SearchRecentSuggestions;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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


public class MainActivity extends FragmentActivity
{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */

    private static final String[] COUNTRIES = new String[] { "Essentials","Women","Kids and Men","Others",
       };

    AlertDialog alertDialogStores;
    private HashMap<String, ArrayList<Content>> stringContentHashMap = new HashMap<>();
    private int startIndex = 0;
    private final static int limit  = 10;

    LayoutInflater mInflater;
    View mCustomView;

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
    private Handler mHandler = new Handler();
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        dataList.add(new NavigationDrawerModel("About Us", R.drawable.ic_people));
        dataList.add(new NavigationDrawerModel("Frequently Asked Questions", R.drawable.ic_whats_hot));

        dataList.add(new NavigationDrawerModel("Shop"));
        dataList.add(new NavigationDrawerModel("Essentials", R.drawable.ic_communities));
        dataList.add(new NavigationDrawerModel("Women",R.drawable.ic_people));
        dataList.add(new NavigationDrawerModel("Kids and Men", R.drawable.ic_photos));
        dataList.add(new NavigationDrawerModel("Others",R.drawable.ic_whats_hot));

        adapter = new NavigationDrawerAdapter(this, R.layout.navigation_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        actionBar = getActionBar();

        mInflater = LayoutInflater.from(this);
        mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        actionBar.setDisplayShowCustomEnabled(true);

        customActionBar("Home");



        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

        /* vignesh code starts */
        Intent intent  = getIntent();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    SuggestionProvider.AUTHORITY, SuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
        }

        /* vignesh code ends */


         /* vignesh custom Autocomplete code starts */
        /*
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_actionbar, null);

        actionBar.setCustomView(v);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        AutoCompleteTextView textView = (AutoCompleteTextView) v
                .findViewById(R.id.editText1);
        textView.setAdapter(adapter);
        final Intent intent1 =new Intent(this,SearchResults.class);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.editText1);
        autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((keyCode==KeyEvent.KEYCODE_ENTER))
                {
                    System.out.println("hello hello");

                    intent1.putExtra("Search",autoCompleteTextView.getText().toString());
                    startActivity(intent1);
                }
                return false;
            }
        });
*/
        /*vignesh custom  Autocomplete code ends */



        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open,R.string.drawer_close)
        {
            public void onDrawerClosed(View view)
            {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView)
            {
                getActionBar().setTitle(mTitle);
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

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


    private  void customActionBar(String actionbartitle) {

        final String title = actionbartitle;
        final TextView textView = (TextView) mCustomView.findViewById(R.id.text2);
        textView.setText(title);

        final ImageView search = (ImageView) mCustomView.findViewById(R.id.img_search);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, COUNTRIES);
        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                textView.setText("");
                Toast.makeText(getApplicationContext(), "You Clicked search", Toast.LENGTH_LONG).show();
                final AutoCompleteTextView atv = (AutoCompleteTextView) mCustomView.findViewById(R.id.editText1);
                atv.setVisibility(View.VISIBLE);
                atv.setAdapter(adapter1);
                atv.setFocusable(true);
                atv.setEms(14);
                atv.showDropDown();
                search.setVisibility(View.GONE);
                final ImageView close = (ImageView) mCustomView.findViewById(R.id.img_close);
                close.setVisibility(View.VISIBLE);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText(title);
                        close.setVisibility(View.INVISIBLE);
                        search.setVisibility(View.VISIBLE);
                        atv.setVisibility(View.INVISIBLE);

                    }
                });

            }
        });
        actionBar.setCustomView(mCustomView);
        actionBar.setDisplayShowCustomEnabled(true);

        final Intent intent1 = new Intent(this, SearchResults.class);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.editText1);
        autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    System.out.println("hello hello");

                    intent1.putExtra("Search", autoCompleteTextView.getText().toString());
                    actionBar.setTitle(autoCompleteTextView.getText().toString());
                    actionBar.setDisplayShowTitleEnabled(true);
                    startActivity(intent1);
                }
                return false;
            }
        });
    }

         /* vignesh code for search toggle ends */

    public void SelectItem(int position)
    {

        System.out.println("Position:" + position);
        android.support.v4.app.Fragment fragment = null;
        Bundle args = new Bundle();
        switch (position) {
            case 0:
                return;
            case 1:
                return;
            case 2:
                fragment = new Aboutus();

                break;
            case 3:
                fragment = new Aboutus();

                break;
            case 4:
                fragment = new DeliveryPolicy();

                break;
            case 5:
                return;
                /*fragment = new FragmentThree();
                args.putString(FragmentThree.ITEM_NAME, dataList.get(position)
                        .getItemName());
                args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(position)
                        .getImgResID());
                break;*/
            case 6:
               fragment = new Products();

                break;
            case 7:
                fragment = new TabProducts();

                break;
            case 8:
                fragment = new Products();

                break;
            case 9:
                fragment = new Products();

                break;

            default:
                break;
        }

        fragment.setArguments(args);
        FragmentManager frgManager = getSupportFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        mDrawerList.setItemChecked(position, true);

        customActionBar(dataList.get(position).getItemName());

        if(dataList.get(position).getItemName() == Constants.MENU_HOME)
        {
            String data = "Bharat Ration";
            customActionBarItem(data);
            getActionBar().setDisplayShowHomeEnabled(false);

        }
        else
        {
            String data;
            if (dataList.get(position).getItemName() == Constants.MENU_HOME) {
                getActionBar().setDisplayShowHomeEnabled(false);
                getActionBar().setDisplayShowTitleEnabled(false);
                data="";
                //customActionBarItem(data);
            }
            else {
                data = dataList.get(position).getItemName();
              //  customActionBarItem(data);
                getActionBar().setDisplayShowHomeEnabled(false);
                getActionBar().setDisplayShowTitleEnabled(false);
            }
        }
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    private void customActionBarItem(String data) {

     //  mInflater = LayoutInflater.from(this);
     //   mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
     //   TextView tvs = (TextView)mCustomView.findViewById(R.id.text2);
      //  tvs.setText(data);


//        ImageView  shoppingIcon =(ImageView)mCustomView.findViewById(R.id.imageView1);
//        ImageView  searchIcon   =(ImageView)mCustomView.findViewById(R.id.imageView2);
//
//        shoppingIcon.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "You Clicked Shopping",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
//
//        searchIcon.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "You Clicked Search",
//                        Toast.LENGTH_LONG).show();
//            }
//        });

        actionBar.setCustomView(mCustomView);
        actionBar.setDisplayShowCustomEnabled(true);

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
    public boolean onCreateOptionsMenu(Menu menu)
    {
       /* MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bharatration, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);

     */
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.

        super.onOptionsItemSelected(item);
       /*
        switch(item.getItemId()){
            case R.id.shopping:
                Toast.makeText(getBaseContext(), "You selected shopping", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        */
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
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
