package com.ashinetech.bharatration.constants;

/**
 * Created by Shinelin on 7/2/2015.
 */
public class Constants {

    //public static final String SERVICE_URL = "http://"+ EnvironmentConstants.CLOUD_URL+"/bharatration/index.php";

    public static final String SERVICE_URL = "http://cloudservices.ashinetech.com/bharatration/index.php";

    public static  final String SERVICE_MAIN_URL = "http://cloudservices.ashinetech.com/bharatration";

    public static  final String SERVICE_LOGIN = SERVICE_MAIN_URL+"/login.php";

    public static final  String PAYMENT_URL="http://cloudservices.ashinetech.com/bharatration/PAYU/createorder.php";

    public static final String SERVICE_PAYU = "https://secure.payu.com/api/v2_1/orders";

    public static final int INFINITE_SCROLL_BATCH_LIMIT = 5;

    public static final String MENU_HOME = "Home";

    public static final int SPLASH_TIME_OUT = 1000;

    /* JSON Constants starts*/

    public static final String ALL_PRODUCTS = "Result";
    public static final String PRODUCT      = "Product";
    public static final String PRODUCT_ID   = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String BRAND        = "Brand";
    public static final String BRAND_DATA   = "BrandData";
    public static final String BRAND_ID     = "brand_id";
    public static final String BRAND_IMAGE  = "brand_img";
    public static final String BRAND_NAME   = "brand_name";


    /* JSON Constants ends */
}
