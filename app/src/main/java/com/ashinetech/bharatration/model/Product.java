package com.ashinetech.bharatration.model;

/**
 * Created by Shinelin on 7/10/2015.
 */
public class Product
{
    private int product_logo;
    private String category_name;
    private String brp;
    private String[] product_names;

    public int getProduct_logo() {
        return product_logo;
    }

    public void setProduct_logo(int product_logo) {
        this.product_logo = product_logo;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getBrp() {
        return brp;
    }

    public void setBrp(String brp) {
        this.brp = brp;
    }

    public String[] getProduct_names() {
        return product_names;
    }

    public void setProduct_names(String[] product_names) {
        this.product_names = product_names;
    }
}
