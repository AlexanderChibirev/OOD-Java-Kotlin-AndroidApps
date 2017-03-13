package com.example.alexander.testapplication.common.parsers;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.widget.Toast;

import com.example.alexander.testapplication.R;

public class RSSResultReceiver extends ResultReceiver {
    private Context mContext;
    public static final int NETWORK_CONNECTION_ERROR = 1;
    public static final int URL_OR_RSS_CHANNEL_ERROR = 2;
    public static final int UNKNOWN_HOST = 3;
    public static final int UNKNOWN_ERROR = 4;

    public RSSResultReceiver(Handler handler, Context context) {
        super(handler);
        mContext = context;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        // Code to process resultData here
        switch (resultCode) {
            case NETWORK_CONNECTION_ERROR:
                Toast.makeText(mContext, R.string.check_network_connection, Toast.LENGTH_SHORT).show();
                break;
            case URL_OR_RSS_CHANNEL_ERROR:
                Toast.makeText(mContext, R.string.bad_url_or_rss_channel, Toast.LENGTH_SHORT).show();
                break;
            case UNKNOWN_HOST:
                Toast.makeText(mContext, R.string.unknown_host, Toast.LENGTH_SHORT).show();
                break;
            case UNKNOWN_ERROR:
                Toast.makeText(mContext, R.string.unknown_error, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
