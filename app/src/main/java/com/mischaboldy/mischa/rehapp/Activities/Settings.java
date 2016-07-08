package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mischaboldy.mischa.rehapp.AchievementTracker;
import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 05/07/16.
 */
public class Settings extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
        root.addView(bar, 0); // insert at top
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            Preference removeMedalsButton = findPreference("removeMedals");
            removeMedalsButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Toast.makeText(getActivity(),"Medailles verwijderd",Toast.LENGTH_SHORT).show();
                    removeMedals(getActivity());
                    return true;
                }
            });

            Preference removeWorkoutsButton = findPreference("removeWorkouts");
            removeWorkoutsButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Toast.makeText(getActivity(),"Workouts verwijderd",Toast.LENGTH_SHORT).show();
                    removeWorkouts();
                    return true;
                }
            });

            Preference removeProfileButton = findPreference("removeProfile");
            removeProfileButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Toast.makeText(getActivity(),"Profiel verwijderd",Toast.LENGTH_SHORT).show();

                    removeProfile();
                    return true;
                }
            });
        }

    }

    public static void removeMedals(Context context){
        AchievementTracker.removeMedals(context);
    }


    public static void removeWorkouts(){
    }


    public static void removeProfile(){
    }
}