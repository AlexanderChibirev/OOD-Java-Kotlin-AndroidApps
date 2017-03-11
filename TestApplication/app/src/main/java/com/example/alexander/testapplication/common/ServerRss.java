package com.example.alexander.testapplication.common;

import android.support.annotation.NonNull;

public class ServerRss {

    @NonNull
    private String mUrl = "";
    private static volatile ServerRss instance;

    public static ServerRss getInstance() {
        ServerRss localInstance = instance;
        if (localInstance == null) {
            synchronized (ServerRss.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ServerRss();
                }
            }
        }
        return localInstance;
    }


    public void setURL(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }
}
