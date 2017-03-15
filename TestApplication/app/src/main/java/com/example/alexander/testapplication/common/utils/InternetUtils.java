package com.example.alexander.testapplication.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.example.alexander.testapplication.R;

public class InternetUtils {

    public static boolean isInternetConnection(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            Toast.makeText(context,
                    R.string.internet_connection_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isCorrectUrl(String url) {
        return android.util.Patterns.WEB_URL.matcher(url).matches();
    }
}
