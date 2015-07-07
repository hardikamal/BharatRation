package com.ashinetech.bharatration.model;

/**
 * Created by ragavendran on 02-07-2015.
 */
public class NavigationDrawerModel
{
    String ItemName;
    int imgResID;
    String title;
    int logo;

    public NavigationDrawerModel(int logo)
    {
        this.logo=logo;
    }

    public NavigationDrawerModel(String itemName, int imgResID) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;
    }

    public NavigationDrawerModel(String title)
    {
        this.title = title;
    }

    public int getLogo() {
        return logo;
    }
    public void setLogo(int logo) {
        this.logo = logo;
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
