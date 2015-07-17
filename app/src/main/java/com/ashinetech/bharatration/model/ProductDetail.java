package com.ashinetech.bharatration.model;

import java.util.List;

/**
 * Created by ragavendran on 17-Jul-2015.
 */
public class ProductDetail
{
    private int product_id;
    private String product_name; //salt
    private String product_brp; //Rs 50
    private List<Brand> brand;

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }



    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_brp() {
        return product_brp;
    }

    public void setProduct_brp(String product_brp) {
        this.product_brp = product_brp;
    }



    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
