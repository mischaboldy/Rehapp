package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.R;


public class EnterTrainingProgram extends AppCompatActivity {

    public static final String PREFS_NAME = "TrainingProgramPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_training_program);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.title_training_program);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        setOptions();
    }

    private void setOptions() {
        SharedPreferences TrainingProgramPreferences = getSharedPreferences(PREFS_NAME, 0);

        CheckBox cycle = (CheckBox) findViewById(R.id.cycle_button);
        CheckBox walk = (CheckBox) findViewById(R.id.walk_button);
        CheckBox cardio = (CheckBox) findViewById(R.id.cardio_button);
        CheckBox hometrainer = (CheckBox) findViewById(R.id.hometrainer_button);
        CheckBox jog = (CheckBox) findViewById(R.id.jog_button);
        CheckBox nordicWalking = (CheckBox) findViewById(R.id.nordic_walking_button);

        Spinner intensityTypeSpinner = (Spinner) findViewById(R.id.intensity_type_spinner);
        Spinner intensityAmountSpinner = (Spinner) findViewById(R.id.intensity_amount_spinner);
        Spinner workoutAmountSpinner = (Spinner) findViewById(R.id.workout_amount_spinner);

        boolean cycleChecked = TrainingProgramPreferences.getBoolean("cycle", false);
        boolean walkChecked = TrainingProgramPreferences.getBoolean("walk", false);
        boolean cardioChecked = TrainingProgramPreferences.getBoolean("cardio", false);
        boolean hometrainerChecked = TrainingProgramPreferences.getBoolean("hometrainer", false);
        boolean jogChecked = TrainingProgramPreferences.getBoolean("jog", false);
        boolean nordicWalkingChecked = TrainingProgramPreferences.getBoolean("nordicWalking", false);
        String intensityType = TrainingProgramPreferences.getString("intensityType", "pre-training");
        String intensityAmount = TrainingProgramPreferences.getString("intensityAmount", "laag");
        String numberOfWorkouts = TrainingProgramPreferences.getString("numberOfWorkouts", "1 keer per week");

        intensityTypeSpinner.setSelection(getIndex(intensityTypeSpinner, intensityType));
        intensityAmountSpinner.setSelection(getIndex(intensityAmountSpinner, intensityAmount));
        workoutAmountSpinner.setSelection(getIndex(workoutAmountSpinner, numberOfWorkouts));

        cycle.setChecked(cycleChecked);
        walk.setChecked(walkChecked);
        cardio.setChecked(cardioChecked);
        hometrainer.setChecked(hometrainerChecked);
        jog.setChecked(jogChecked);
        nordicWalking.setChecked(nordicWalkingChecked);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.help_button){
            InfoBoxHelper.openBox(this, "trainingprogram");
        }
        return super.onOptionsItemSelected(item);
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

    public void saveTrainingProgram(View view) {
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
        Spinner trainingAmountSpinner = (Spinner) findViewById(R.id.workout_amount_spinner);

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
        editor.putBoolean("workoutSaved", true);
        editor.apply();

        Intent intent = new Intent(this, TrainingProgram.class);
        startActivity(intent);
    }
}