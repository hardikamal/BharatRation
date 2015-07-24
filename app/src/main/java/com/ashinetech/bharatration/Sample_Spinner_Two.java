package com.ashinetech.bharatration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by ragavendran on 24-Jul-2015.
 */
public class Sample_Spinner_Two extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.sample_spinner_two,container,false);

        String[] sampletwodata = getArguments().getStringArray("spinnertwodata");

        Spinner spinner = (Spinner) view.findViewById(R.id.sample_spinner_two);

        ArrayAdapter<String> sampletwoarray = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,sampletwodata);
        spinner.setAdapter(sampletwoarray);

        return view;
    }
}
