package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 23/06/16.
 */
public class EnterWorkout extends AppCompatActivity {

    private SQLiteDatabase rehappDB = null;
    private String selectedWorkout;
    private int borgValue = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_workout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.enter_workout_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        Button saveWorkoutButton = (Button)findViewById(R.id.start_workout_button);
        saveWorkoutButton.setClickable(false);

        final TextView borgValueTextView = (TextView)findViewById(R.id.borg_value_text_view);
        final ImageView borgValueImageView = (ImageView)findViewById(R.id.borg_value_image_view);

        borgValueImageView.setImageResource(R.drawable.smilie_1);

        SeekBar borgschaleSlider = (SeekBar)findViewById(R.id.borgschale_slider);
        borgschaleSlider.setProgress(0);
        borgschaleSlider.setMax(9);
        borgschaleSlider.incrementProgressBy(1);
        borgschaleSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                borgValue = progress + 1;
                borgValueTextView.setText(String.valueOf(borgValue));

                if(borgValue == 1){
                    borgValueImageView.setImageResource(R.drawable.smilie_1);
                }else if(borgValue == 3){
                    borgValueImageView.setImageResource(R.drawable.smilie_2);
                }else if(borgValue == 4){
                    borgValueImageView.setImageResource(R.drawable.smilie_3);
                }else if(borgValue == 5){
                    borgValueImageView.setImageResource(R.drawable.smilie_4);
                }else if(borgValue == 6){
                    borgValueImageView.setImageResource(R.drawable.smilie_5);
                }else if(borgValue == 8){
                    borgValueImageView.setImageResource(R.drawable.smilie_6);
                }else if(borgValue == 10){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.help_button){
            InfoBoxHelper.openBox(this, "enterWorkout");
        }
        return super.onOptionsItemSelected(item);
    }

    public void workoutRadioButtonClicked(View view) {
        RadioGroup radiogroup_top = (RadioGroup) findViewById(R.id.radiogroup_top);
        RadioGroup radiogroup_bottom = (RadioGroup) findViewById(R.id.radiogroup_bottom);
        Button saveWorkoutButton = (Button)findViewById(R.id.start_workout_button);
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
                selectedWorkout = "nordic_walking";
                radiogroup_top.clearCheck();
                break;

            }
        }
    }

    public void startWorkout(View view) {
        rehappDB = this.openOrCreateDatabase("RehappDatabase.sqlite", MODE_PRIVATE, null);
        Intent intent = new Intent(this, RegisterWorkout.class);
        intent.putExtra("Workout", selectedWorkout);
        intent.putExtra("BorgValueStart", borgValue);
        startActivity(intent);
    }
}
