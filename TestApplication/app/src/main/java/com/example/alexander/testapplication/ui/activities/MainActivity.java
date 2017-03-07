package com.example.alexander.testapplication.ui.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.example.alexander.testapplication.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;

    @AfterViews
    void init() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @OptionsItem(R.id.menu_preference)
    void onPreferencesClick() {
        showPreferences();
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
        PreferencesActivity_.intent(this).start();
        //WebViewActivity_.intent(this).extra("link", "http://developer.alexanderklimov.ru/android").start();
    }
}
