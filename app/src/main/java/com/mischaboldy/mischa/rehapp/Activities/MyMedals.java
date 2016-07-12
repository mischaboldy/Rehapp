package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.Activities.MyProfile;
import com.mischaboldy.mischa.rehapp.ListViewAdapters.MedalsListViewAdapter;
import com.mischaboldy.mischa.rehapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by mischa on 05/07/16.
 */
public class MyMedals extends AppCompatActivity {

    public static final String PREFS_NAME = "MedalsFile";
    SharedPreferences medals;
    String[] medalsList = new String[5];
    String[][] medals2dList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medals);
        medals = getSharedPreferences(PREFS_NAME, 0);
        medals2dList = new String[][]{
                new String[] {"workoutMedal", medals.getString("workoutDate", "01-01-2016 00:00:00")}
                ,   new String[] {"trainingMedal", medals.getString("trainingDate", "01-01-2016 00:00:00")}
                ,   new String[] {"testMedal", medals.getString("testDate", "01-01-2016 00:00:00")}
                ,   new String[] {"helpMedal", medals.getString("helpDate", "01-01-2016 00:00:00")}
                ,   new String[] {"infoMedal", medals.getString("infoDate", "01-01-2016 00:00:00")}
        };

        sort2dArray(medals2dList);
        extracMedalsList();

        if(!medals.contains("medalsMade")){ initiateMedals();}

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.my_medals_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        ListAdapter listAdapter = new MedalsListViewAdapter(this, medalsList);

        ListView listView = (ListView) findViewById(R.id.medals_list_view);

        listView.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_medals, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.open_leaderboards_menu_button:
                intent = new Intent(this, Leaderboards.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item); // important line
    }


    private void sort2dArray(String[][] medalsList) {

        Arrays.sort(medalsList, new Comparator<String[]>() {

            @Override
            public int compare(String[] o1, String[] o2) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date d1 = null;
                Date d2 = null;
                try {
                    d1 = sdf.parse(o1[1]);
                    d2 = sdf.parse(o2[1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return d2.compareTo(d1);
            }
        });

        System.out.println(Arrays.deepToString(medalsList));
    }

    private void extracMedalsList(){
        for(int i = 0; i<medals2dList.length ; i++){
            medalsList[i] = medals2dList[i][0];
        }
    }

    public void initiateMedals(){
        SharedPreferences.Editor editor = medals.edit();

        editor.putBoolean("workoutMedal", false);
        editor.putBoolean("trainingMedal", false);
        editor.putBoolean("testMedal", false);
        editor.putBoolean("helpMedal", false);
        editor.putBoolean("infoMedal", false);
        editor.putBoolean("medalsMade", true);
        editor.apply();

    }
}