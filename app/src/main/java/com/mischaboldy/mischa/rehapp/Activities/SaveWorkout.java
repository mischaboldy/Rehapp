package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.DatabaseHelper;
import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 20/08/16.
 */
public class SaveWorkout extends AppCompatActivity {

    String workout;
    Integer workout_duration;
    Integer borgValueStart;
    Integer borgValueEnd = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_workout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.save_workout_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            workout = extras.getString("Workout");
            borgValueStart = extras.getInt("BorgValueStart");
            workout_duration = extras.getInt("Time");
        }


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
                borgValueEnd = progress + 1;
                borgValueTextView.setText(String.valueOf(borgValueEnd));

                if(borgValueEnd == 1){
                    borgValueImageView.setImageResource(R.drawable.smilie_1);
                }else if(borgValueEnd == 3){
                    borgValueImageView.setImageResource(R.drawable.smilie_2);
                }else if(borgValueEnd == 4){
                    borgValueImageView.setImageResource(R.drawable.smilie_3);
                }else if(borgValueEnd == 5){
                    borgValueImageView.setImageResource(R.drawable.smilie_4);
                }else if(borgValueEnd == 6){
                    borgValueImageView.setImageResource(R.drawable.smilie_5);
                }else if(borgValueEnd == 8){
                    borgValueImageView.setImageResource(R.drawable.smilie_6);
                }else if(borgValueEnd == 10){
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
            InfoBoxHelper.openBox(this, "saveWorkout");
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveWorkout(View view) {
        String query = "INSERT INTO workouts (workout, duration, borgvalue_start, borgvalue_end) VALUES ('" + workout + "', '" +
                workout_duration + "', '" + borgValueStart + "', '" + borgValueEnd + "');";
        DatabaseHelper.insertIntoDatabase(query);
        Intent intent = new Intent(this, MyWorkouts.class);
        intent.putExtra("WORKOUTADDED", true);
        startActivity(intent);
    }
}
