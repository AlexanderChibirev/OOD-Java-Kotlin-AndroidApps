package com.example.alexander.testapplication.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;

import org.androidannotations.annotations.EFragment;

import static com.example.alexander.testapplication.R.xml.preferences;

@EFragment
public class PreferencesFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(preferences);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        EditTextPreference editTextPreference = (EditTextPreference) findPreference(key);
        String text = editTextPreference.getText();
       /* if (NetworkUtils.getXmlDocument(text) != null) {// is correct url
            editTextPreference.setText(text);
        } else {
            ToastMessageUtils.showMessage(
                    getString(R.string.rrs_url_error),
                    editTextPreference.getContext());
        }*/
    }
}
