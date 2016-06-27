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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by mischa on 27/06/16.
 */
public class ShowProfileFragment extends Fragment {

    public static final String PREFS_NAME = "ProfileInfoFile";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.show_profile_fragment, container, false);

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

        String name  = profileInfo.getString("name", "");
        String dob  = profileInfo.getString("dob", "");
        String sex  = profileInfo.getString("sex", "man");
        String weight  = profileInfo.getString("weight", "");
        String height  = profileInfo.getString("height", "");

        // Get ListView object from xml
        ListView profileInfoList = (ListView) view.findViewById(R.id.profile_info_list);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "naam: " + name,
                "Geboorte datum: " + dob,
                "Geslacht: " + sex,
                "Gewicht: " + weight + " kg",
                "Lengte: " + height + " m",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_2, android.R.id.text1, values);

        // Assign adapter to ListView
        profileInfoList.setAdapter(adapter);

        return view;
    }
}
