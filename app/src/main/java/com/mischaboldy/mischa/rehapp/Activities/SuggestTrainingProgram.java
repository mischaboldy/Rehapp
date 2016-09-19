package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 17/08/16.
 */
public class SuggestTrainingProgram extends AppCompatActivity {

    private String chosenProgram = "";
    private static final String PREFS_NAME = "TrainingProgramPrefsFile";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_training_program);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.title_suggest_training_program);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

    }

    public void selectTrainingProgram(View view) {

        if(chosenProgram.equals("")){
            Toast.makeText(this, "Kies alstublieft een trainingsprogramma", Toast.LENGTH_LONG).show();
        }else{
            saveTrainingProgram(chosenProgram);
            Intent intent = new Intent(this, TrainingProgram.class);
            startActivity(intent);
        }
    }

    public void chooseTrainingProgram(View view) {
        Intent intent = new Intent(this, EnterTrainingProgram.class);
        startActivity(intent);
    }

    private void saveTrainingProgram(String chosenProgram) {
        SharedPreferences trainingPreferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = trainingPreferences.edit();

        editor.putString("chosenProgram", chosenProgram);
        editor.putBoolean("workoutSaved", true);
        editor.apply();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.first_program_radio_button:
                if (checked)
                    chosenProgram = "firstProgram";
                    break;
            case R.id.second_program_radio_button:
                if (checked)
                    chosenProgram = "secondProgram";
                    break;
        }
    }
}
