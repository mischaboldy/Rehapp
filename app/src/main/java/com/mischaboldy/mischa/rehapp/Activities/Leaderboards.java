package com.mischaboldy.mischa.rehapp.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.ListViewAdapters.LeaderboardListViewAdapter;
import com.mischaboldy.mischa.rehapp.Users;
import com.mischaboldy.mischa.rehapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mischa on 11/07/16.
 */
public class Leaderboards extends AppCompatActivity {

    public static final String PREFS_NAME1 = "MedalsFile";
    public static final String PREFS_NAME2 = "ProfileInfoFile";

    SharedPreferences profileInfo;
    SharedPreferences medals;
    ArrayList<Users> leaderboards2dList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        medals = getSharedPreferences(PREFS_NAME1, 0);
        profileInfo = getSharedPreferences(PREFS_NAME2, 0);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.leaderboards_title);

        leaderboards2dList = getLeaderboardsList();


        Collections.sort(leaderboards2dList, new Comparator<Users>(){
            public int compare(Users emp1, Users emp2) {
                Integer case1 = Integer.parseInt(emp1.getMedals());
                Integer case2 = Integer.parseInt(emp2.getMedals());

                return case1.compareTo(case2);
            }
        });
        Collections.reverse(leaderboards2dList);
        ListAdapter listAdapter = new LeaderboardListViewAdapter(this, leaderboards2dList);

        ListView listView = (ListView) findViewById(R.id.leaderboards_list_view);

        listView.setAdapter(listAdapter);

    }


    public Users getUser() {

        Boolean[] EarnedMedals = new Boolean[5];
        Integer count = 0;
        EarnedMedals[0] = medals.getBoolean("workoutMedal", false);
        EarnedMedals[1] = medals.getBoolean("trainingMedal", false);
        EarnedMedals[2] = medals.getBoolean("testMedal", false);
        EarnedMedals[3] = medals.getBoolean("helpMedal", false);
        EarnedMedals[4] = medals.getBoolean("infoMedal", false);

        for(int i = 0; i < 5; i++){
            if (EarnedMedals[i].equals(true)){
                count++;
            }
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPref.getString("name", "");
        Users user = new Users(userName, count.toString());

        return user;
    }

    public ArrayList<Users> getLeaderboardsList() {
        Users user1 = new Users("gebruiker2", "3");
        Users user2 = new Users("gebruiker3", "12");
        Users user3 = new Users("gebruiker4", "2");
        Users user4 = new Users("gebruiker5", "7");
        Users user5 = new Users("gebruiker6", "9");
        Users user6 = new Users("gebruiker7", "13");
        Users user7 = new Users("gebruiker8", "1");
        Users user8 = new Users("gebruiker9", "13");
        Users user9 = new Users("gebruiker10", "10");
        Users user10 = new Users("gebruiker11", "5");
        Users user11 = new Users("gebruiker12", "0");
        Users user12 = getUser();

        ArrayList<Users> userList = new ArrayList<Users>();

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);
        userList.add(user7);
        userList.add(user8);
        userList.add(user9);
        userList.add(user10);
        userList.add(user11);
        userList.add(user12);

        return userList;
    }
}
