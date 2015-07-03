package com.ashinetech.bharatration;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AbsListView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ashinetech.bharatration.adapter.CustomList;
import com.ashinetech.bharatration.adapter.Custom_Drawer_Adapter;
import com.ashinetech.bharatration.adapter.MyList;
import com.ashinetech.bharatration.model.Content;
import com.ashinetech.bharatration.model.DrawerItem;
import com.ashinetech.bharatration.model.Heading;
import com.ashinetech.bharatration.model.InfiniteModel;
import com.ashinetech.bharatration.constants.Constants;
import com.ashinetech.bharatration.service.RestfulService;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks
{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    AlertDialog alertDialogStores;
    private HashMap<String, ArrayList<Content>> stringContentHashMap = new HashMap<>();
    private int startIndex = 0;
    private final static int limit  = 10;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    Custom_Drawer_Adapter adapter;

    List<DrawerItem> dataList;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    Context context;
    ProgressDialog progressDialog;
    String mdata;
    ArrayList<InfiniteModel> modelClasses = new ArrayList<InfiniteModel>();
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Ragav Code Starts */
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
       // new GetData().execute();
        /* Ragav Code Ends */

        /* Navigation Drawer Starts */
        dataList = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        dataList.add(new DrawerItem("General"));
        dataList.add(new DrawerItem("Home", R.drawable.ic_home));
        dataList.add(new DrawerItem("About US", R.drawable.ic_people));
        dataList.add(new DrawerItem("Delievery Policy", R.drawable.ic_whats_hot));

        dataList.add(new DrawerItem("Shop"));
        dataList.add(new DrawerItem("Essentails", R.drawable.ic_communities));
        dataList.add(new DrawerItem("Kids", R.drawable.ic_photos));

        if (savedInstanceState == null) {

            if (dataList.get(0).getTitle() != null) {
                SelectItem(1);
            } else {
                SelectItem(0);
            }
        }


        adapter = new Custom_Drawer_Adapter(this, R.layout.custom_list,
                dataList);

        mDrawerList.setAdapter(adapter);
       // mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //        getActionBar().setDisplayHomeAsUpEnabled(true);
        //       getActionBar().setHomeButtonEnabled(true);



        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            SelectItem(0);
        }
        /* Navigation Drawer Ends  */



        /* Vignesh Code Starts */
     /*   View.OnClickListener handler = new View.OnClickListener(){

            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.buttonShowPopUp:
                        showPopUp();
                        break;

                }

            }
        };
        findViewById(R.id.buttonShowPopUp).setOnClickListener(handler); */
        /* Vignesh Code Ends  */

     /*   mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));*/
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void SelectItem(int possition) {

        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            default:
                break;
        }

        mDrawerList.setItemChecked(possition, true);
        setTitle(dataList.get(possition).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

    }


    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        //   mDrawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       /* if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();

            return true;
        }
        return super.onCreateOptionsMenu(menu);
        */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showPopUp()
    {

        /* Custom Code  Starts*/
        String myjson = "{\n" +
                "    \"Result\": [\n" +
                "        {\n" +
                "            \"heading\": {\n" +
                "                \"title\": \"general\"\n" +
                "            },\n" +
                "            \"listcontent\": [\n" +
                "                {\n" +
                "                    \"icon\": \"essential\",\n" +
                "                    \"title\": \"Essential\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"icon\": \"men\",\n" +
                "                    \"title\": \"Men\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"icon\": \"women\",\n" +
                "                    \"title\": \"Women\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"icon\": \"others\",\n" +
                "                    \"title\": \"Others\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"heading\": {\n" +
                "                \"title\": \"demo\"\n" +
                "            },\n" +
                "            \"listcontent\": [\n" +
                "                {\n" +
                "                    \"icon\": \"home\",\n" +
                "                    \"title\": \"Home\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"icon\": \"about\",\n" +
                "                    \"title\": \"About Us\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"icon\": \"policy\",\n" +
                "                    \"title\": \"Delievery Policy\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"icon\": \"contact\",\n" +
                "                    \"title\": \"Contact Us\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "} ";

        ArrayList<Heading> headings = new ArrayList<Heading>();

        try
        {
            JSONObject initialdata = new JSONObject(myjson);
            JSONArray result = initialdata.getJSONArray("Result");

            for(int i=0;i<result.length();i++) {

                JSONObject jobj = result.getJSONObject(i);
                JSONObject heading = jobj.getJSONObject("heading");//for heading pojo
                Heading h = new Heading();
                h.setTitle(heading.getString("title"));
                JSONArray listcontent = jobj.getJSONArray("listcontent");

                ArrayList<Content> contents = new ArrayList<Content>();
                for (int j = 0; j < listcontent.length(); j++)
                {
                    JSONObject temp = listcontent.getJSONObject(j);
                    Content c = new Content();
                    c.setTitle(temp.getString("title"));
                    c.setIcon(temp.getString("icon"));
                    contents.add(c);
                    System.out.println("vign" + c.toString());
                }
                h.setContents(contents);
                stringContentHashMap.put(h.getTitle(),h.getContents());
                headings.add(h);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        MyList myList = new MyList(this,R.layout.navigation_drawer_layout,headings);
        ListView listViewItems = new ListView(this);
        listViewItems.setAdapter(myList);

        System.out.println("VVV"+headings.toString());
       /* Custom Code Ends */

        alertDialogStores = new AlertDialog.Builder(MainActivity.this)
                .setView(listViewItems)
                .setTitle("Stores")
                .show();
    }


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


                CustomList customList = new CustomList(MainActivity.this,modelClasses);
                list  = (ListView) findViewById(R.id.list);
                list.setAdapter(customList);

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


                CustomList customList = new CustomList(MainActivity.this,modelClasses);
                list  = (ListView) findViewById(R.id.list);
                int position = list.getLastVisiblePosition();
                list.setAdapter(customList);
                list.setSelectionFromTop(position, 0);


               }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
