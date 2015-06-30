package com.ashinetech.bharatration.model;

import java.util.ArrayList;

/**
 * Created by Vignesh on 30-06-2015.
 */
public class Heading
{
    private  String title;
    private ArrayList<Content> contents;

    public ArrayList<Content> getContents() {
        return contents;
    }

    public void setContents(ArrayList<Content> contents) {
        this.contents = contents;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
