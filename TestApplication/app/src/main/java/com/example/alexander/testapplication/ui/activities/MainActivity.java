package com.example.alexander.testapplication.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.alexander.testapplication.R;


public class MainActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_preference:
                showPreferences();
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        String rssFlow = mSharedPreferences.getString(
                getString(R.string.preference_address_rss_key), null);
        if (rssFlow != null) {
            //TODO:: set url rrs address
        }
    }

    private void showPreferences() {
        Intent intent = new Intent();
        intent.setClass(this, PreferencesActivity_.class);
        startActivity(intent);
        //WebViewActivity_.intent(this).extra("link", "http://developer.alexanderklimov.ru/android").start();
    }
}
