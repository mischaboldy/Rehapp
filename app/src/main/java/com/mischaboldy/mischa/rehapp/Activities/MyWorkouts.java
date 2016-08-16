package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.ExperienceTracker;
import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.ListViewAdapters.WorkoutListViewAdapter;
import com.mischaboldy.mischa.rehapp.R;

import java.util.ArrayList;

/**
 * Created by mischa on 12/07/16.
 */
public class MyWorkouts extends AppCompatActivity{

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
        if(extras != null){
            fromWorkout = extras.getBoolean("WORKOUTADDED");
            if(fromWorkout == true){
                ExperienceTracker.addExperience(this, 22, "WorkoutExperience");
                ExperienceTracker.addExperience(this, 13, "ScheduleExperience");

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
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.help_button){
            InfoBoxHelper.openBox(this, "myWorkouts");
        }
        return super.onOptionsItemSelected(item);
    }


    public ArrayList<String> getWorkouts() {

        ArrayList<String> workouts = new ArrayList<String>();

        SQLiteDatabase rehappDB = this.openOrCreateDatabase("RehappDatabase.sqlite", MODE_PRIVATE, null);

        Cursor cursor = rehappDB.rawQuery("SELECT * FROM workouts", null);

        int idColumn = cursor.getColumnIndex("id");
        int workoutColumn = cursor.getColumnIndex("workout");
        int durationColumn = cursor.getColumnIndex("duration");
        int borgValueStartColumn = cursor.getColumnIndex("borgvalue_start");
        int borgValueEndColumn = cursor.getColumnIndex("borgvalue_end");
        int dtColumn = cursor.getColumnIndex("dt");

        cursor.moveToFirst();


        if (cursor != null && (cursor.getCount() > 0)) {

            do {

                String id = cursor.getString(idColumn);
                String workout = cursor.getString(workoutColumn);
                String duration = cursor.getString(durationColumn);
                String borgValueStart = cursor.getString(borgValueStartColumn);
                String borgValueEnd = cursor.getString(borgValueEndColumn);

                String dt = cursor.getString(dtColumn);

                workouts.add(id + ":" + workout + ":" + duration + ":" + borgValueStart + ":" + borgValueEnd + ":" + dt);

            } while (cursor.moveToNext());

        }
        return workouts;
    }

    public void enterWorkout(View view) {
        Intent intent = new Intent(this, EnterWorkout.class);
        startActivity(intent);
    }
}
