package com.mischaboldy.mischa.rehapp;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by mischa on 27/06/16.
 */
public class EnterProfileFragment extends Fragment {

    public static final String PREFS_NAME = "ProfileInfoFile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_enter_profile, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        appTitle.setText(R.string.my_profile_title);

        Typeface titleTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        SharedPreferences profileInfo = getActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = profileInfo.edit();

        String name  = profileInfo.getString("name", "");
        String dob  = profileInfo.getString("dob", "");
        String sex  = profileInfo.getString("sex", "man");
        String weight  = profileInfo.getString("weight", "");
        String height  = profileInfo.getString("height", "");

        Spinner sexSpinner = (Spinner) view.findViewById(R.id.sex_spinner);

        EditText nameEditText = (EditText) view.findViewById(R.id.name_input_edit_text);
        EditText dobEditText = (EditText) view.findViewById(R.id.dob_input_edit_text);
        EditText weightEditText = (EditText) view.findViewById(R.id.weight_input_edit_text);
        EditText heightEditText = (EditText) view.findViewById(R.id.height_input_edit_text);

        nameEditText.setText(name);
        dobEditText.setText(dob);
        weightEditText.setText(weight);
        heightEditText.setText(height);

        sexSpinner.setSelection(getIndex(sexSpinner, sex));

        return view;
    }

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

}
