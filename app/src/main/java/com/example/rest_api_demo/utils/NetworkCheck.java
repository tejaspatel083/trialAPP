package com.example.rest_api_demo.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import java.net.InetAddress;

public class NetworkCheck {

    public boolean checkNetwork(Activity activity)
    {


        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected())
        {
            return checkInternet();
        }
        else
        {
            return false;
        }
    }


    public boolean checkInternet()
    {
        try
        {
            InetAddress iAddress = InetAddress.getByName("google.com");

            return !iAddress.equals("");

        }
        catch(Exception e)
        {
            Log.d("Network Check", ""+e.getMessage());
            return false;
        }
    }
}
