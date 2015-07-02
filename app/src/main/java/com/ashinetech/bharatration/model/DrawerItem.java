package com.ashinetech.bharatration.model;

/**
 * Created by ragavendran on 02-07-2015.
 */
public class DrawerItem
{
    String ItemName;
    int imgResID;
    String title;

    public DrawerItem(String itemName, int imgResID) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;


    }

    public DrawerItem(String title)
    {
        this.title = title;
    }
    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public int getImgResID() {
        return imgResID;
    }
    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
