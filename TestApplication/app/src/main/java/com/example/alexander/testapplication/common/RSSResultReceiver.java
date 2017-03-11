package com.example.alexander.testapplication.common;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.widget.Toast;

import com.example.alexander.testapplication.R;

public class RSSResultReceiver extends ResultReceiver {
    private Context mContext;
    public static int NETWORK_CONNECTION_ERROR = 1;
    public static int URL_OR_RSS_CHANNEL_ERROR = 2;
    public static int SERVER_ERROR = 3;
    public static int UNKNOWN_ERROR = 4;

    public RSSResultReceiver(Handler handler, Context context) {
        super(handler);
        mContext = context;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        // Code to process resultData here
        if (resultCode == NETWORK_CONNECTION_ERROR) {
            Toast.makeText(mContext, R.string.check_network_connection, Toast.LENGTH_SHORT).show();
        } else if (resultCode == URL_OR_RSS_CHANNEL_ERROR) {
            Toast.makeText(mContext, R.string.bad_url_or_rss_channel, Toast.LENGTH_SHORT).show();
        } else if (resultCode == SERVER_ERROR) {
            Toast.makeText(mContext, R.string.server_error, Toast.LENGTH_SHORT).show();
        } else if (resultCode == UNKNOWN_ERROR) {
            Toast.makeText(mContext, R.string.unknown_error, Toast.LENGTH_SHORT).show();
        }
    }
}
