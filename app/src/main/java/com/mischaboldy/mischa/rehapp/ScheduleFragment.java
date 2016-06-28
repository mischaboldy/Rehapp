package com.mischaboldy.mischa.rehapp;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mischa on 23/06/16.
 */
public class ScheduleFragment extends Fragment {

    public static final String PREFS_NAME = "TrainingPrefsFile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_schedule, container, false);
        SharedPreferences trainingPreferences = getActivity().getSharedPreferences(PREFS_NAME, 0);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.my_training_title);

        Typeface titleTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        TextView training_title_textview = (TextView) view.findViewById(R.id.training_title_textview);
        TextView training_intensity_textview = (TextView) view.findViewById(R.id.training_intensity_textview);
        TextView training_rest_textview = (TextView) view.findViewById(R.id.training_rest_textview);
        TextView training_amount_textview = (TextView) view.findViewById(R.id.training_amount_textview);
        TextView number_of_workouts_textview = (TextView) view.findViewById(R.id.number_of_workouts_textview);

        String intensityType = trainingPreferences.getString("intensityType", "pre-training");
        String intensityAmount = trainingPreferences.getString("intensityAmount", "laag");
        String numberOfWorkouts = trainingPreferences.getString("numberOfWorkouts", "1 keer per week");
        String title = intensityType + " " + intensityAmount;
        String[] results = new String[3];
        results = TrainingOptions.getProgram(title);

        ArrayList<String> workouts = getWorkouts();

        training_title_textview.setText(title);
        training_intensity_textview.setText(results[0] + " VO2max");
        training_rest_textview.setText(results[1]);
        training_amount_textview.setText(results[2] + " weeks trainingsprogramma");
        number_of_workouts_textview.setText(numberOfWorkouts);

        ListAdapter listAdapter = new workoutListViewAdapter(getActivity().getApplicationContext()
                , workouts);

        ListView listView = (ListView) view.findViewById(R.id.workout_list_view);

        listView.setAdapter(listAdapter);

        return view;
    }

    public ArrayList<String> getWorkouts() {

        ArrayList<String> workouts = new ArrayList<String>();

        SQLiteDatabase rehappDB = getActivity().openOrCreateDatabase("RehappDatabase.sqlite", getActivity().MODE_PRIVATE, null);

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
