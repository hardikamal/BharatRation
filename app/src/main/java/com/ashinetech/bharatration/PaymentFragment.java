package com.ashinetech.bharatration;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh on 25-Jul-2015.
 */
public class PaymentFragment extends Fragment
{
    String mdata;
    ProgressDialog progressDialog;
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
        s.setTotalAmount("3200");
        s.setExtOrderId("745547");


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
        String jsonRequest = gson.toJson(s).toString();


        System.out.println("Values" + s) ;


        new GetData(jsonRequest).execute();
        return  view;
    }

    private class GetData extends AsyncTask<String , String , String>
    {
        String jsonRequest;
        GetData(String jsonRequest)
        {
            this.jsonRequest = jsonRequest;
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
               // return new DefaultHttpClient().execute(httpPost);
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

        protected String doInBackground(String... arg0) {
           /*mdata = RestfulService.source(Constants.PAYMENT_URL + "?purchase="+jsonRequest);
            System.out.println("URL"+RestfulService.source(Constants.PAYMENT_URL + "?purchase="+jsonRequest));
            System.out.println("DATA"+jsonRequest);
            System.out.println("RESS"+mdata);
            return mdata;*/
             mdata = Constants.SERVICE_PAYU;
            HttpResponse jsonresponse = makeRequest(mdata,jsonRequest);
            final int statusCode = jsonresponse.getStatusLine().getStatusCode();
            System.out.println("RESS"+statusCode);
            System.out.println("REQ"+jsonRequest);
            HttpEntity entity = jsonresponse.getEntity();
            try {
                String res = EntityUtils.toString(entity);
                System.out.println("EEE"+res);
                return res;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        protected void onPostExecute(String data)
        {
            if (progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }

        }
    }

}