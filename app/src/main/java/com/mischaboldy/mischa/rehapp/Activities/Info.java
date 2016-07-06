package com.mischaboldy.mischa.rehapp.Activities;

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
public class Info extends AppCompatActivity {


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        AchievementTracker.trackAchievement(this, "info");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.info_title);

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
        listDataHeader.add(getResources().getString(R.string.introduction_header));
        listDataHeader.add(getResources().getString(R.string.disclaimer_header));
        listDataHeader.add(getResources().getString(R.string.general_header));
        listDataHeader.add(getResources().getString(R.string.financial_header));
        listDataHeader.add(getResources().getString(R.string.copyright_header));
        listDataHeader.add(getResources().getString(R.string.news_header));

        // Adding child data
        List<String> appInfo = new ArrayList<String>();
        appInfo.add(getResources().getString(R.string.introduction));

        List<String> disclaimer = new ArrayList<String>();
        disclaimer.add(getResources().getString(R.string.disclaimer));

        List<String> general = new ArrayList<String>();
        general.add(getResources().getString(R.string.general));

        List<String> financial = new ArrayList<String>();
        financial.add(getResources().getString(R.string.financial));

        List<String> copyright = new ArrayList<String>();
        copyright.add(getResources().getString(R.string.copyright));

        List<String> news = new ArrayList<String>();
        news.add(getResources().getString(R.string.news));

        listDataChild.put(listDataHeader.get(0), appInfo); // Header, Child data
        listDataChild.put(listDataHeader.get(1), disclaimer);
        listDataChild.put(listDataHeader.get(2), general);
        listDataChild.put(listDataHeader.get(3), financial);
        listDataChild.put(listDataHeader.get(4), copyright);
        listDataChild.put(listDataHeader.get(5), news);
    }

    public void helpMe(View view) {
        Toast.makeText(this, R.string.try_again_toast_text,Toast.LENGTH_LONG).show();
    }
}

