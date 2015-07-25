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
        s.setExtOrderId("277609");


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
        System.out.println("Values" + s);

        new GetData(s).execute();
        return  view;
    }

    private class GetData extends AsyncTask<String , String , String>
    {
        Purchase p;
        GetData(Purchase p)
        {
            this.p = p;
        }
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        protected String doInBackground(String... arg0) {
            mdata = RestfulService.source(Constants.PAYMENT_URL + "?purchase=" +p );
            System.out.println( mdata);
            return mdata;
        }

        protected void onPostExecute(String data)
        {
            if (progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
            System.out.println("Ragav"+data);
        }
    }

}
