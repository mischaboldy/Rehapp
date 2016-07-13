package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.AchievementTracker;
import com.mischaboldy.mischa.rehapp.ListViewAdapters.WorkoutListViewAdapter;
import com.mischaboldy.mischa.rehapp.R;

import java.util.ArrayList;

/**
 * Created by mischa on 12/07/16.
 */
public class MyWorkouts extends AppCompatActivity{


    public static final String PREFS_NAME = "TrainingPrefsFile";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workouts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.my_workouts_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);


        Bundle extras = getIntent().getExtras();
        Boolean fromWorkout;
        Boolean fromTraining;
        Boolean fromTest;

        if(extras != null){
            fromWorkout = extras.getBoolean("FROM_WORKOUTS");
            if(fromWorkout == true){
                AchievementTracker.trackAchievement(this, "workout");
            }
            fromTraining = extras.getBoolean("FROM_TRAINING");
            if(fromTraining == true){
                AchievementTracker.trackAchievement(this, "training");
            }
            fromTest = extras.getBoolean("FROM_TEST");
            if(fromTest == true){
                AchievementTracker.trackAchievement(this, "test");
            }
        }
        ArrayList<String> workouts = getWorkouts();

        ListAdapter listAdapter = new WorkoutListViewAdapter(this, workouts);

        ListView listView = (ListView) findViewById(R.id.workout_list_view);

        listView.setAdapter(listAdapter);

        TextView emptyText = (TextView)findViewById(R.id.empty_text);
        listView.setEmptyView(emptyText);
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_workouts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.enter_workout_menu_button:
                intent = new Intent(this, EnterWorkout.class);
                startActivity(intent);
                return true;
            case R.id.enter_test_menu_button:
                intent = new Intent(this, EnterTest.class);
                startActivity(intent);
                return true;
            case R.id.enter_training_menu_button:
                intent = new Intent(this, EnterTraining.class);
                startActivity(intent);
                return true;
            case R.id.open_calendar_menu_button:
                intent = new Intent(this, MyCalendar.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item); // important line
    }


    public ArrayList<String> getWorkouts() {

        ArrayList<String> workouts = new ArrayList<String>();

        SQLiteDatabase rehappDB = this.openOrCreateDatabase("RehappDatabase.sqlite", MODE_PRIVATE, null);

        Cursor cursor = rehappDB.rawQuery("SELECT * FROM workouts", null);

        int idColumn = cursor.getColumnIndex("id");
        int workoutColumn = cursor.getColumnIndex("workout");
        int durationColumn = cursor.getColumnIndex("duration");
        int borgValueColumn = cursor.getColumnIndex("borgvalue");
        int dtColumn = cursor.getColumnIndex("dt");

        cursor.moveToFirst();


        if (cursor != null && (cursor.getCount() > 0)) {

            do {

                String id = cursor.getString(idColumn);
                String workout = cursor.getString(workoutColumn);
                String duration = cursor.getString(durationColumn);
                String borgValue = cursor.getString(borgValueColumn);
                String dt = cursor.getString(dtColumn);

                workouts.add(id + ":" + workout + ":" + duration + ":" + borgValue + ":" + dt);

            } while (cursor.moveToNext());

        }
        return workouts;
    }


}
