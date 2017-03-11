package com.example.alexander.testapplication.ui.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.alexander.testapplication.ui.fragments.PreferencesFragment;

import org.androidannotations.annotations.EActivity;

import java.util.List;


@EActivity
public class PreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferencesFragment()).commit();
    }
}
