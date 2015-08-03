package com.ashinetech.bharatration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.ashinetech.bharatration.constants.Constants;
import com.ashinetech.bharatration.model.Buyer;
import com.ashinetech.bharatration.model.Delivery;
import com.ashinetech.bharatration.model.Invoice;
import com.ashinetech.bharatration.model.Products;
import com.ashinetech.bharatration.model.Purchase;
import com.ashinetech.bharatration.service.RestfulService;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vignesh on 25-Jul-2015.
 */
public class PaymentFragment extends Fragment
{
    String mdata;
    ProgressDialog progressDialog;
    private WebView browser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.cart,container, false);
        Purchase s =new Purchase();
        s.setNotifyUrl("http://localhost/PAYU/OrderNotify.php");
        s.setContinueUrl("http://localhost/PAYU/../../layout/success.php");
        s.setCustomerIp("127.0.0.1");
        s.setMerchantPosId("145227");
        s.setDescription("New order");
        s.setCurrencyCode("PLN");
        s.setTotalAmount("1873");
        s.setExtOrderId("907");

        Products products=new Products();
        products.setName("Spice");
        products.setUnitPrice(30);
        products.setQuantity(6);

        Products products1=new Products();
        products1.setName("Pepper");
        products1.setUnitPrice(310);
        products1.setQuantity(6);

        Invoice invoice = new Invoice();
        invoice.setRecipientName("Harbhajan");
        invoice.setRecipientEmail("bajji@gmail.com");
        invoice.setRecipientPhone("48456456789");
        invoice.setName("The very first invoice");
        invoice.setStreet("Foo St. 155");
        invoice.setPostalBox("Warsaw");
        invoice.setPostalCode("222");
        invoice.setCity("Warsaw");
        invoice.setCountryCode("PL");
        invoice.setTin("8252212616");

        Delivery delivery =new Delivery();
        delivery.setRecipientName("Shikar");
        delivery.setRecipientEmail("shikar@gmail.com");
        delivery.setRecipientPhone("99445539059");
        delivery.setStreet("No 225");
        delivery.setPostalBox("Warsaw");
        delivery.setPostalCode("222");
        delivery.setCity("Warsaw");
        delivery.setState("Masovian district");
        delivery.setCountryCode("PL");

        Buyer buyer = new Buyer();
        buyer.setEmail("dd@gmail.com");
        buyer.setPhone("888595958");
        buyer.setFirstName("Shane");
        buyer.setLastName("Watson");
        buyer.setInvoice(invoice);
        buyer.setDelivery(delivery);

        List<Products> p =  new ArrayList<Products>();
        p.add(products);
        p.add(products1);

        s.setProducts(p);
        s.setBuyer(buyer);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(s);
        System.out.println("Values" + s.toString()) ;
        new GetData(jsonRequest,view).execute();


        return  view;
    }

    private class GetData extends AsyncTask<String , String , String>
    {
        String jsonRequest;
        String text;
        View view;
        GetData(String jsonRequest,View view)
        {
            this.jsonRequest = jsonRequest;
            this.view = view;
        }

        public HttpResponse makeRequest(String uri, String json) {
            try {
                HttpPost httpPost = new HttpPost(uri);
                httpPost.setEntity(new StringEntity(json));
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                DefaultHttpClient client = new DefaultHttpClient();
                HttpResponse httpResponse = client.execute(httpPost);
                return httpResponse;
               //  return new DefaultHttpClient().execute(httpPost);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0)
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Constants.PAYMENT_URL);
            JSONObject  jsonObject = null;
            String test = "ddd";

            try
            {
                StringEntity s = new StringEntity("jsonpost="+jsonRequest.toString());
                httppost.addHeader("content-type", "application/x-www-form-urlencoded");
                httppost.setEntity(s);
                HttpResponse response;
                response = httpclient.execute(httppost);
                String resFromServer = org.apache.http.util.EntityUtils.toString(response.getEntity());

                jsonObject = new JSONObject(resFromServer);
                String status = jsonObject.getString("status");
                String url = jsonObject.getString("url");
                System.out.println("URL"+url);
                /*
                if(status == "success")
                {
                    url = jsonObject.getString("url");
                    System.out.println("URL"+url);

                }
                else
                {
                    url = "empty";
                }
                */

                System.out.println("STATUS" + status);
                Log.i("Response from server", jsonObject.toString());
                return url;

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return test;

        }

        @Override
        protected void onPostExecute(String data)
        {
            System.out.println("DATADATA"+data);


            if (progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }

           // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
           // startActivity(browserIntent);
            WebView myWebView = (WebView) view.findViewById(R.id.webview);
            myWebView.loadUrl(data);
        }
    }

    private class WebBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
