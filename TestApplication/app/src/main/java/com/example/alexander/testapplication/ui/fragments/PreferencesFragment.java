package com.example.alexander.testapplication.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.example.alexander.testapplication.R;
import com.example.alexander.testapplication.common.utils.InternetUtils;

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
        if (!InternetUtils.isCorrectUrl(editTextPreference.getText())) {
            Toast.makeText(this.getActivity(), R.string.bad_url_or_rss_channel, Toast.LENGTH_SHORT).show();
        } else {
            String text = editTextPreference.getText();
            editTextPreference.setText(text);
        }
    }
}
