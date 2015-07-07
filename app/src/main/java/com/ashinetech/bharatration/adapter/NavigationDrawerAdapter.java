package com.ashinetech.bharatration.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashinetech.bharatration.R;
import com.ashinetech.bharatration.model.NavigationDrawerModel;

import java.util.List;

/**
 * Created by ragavendran on 02-07-2015.
 */
public class NavigationDrawerAdapter extends ArrayAdapter<NavigationDrawerModel>
{
    Context context;
    List<NavigationDrawerModel> navigationDrawerModelList;
    int layoutResID;

    public NavigationDrawerAdapter(Context context, int layoutResID, List<NavigationDrawerModel> navigationDrawerModels) {
        super(context, layoutResID, navigationDrawerModels);
        this.context = context;
        this.navigationDrawerModelList = navigationDrawerModels;
        this.layoutResID = layoutResID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.ItemName = (TextView) view
                    .findViewById(R.id.drawer_itemName);
            drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

            drawerHolder.logo=(ImageView)view.findViewById(R.id.logo);

            drawerHolder.title = (TextView) view.findViewById(R.id.drawerTitle);

            drawerHolder.headerLayout = (LinearLayout) view
                    .findViewById(R.id.headerLayout);
            drawerHolder.itemLayout = (LinearLayout) view
                    .findViewById(R.id.itemLayout);
            drawerHolder.imageHeader = (LinearLayout) view
                    .findViewById(R.id.image_header);


            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();

        }

        NavigationDrawerModel dItem = (NavigationDrawerModel) this.navigationDrawerModelList.get(position);


         if (dItem.getTitle() != null) {
             /*drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
             drawerHolder.imageHeader.setVisibility(LinearLayout.INVISIBLE);*/
             drawerHolder.headerLayout.setVisibility(LinearLayout.VISIBLE);

             View _itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);
             if(_itemLayout != null) ((LinearLayout)_itemLayout.getParent()).removeView(_itemLayout);
             View _imageHeader = (LinearLayout) view.findViewById(R.id.image_header);
             if(_imageHeader != null) ((LinearLayout)_imageHeader.getParent()).removeView(_imageHeader);

             drawerHolder.title.setText(dItem.getTitle());

        } else {
             //drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
             View _headerLayout = (LinearLayout) view.findViewById(R.id.headerLayout);
             if(_headerLayout != null) ((LinearLayout)_headerLayout.getParent()).removeView(_headerLayout);


             if(dItem.getImgResID() != 0) {
                 //drawerHolder.imageHeader.setVisibility(LinearLayout.INVISIBLE);
                 View _imageHeader = (LinearLayout) view.findViewById(R.id.image_header);
                 if(_imageHeader != null) ((LinearLayout)_imageHeader.getParent()).removeView(_imageHeader);

                 drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);
                 drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));
             }
             if(dItem.getLogo()!=0) {
                 //drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
                 View _itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);
                 if(_itemLayout != null) ((LinearLayout)_itemLayout.getParent()).removeView(_itemLayout);

                 drawerHolder.imageHeader.setVisibility(LinearLayout.VISIBLE);
                 drawerHolder.logo.setImageDrawable(view.getResources().getDrawable(dItem.getLogo()));
             }
         drawerHolder.ItemName.setText(dItem.getItemName());

        }
        return view;
    }

    private static class DrawerItemHolder
    {
        TextView ItemName, title;
        ImageView icon,logo;
        LinearLayout headerLayout, itemLayout,imageHeader;
    }
}
