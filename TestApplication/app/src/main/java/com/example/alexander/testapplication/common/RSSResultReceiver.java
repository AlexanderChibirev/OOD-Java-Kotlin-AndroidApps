package com.example.alexander.testapplication.common;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.widget.Toast;

import com.example.alexander.testapplication.R;

public class RSSResultReceiver extends ResultReceiver {
    Context mContext;

    public RSSResultReceiver(Handler handler, Context context) {
        super(handler);
        mContext = context;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        // Code to process resultData here
        if (resultCode == 1) {
            Toast.makeText(mContext, R.string.check_network_connection, Toast.LENGTH_SHORT).show();
        } else if (resultCode == 2) {
            Toast.makeText(mContext, R.string.bad_url_or_rss_channel, Toast.LENGTH_SHORT).show();
        } else if (resultCode == 3) {
            Toast.makeText(mContext, R.string.server_error, Toast.LENGTH_SHORT).show();
        }
    }
}
