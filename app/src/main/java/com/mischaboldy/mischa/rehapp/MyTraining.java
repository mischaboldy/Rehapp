package com.mischaboldy.mischa.rehapp;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyTraining extends AppCompatActivity {

    public static final String PREFS_NAME = "TrainingPrefsFile";
    boolean trainingSaved;
    SQLiteDatabase workoutDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences trainingPreferences = getSharedPreferences(PREFS_NAME, 0);
        createDatabase();

        if (trainingPreferences != null){
            trainingSaved= trainingPreferences.getBoolean("trainingSaved", false);
        }else{
            trainingSaved = false;
        }

        if(trainingSaved){
            setFragment("schedule");
        }else{
            setFragment("training");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void save_training(View view) {
        SharedPreferences trainingPreferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = trainingPreferences.edit();

        CheckBox cycle = (CheckBox) findViewById(R.id.cycle_button);
        CheckBox walk = (CheckBox) findViewById(R.id.walk_button);
        CheckBox cardio = (CheckBox) findViewById(R.id.cardio_button);
        CheckBox hometrainer = (CheckBox) findViewById(R.id.hometrainer_button);
        CheckBox jog = (CheckBox) findViewById(R.id.jog_button);
        CheckBox nordicWalking = (CheckBox) findViewById(R.id.nordic_walking_button);

        Spinner intensityTypeSpinner = (Spinner) findViewById(R.id.intensity_type_spinner);
        Spinner intensityAmountSpinner = (Spinner) findViewById(R.id.intensity_amount_spinner);
        Spinner trainingAmountSpinner = (Spinner) findViewById(R.id.training_amount_spinner);

        String intensityType = intensityTypeSpinner.getSelectedItem().toString();
        String intensityAmount = intensityAmountSpinner.getSelectedItem().toString();
        String numberOfWorkouts = trainingAmountSpinner.getSelectedItem().toString();

        editor.putBoolean("cycle", cycle.isChecked());
        editor.putBoolean("walk", walk.isChecked());
        editor.putBoolean("cardio", cardio.isChecked());
        editor.putBoolean("hometrainer", hometrainer.isChecked());
        editor.putBoolean("jog", jog.isChecked());
        editor.putBoolean("nordicWalking", nordicWalking.isChecked());
        editor.putString("intensityType", intensityType);
        editor.putString("intensityAmount", intensityAmount);
        editor.putString("numberOfWorkouts", numberOfWorkouts);
        editor.putBoolean("trainingSaved", true);
        editor.apply();

        setFragment("schedule");

    }

    public void update_training(View view) {
        SharedPreferences trainingPreferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = trainingPreferences.edit();
        editor.putBoolean("trainingSaved", false);
        editor.apply();

        setFragment("training");
    }

    public void input_workout(View view) {
        Intent intent = new Intent(this, InputWorkout.class);
        startActivity(intent);
    }

    public void view_agenda(View view) {
        Intent intent = new Intent(this, InputWorkout.class);
        startActivity(intent);
    }

    public void setFragment(String chosenFragment){
        SharedPreferences trainingPreferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = trainingPreferences.edit();

        boolean cycle = trainingPreferences.getBoolean("cycle", false);
        boolean walk = trainingPreferences.getBoolean("walk", false);
        boolean cardio = trainingPreferences.getBoolean("cardio", false);
        boolean hometrainer = trainingPreferences.getBoolean("hometrainer", false);
        boolean jog = trainingPreferences.getBoolean("jog", false);
        boolean nordicWalking = trainingPreferences.getBoolean("nordicWalking", false);
        String intensityType = trainingPreferences.getString("intensityType", "pre-training");
        String intensityAmount = trainingPreferences.getString("intensityAmount", "laag");
        String numberOfWorkouts = trainingPreferences.getString("numberOfWorkouts", "1 keer per week");
        String title = intensityType + " " + intensityAmount;
        String[] results = new String[3];
        results = TrainingOptions.getProgramme(title);

        ArrayList<String> workouts = getWorkouts();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if(chosenFragment.equals("schedule")){
            ScheduleFragment fragment = ScheduleFragment.newInstance(title, results[0],
                    results[1],results[2], numberOfWorkouts, workouts);
            fragmentTransaction.replace(android.R.id.content, fragment);
        }else if(chosenFragment.equals("training")){
            TrainingFragment fragment = TrainingFragment.newInstance(cycle, walk, cardio,
                    hometrainer, jog, nordicWalking, intensityType, intensityAmount, numberOfWorkouts);
            fragmentTransaction.replace(android.R.id.content, fragment);
        }

        fragmentTransaction.commit();
    }

    public ArrayList<String> getWorkouts() {

        ArrayList<String> workouts = new ArrayList<String>();


        Cursor cursor = workoutDB.rawQuery("SELECT * FROM workouts", null);

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

    public void createDatabase() {
        try{

            workoutDB = this.openOrCreateDatabase("WorkoutDatabase.sqlite", MODE_PRIVATE, null);

            workoutDB.execSQL("CREATE TABLE IF NOT EXISTS workouts " +
                    "(id integer primary key, workout VARCHAR, duration integer, borgvalue integer, " +
                    "dt datetime default current_timestamp)");
        }
        catch (Exception e){
            Log.e("CONTACTS ERROR", "Error creating database");
        }
    }
}