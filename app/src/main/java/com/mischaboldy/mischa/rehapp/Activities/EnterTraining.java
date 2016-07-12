package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mischaboldy.mischa.rehapp.AchievementTracker;
import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 12/07/16.
 */
public class EnterTraining extends AppCompatActivity {

    SQLiteDatabase rehappDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_training);

        rehappDB = openOrCreateDatabase("RehappDatabase.sqlite", MODE_PRIVATE, null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView trainingInfoTextView = (TextView) findViewById(R.id.title_training_info_form_text_view);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.input_training_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);
        trainingInfoTextView.setTypeface(titleTypeFace);

    }

    public void saveTraining(View view) {

        EditText heartFqRestEditText = (EditText)findViewById(R.id.heart_fq_rest_input_edit_text);
        EditText heartFqMaxtEditText = (EditText)findViewById(R.id.heart_fq_max_input_edit_text);
        EditText bloodPressureRestEditText = (EditText)findViewById(R.id.blood_pressure_rest_input_edit_text);
        EditText bloodPressureMaxEditText = (EditText)findViewById(R.id.blood_pressure_max_input_edit_text);
        EditText bloodSugarEditText = (EditText)findViewById(R.id.blood_sugar_input_edit_text);
        EditText sao2EditText = (EditText)findViewById(R.id.sao2_input_edit_text);

        String heartFqRest = heartFqRestEditText.getText().toString();
        String heartFqMax = heartFqMaxtEditText.getText().toString();
        String bloodPressureRest = bloodPressureRestEditText.getText().toString();
        String bloodPressureMax =bloodPressureMaxEditText.getText().toString();
        String bloodSugar = bloodSugarEditText.getText().toString();
        String sao2 = sao2EditText.getText().toString();

        if(TextUtils.isEmpty(heartFqRest) || TextUtils.isEmpty(heartFqMax) ||
                TextUtils.isEmpty(bloodPressureRest) || TextUtils.isEmpty(bloodPressureMax) ||
                TextUtils.isEmpty(bloodSugar) || TextUtils.isEmpty(sao2)){

            Toast.makeText(this, "Vul alstublieft alle gegevens in", Toast.LENGTH_LONG).show();
        }else{

            Integer heartFqRestInt = Integer.parseInt(heartFqRest);
            Integer heartFqMaxInt = Integer.parseInt(heartFqMax);
            Integer bloodPressureRestInt = Integer.parseInt(bloodPressureRest);
            Integer bloodPressureMaxInt = Integer.parseInt(bloodPressureMax);
            Integer bloodSugarInt = Integer.parseInt(bloodSugar);
            Integer sao2Int = Integer.parseInt(sao2);

            try{
                rehappDB.execSQL("INSERT INTO training (heart_fq_rest, heart_fq_max, blood_pressure_rest, " +
                        "blood_pressure_max, blood_sugar, sao2) VALUES ('" + heartFqRestInt + "', '" +
                        heartFqMaxInt + "', '" + bloodPressureRestInt + "', '" + bloodPressureMaxInt +
                        "', '" + bloodSugarInt + "', '" +  sao2Int + "');");
            }
            catch (Exception e){
                Log.e("CONTACTS ERROR", "Error inputting training");
            }

            Toast.makeText(this, "Trainingsgegevens opgeslagen", Toast.LENGTH_LONG).show();
            AchievementTracker.trackAchievement(this, "training");

            Intent intent = new Intent(this, MyWorkouts.class);
            intent.putExtra("FROM_TRAINING", true);
            startActivity(intent);
        }
    }
}
