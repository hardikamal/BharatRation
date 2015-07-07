package com.ashinetech.bharatration.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ashinetech.bharatration.R;

import java.util.ArrayList;

import com.ashinetech.bharatration.model.InfiniteModel;

/**
 * Created by ragavendran on 30-06-2015.
 */
public class InfiniteScrollListAdapter extends ArrayAdapter<InfiniteModel>
{
    private ArrayList<InfiniteModel> infiniteModelArrayAdapter = null;
    private final Activity context;
    private int currentVisibleItemCount;
    private int currentScrollState;
    private int currentFirstVisibleItem;
    private boolean isLoading;

    ArrayAdapter<InfiniteModel> adapter;
    public InfiniteScrollListAdapter(Activity context, ArrayList<InfiniteModel> infiniteModelArrayAdapter)
    {
        super(context, R.layout.list_single,infiniteModelArrayAdapter);
        this.context = context;
        this.infiniteModelArrayAdapter = infiniteModelArrayAdapter;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
          LayoutInflater inflater = context.getLayoutInflater();
          View rowView= inflater.inflate(R.layout.list_single, null, true);
          TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
          txtTitle.setText(infiniteModelArrayAdapter.get(position).getTitle());
          return rowView;
    }
}
