package com.example.alexander.testapplication.ui.activities;

import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.alexander.testapplication.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import static com.example.alexander.testapplication.R.id.webView;


@EActivity(R.layout.activity_webview_record)
public class WebViewActivity extends Activity {

    @Extra("link")
    String mLink;

    @ViewById(webView)
    WebView mWebView;

    @AfterViews
    void init() {
        mWebView.loadUrl(mLink);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}