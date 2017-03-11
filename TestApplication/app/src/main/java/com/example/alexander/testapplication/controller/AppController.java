package com.example.alexander.testapplication.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.alexander.testapplication.R;

public class AppController {
    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private String mCurrentRssUrl = "";

    public AppController(Context context) {
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mCurrentRssUrl = getRssUrl();
    }

    public String getRssUrl() {
        return mSharedPreferences.getString(
                mContext.getString(R.string.preference_address_rss_key), null);
    }

    public boolean isCorrectRssUrl() {
        return android.util.Patterns.WEB_URL.matcher(getRssUrl()).matches();
    }


    public boolean isInternetConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            Toast.makeText(mContext,
                    R.string.internet_connection_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean canUpdateRV() {//TODO:: add tests
        String newUrl = getRssUrl();
        return !mCurrentRssUrl.equals(newUrl);
    }
}
