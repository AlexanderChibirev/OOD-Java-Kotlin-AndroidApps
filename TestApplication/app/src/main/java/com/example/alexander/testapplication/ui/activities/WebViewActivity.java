package com.example.alexander.testapplication.ui.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.alexander.testapplication.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_webview_record)
public class WebViewActivity extends Activity {

    @Extra("link")
    String link;

    @ViewById(R.id.webView)
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @AfterViews
    void init() {
        webView.loadUrl(link);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}