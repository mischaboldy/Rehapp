package com.mischaboldy.mischa.rehapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by mischa on 27/06/16.
 */
public class MyProfile extends AppCompatActivity {

    public static final String PREFS_NAME = "ProfileInfoFile";
    boolean profileSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences profileInfo = getSharedPreferences(PREFS_NAME, 0);

        if (profileInfo != null){
            profileSaved= profileInfo.getBoolean("profileSaved", false);
        }else{
            profileSaved = false;
        }

        if(profileSaved){
            setFragment("showProfile");
        }else{
            setFragment("enterProfile");
        }
    }

    public void saveProfile(View view) {
        SharedPreferences trainingPreferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = trainingPreferences.edit();

        EditText name = (EditText) findViewById(R.id.name_input_edit_text);
        EditText dob = (EditText) findViewById(R.id.dob_input_edit_text);
        Spinner sexSpinner = (Spinner) findViewById(R.id.sex_spinner);
        EditText weight = (EditText) findViewById(R.id.weight_input_edit_text);
        EditText height = (EditText) findViewById(R.id.height_input_edit_text);

        editor.putString("name", String.valueOf(name.getText()));
        editor.putString("dob", String.valueOf(dob.getText()));
        editor.putString("sex", String.valueOf(sexSpinner.getSelectedItem()));
        editor.putString("weight", String.valueOf(weight.getText()));
        editor.putString("height", String.valueOf(height.getText()));

        editor.putBoolean("profileSaved", true);
        editor.apply();

        setFragment("showProfile");
    }

    public void updateProfile(View view) {
        SharedPreferences profileInfo = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = profileInfo.edit();
        editor.putBoolean("profileSaved", false);
        editor.apply();

        setFragment("enterProfile");
    }

    public void setFragment(String chosenFragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(chosenFragment.equals("showProfile")){
            ShowProfileFragment fragment = new ShowProfileFragment();
            fragmentTransaction.replace(android.R.id.content, fragment);
        }else if(chosenFragment.equals("enterProfile")){
            EnterProfileFragment fragment = new EnterProfileFragment();
            fragmentTransaction.replace(android.R.id.content, fragment);
        }

        fragmentTransaction.commit();
    }
}
