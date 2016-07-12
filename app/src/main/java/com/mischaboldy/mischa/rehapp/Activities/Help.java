package com.mischaboldy.mischa.rehapp.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mischaboldy.mischa.rehapp.AchievementTracker;
import com.mischaboldy.mischa.rehapp.ListViewAdapters.ExpandableListAdapter;
import com.mischaboldy.mischa.rehapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mischa on 05/07/16.
 */
public class Help extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        AchievementTracker.trackAchievement(this, "help");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.help_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add(getResources().getString(R.string.question1));
        listDataHeader.add(getResources().getString(R.string.question2));
        listDataHeader.add(getResources().getString(R.string.question3));
        listDataHeader.add(getResources().getString(R.string.question4));
        listDataHeader.add(getResources().getString(R.string.question5));

        // Adding child data
        List<String> answer1 = new ArrayList<String>();
        answer1.add(getResources().getString(R.string.answer1));

        List<String> answer2 = new ArrayList<String>();
        answer2.add(getResources().getString(R.string.answer2));

        List<String> answer3 = new ArrayList<String>();
        answer3.add(getResources().getString(R.string.answer3));

        List<String> answer4 = new ArrayList<String>();
        answer4.add(getResources().getString(R.string.answer4));

        List<String> answer5 = new ArrayList<String>();
        answer5.add(getResources().getString(R.string.answer5));

        listDataChild.put(listDataHeader.get(0), answer1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), answer2);
        listDataChild.put(listDataHeader.get(2), answer3);
        listDataChild.put(listDataHeader.get(3), answer4);
        listDataChild.put(listDataHeader.get(4), answer5);

    }

    public void helpMe(View view) {
        Toast.makeText(this, R.string.try_again_toast_text,Toast.LENGTH_LONG).show();
    }
}

