package com.ashinetech.bharatration;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ashinetech.bharatration.constants.Constants;
import com.ashinetech.bharatration.model.User;
import com.ashinetech.bharatration.service.RestfulService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ragavendran on 23-Jul-2015.
 */
public class login extends Fragment
{
    ProgressDialog progressDialog;

    List<User> userList ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.login,container, false);
        EditText user_mobile   = (EditText) view.findViewById(R.id.user_mobile);
        EditText user_password = (EditText) view.findViewById(R.id.user_password);

        User user = new User();
        user.setUser_mobile(user_mobile.getText().toString());
        user.setUser_password(user_password.getText().toString());
        userList = new ArrayList<>();
        userList.add(user);
        new GetData(userList).execute();

        return  view;
    }

    private class GetData extends AsyncTask<String , String , String>
    {
        List<User> userList = new ArrayList<>();
        GetData(List<User> userList)
        {
            this.userList = userList;
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... params)
        {
            String data = RestfulService.source(Constants.SERVICE_LOGIN);
            
            return data;
        }

        protected void onPostExecute(String data)
        {
            System.out.println("RESTDATA"+data);
            if (progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
        }
    }
}
