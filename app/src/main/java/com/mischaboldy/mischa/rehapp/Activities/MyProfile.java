package com.mischaboldy.mischa.rehapp.Activities;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 27/06/16.
 */
public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.my_profile_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MyProfileFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.help_button){
            InfoBoxHelper.openBox(this, "myProfile");
        }
        return super.onOptionsItemSelected(item);
    }

    public static class MyProfileFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.profile);
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onResume() {
            super.onResume();
            for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); ++i) {
                Preference preference = getPreferenceScreen().getPreference(i);
                if (preference instanceof PreferenceGroup) {
                    PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
                    for (int j = 0; j < preferenceGroup.getPreferenceCount(); ++j) {
                        Preference singlePref = preferenceGroup.getPreference(j);
                        updatePreference(singlePref, singlePref.getKey());
                    }
                } else {
                    updatePreference(preference, preference.getKey());
                }
            }
        }
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            updatePreference(findPreference(key), key);
        }

        private void updatePreference(Preference preference, String key) {
            if (preference == null) return;
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                listPreference.setSummary(listPreference.getEntry());
                return;
            }
            SharedPreferences sharedPrefs = getPreferenceManager().getSharedPreferences();
            String additive = "";
            if (key.equals("weight") && sharedPrefs.getString(key, "") != ""){additive = " KG";}
            if (key.equals("height") && sharedPrefs.getString(key, "") != ""){additive = " M";}
            preference.setSummary(sharedPrefs.getString(key, "") + additive);
        }

    }
}
