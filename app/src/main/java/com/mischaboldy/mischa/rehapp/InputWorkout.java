package com.mischaboldy.mischa.rehapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;

/**
 * Created by mischa on 23/06/16.
 */
public class InputWorkout extends AppCompatActivity {

    SQLiteDatabase workoutDB = null;
    String selectedWorkout;
    Integer borgValue = 6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_workout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText("Workout toevoegen");

        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        Button saveWorkoutButton = (Button)findViewById(R.id.save_workout_button);
        saveWorkoutButton.setClickable(false);

        final TextView borgValueTextView = (TextView)findViewById(R.id.borg_value_text_view);
        final ImageView borgValueImageView = (ImageView)findViewById(R.id.borg_value_image_view);

        borgValueImageView.setImageResource(R.drawable.smilie_1);

        SeekBar borgschaleSlider = (SeekBar)findViewById(R.id.borgschale_slider);
        borgschaleSlider.setProgress(0);
        borgschaleSlider.setMax(14);
        borgschaleSlider.incrementProgressBy(1);
        borgschaleSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                borgValue = progress + 6;
                borgValueTextView.setText(String.valueOf(borgValue));

                if(borgValue == 6){
                    borgValueImageView.setImageResource(R.drawable.smilie_1);
                }else if(borgValue == 9){
                    borgValueImageView.setImageResource(R.drawable.smilie_2);
                }else if(borgValue == 11){
                    borgValueImageView.setImageResource(R.drawable.smilie_3);
                }else if(borgValue == 13){
                    borgValueImageView.setImageResource(R.drawable.smilie_4);
                }else if(borgValue == 15){
                    borgValueImageView.setImageResource(R.drawable.smilie_5);
                }else if(borgValue == 17){
                    borgValueImageView.setImageResource(R.drawable.smilie_6);
                }else if(borgValue == 19){
                    borgValueImageView.setImageResource(R.drawable.smilie_7);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void workoutRadioButtonClicked(View view) {
        RadioGroup radiogroup_top = (RadioGroup) findViewById(R.id.radiogroup_top);
        RadioGroup radiogroup_bottom = (RadioGroup) findViewById(R.id.radiogroup_bottom);
        Button saveWorkoutButton = (Button)findViewById(R.id.save_workout_button);
        saveWorkoutButton.setClickable(true);

        switch (view.getId()) {
            case R.id.cycle_radio_button: {
                selectedWorkout = "cycle";
                radiogroup_bottom.clearCheck();
                break;
            }
            case R.id.walk_radio_button: {
                selectedWorkout = "walk";
                radiogroup_bottom.clearCheck();
                break;
            }
            case R.id.cardio_radio_button: {
                selectedWorkout = "cardio";
                radiogroup_bottom.clearCheck();
                break;
            }
            case R.id.hometrainer_radio_button: {
                selectedWorkout = "hometrainer";
                radiogroup_top.clearCheck();
                break;
            }
            case R.id.jog_radio_button: {
                selectedWorkout = "jog";
                radiogroup_top.clearCheck();
                break;
            }
            case R.id.nordic_walking_radio_button: {
                selectedWorkout = "nordic walking";
                radiogroup_top.clearCheck();
                break;

            }
        }
    }

    public void save_workout(View view) {

        workoutDB = this.openOrCreateDatabase("WorkoutDatabase.sqlite", MODE_PRIVATE, null);

        String workout_type = selectedWorkout;
        Spinner workoutDurationSpinner = (Spinner) findViewById(R.id.workout_duration_spinner);

        Integer workout_duration = getResources().getIntArray(R.array.workout_duration_spinner_values)
                [workoutDurationSpinner.getSelectedItemPosition()];

        workoutDB.execSQL("INSERT INTO workouts (workout, duration, borgvalue) VALUES ('" + workout_type + "', '" +
                workout_duration + "', '" + borgValue +"');");

        Intent intent = new Intent(this, MyTraining.class);
        startActivity(intent);
    }
}