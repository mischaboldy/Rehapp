package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.mischaboldy.mischa.rehapp.ExperienceTracker;
import com.mischaboldy.mischa.rehapp.DatabaseHelper;
import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 05/07/16.
 */
public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.settings_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);


        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MyPreferenceFragment()).commit();
    }


    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
//            Preference removeMedalsButton = findPreference("removeMedals");
//            removeMedalsButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                @Override
//                public boolean onPreferenceClick(Preference preference) {
//                    Toast.makeText(getActivity(),"Medailles verwijderd",Toast.LENGTH_SHORT).show();
//                    removeMedals(getActivity());
//                    return true;
//                }
//            });
//
//            Preference removeWorkoutsButton = findPreference("removeWorkouts");
//            removeWorkoutsButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                @Override
//                public boolean onPreferenceClick(Preference preference) {
//                    Toast.makeText(getActivity(),"Workouts verwijderd",Toast.LENGTH_SHORT).show();
//                    removeWorkouts(getActivity());
//                    return true;
//                }
//            });
//
//            Preference removeProfileButton = findPreference("removeProfile");
//            removeProfileButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                @Override
//                public boolean onPreferenceClick(Preference preference) {
//                    Toast.makeText(getActivity(),"Profiel verwijderd",Toast.LENGTH_SHORT).show();
//                    removeProfile(getActivity());
//                    return true;
//                }
//            });
//
//            Preference removeTrainingButton = findPreference("removeTraining");
//            removeTrainingButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                @Override
//                public boolean onPreferenceClick(Preference preference) {
//                    Toast.makeText(getActivity(),"Trainingen verwijderd",Toast.LENGTH_SHORT).show();
//                    removeTraining(getActivity());
//                    return true;
//                }
//            });
//
//            Preference removeTestButton = findPreference("removeTests");
//            removeTestButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                @Override
//                public boolean onPreferenceClick(Preference preference) {
//                    Toast.makeText(getActivity(),"Tests verwijderd",Toast.LENGTH_SHORT).show();
//                    removeTests(getActivity());
//                    return true;
//                }
//            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.help_button){
            InfoBoxHelper.openBox(this, "settings");
        }
        return super.onOptionsItemSelected(item);
    }

    public static void removeMedals(Context context){ ExperienceTracker.removeExperience(context); }

    public static void removeWorkouts(Context context){ DatabaseHelper.clearTable(context, "workouts"); }

    public static void removeTraining(Context context){ DatabaseHelper.clearTable(context, "training"); }

    public static void removeTests(Context context){ DatabaseHelper.clearTable(context, "test"); }

    public static void removeProfile(Context context){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", "");
        editor.putString("userName", "");
        editor.putString("dob", "");
        editor.putString("height", "");
        editor.putString("weight", "");
        editor.putString("sex", "male");
        editor.apply();
    }
}