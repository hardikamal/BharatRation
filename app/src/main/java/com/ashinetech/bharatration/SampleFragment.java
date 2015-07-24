package com.ashinetech.bharatration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ragavendran on 24-Jul-2015.
 */
public class SampleFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        String[] spinneronedata = {"one","two","three"};
        String[] spinnertwodata = {"four","five","six"};

        View view = inflater.inflate(R.layout.sample_fragment,container,false);

        Bundle bundleone = new Bundle();
        bundleone.putStringArray("spinneronedata",spinneronedata);

        Bundle bundletwo = new Bundle();
        bundletwo.putStringArray("spinnertwodata",spinnertwodata);

        Sample_Spinner_One sample_spinner_one = new Sample_Spinner_One();
        Sample_Spinner_Two sample_spinner_two = new Sample_Spinner_Two();

        sample_spinner_one.setArguments(bundleone);
        sample_spinner_two.setArguments(bundletwo);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.sample_fragment_one,sample_spinner_one);
        fragmentTransaction.replace(R.id.sample_fragment_two,sample_spinner_two);

        fragmentTransaction.commit();

        return view;
    }
}
