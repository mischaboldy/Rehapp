package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mischaboldy.mischa.rehapp.AchievementTracker;
import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 12/07/16.
 */
public class EnterTest extends AppCompatActivity {

    SQLiteDatabase rehappDB;
    Integer borgValue = 6;
    EditText maxWattageEditText;
    EditText meterEditText;
    EditText levelEditText;
    EditText speedEditText;

    String testType;
    String maxWattage;
    String meter;
    String level;
    String speed;
    String MET;
    String vo2max;

    Integer maxWattInt;
    Integer meterInt;
    Integer levelInt;
    Integer speedTime;
    Float METFloat;
    Integer vo2maxInt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_test);

        rehappDB = openOrCreateDatabase("RehappDatabase.sqlite", MODE_PRIVATE, null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        maxWattageEditText = (EditText) findViewById(R.id.max_watt_input_edit_text);
        meterEditText = (EditText) findViewById(R.id.six_minute_meter_edit_text);
        levelEditText = (EditText) findViewById(R.id.shuttle_level_input_edit_text);
        speedEditText = (EditText) findViewById(R.id.speed_edit_text);
        TextView testInfoTextView = (TextView) findViewById(R.id.title_test_info_form_text_view);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.enter_test_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);
        testInfoTextView.setTypeface(titleTypeFace);

        setCorrectEditTexts("Fietstest");

        final Spinner testTypeSpinner = (Spinner) findViewById(R.id.test_type_spinner);

        testTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setCorrectEditTexts(testTypeSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        final TextView borgValueTextView = (TextView) findViewById(R.id.borg_value_text_view);
        final ImageView borgValueImageView = (ImageView) findViewById(R.id.borg_value_image_view);

        borgValueImageView.setImageResource(R.drawable.smilie_1);

        SeekBar borgschaleSlider = (SeekBar) findViewById(R.id.borgschale_slider);
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

    private void setCorrectEditTexts(String testType) {
        if(testType.equals("Fietstest")){
            maxWattageEditText.setVisibility(View.VISIBLE);
            meterEditText.setVisibility(View.GONE);
            levelEditText.setVisibility(View.GONE);
            speedEditText.setVisibility(View.GONE);
        }else if(testType.equals("6 Minuten wandeltest")){
            maxWattageEditText.setVisibility(View.GONE);
            meterEditText.setVisibility(View.VISIBLE);
            levelEditText.setVisibility(View.GONE);
            speedEditText.setVisibility(View.VISIBLE);
        }else if(testType.equals("Shuttle wandeltest")){
            maxWattageEditText.setVisibility(View.GONE);
            meterEditText.setVisibility(View.GONE);
            levelEditText.setVisibility(View.VISIBLE);
            speedEditText.setVisibility(View.VISIBLE);
        }

    }



    public void saveTest(View view) {

        Spinner testTypeSpinner = (Spinner) findViewById(R.id.test_type_spinner);
        EditText METEditText= (EditText)findViewById(R.id.MET_input_edit_text);
        EditText vo2maxEditText = (EditText)findViewById(R.id.vo2max_edit_text);

        testType = testTypeSpinner.getSelectedItem().toString();
        maxWattage = maxWattageEditText.getText().toString();
        meter = meterEditText.getText().toString();
        level = levelEditText.getText().toString();
        speed = speedEditText.getText().toString();
        MET = METEditText.getText().toString();
        vo2max = vo2maxEditText.getText().toString();

        if(testType.equals("Fietstest")){
            if (TextUtils.isEmpty(maxWattage) || TextUtils.isEmpty(MET) || TextUtils.isEmpty(vo2max)){
                Toast.makeText(this, "Vul alstublieft alle gegevens in", Toast.LENGTH_LONG).show();
            }else{
                saveTestToDatabase(testType);
            }

        }else if (testType.equals("6 Minuten wandeltest")){
            if (TextUtils.isEmpty(meter) || TextUtils.isEmpty(speed) ||
                    TextUtils.isEmpty(MET) || TextUtils.isEmpty(vo2max)){
                Toast.makeText(this, "Vul alstublieft alle gegevens in", Toast.LENGTH_LONG).show();
            }else{
                saveTestToDatabase(testType);
            }
        }else if (testType.equals("Shuttle wandeltest")){
            if (TextUtils.isEmpty(level) || TextUtils.isEmpty(speed) ||
                    TextUtils.isEmpty(MET) || TextUtils.isEmpty(vo2max)){
                Toast.makeText(this, "Vul alstublieft alle gegevens in", Toast.LENGTH_LONG).show();
            }else{
                saveTestToDatabase(testType);
            }
        }
    }

    private void saveTestToDatabase(String input) {

        String queryString = "";
        METFloat  = Float.parseFloat(MET);
        vo2maxInt  = Integer.parseInt(vo2max);

        if(input.equals("Fietstest")){
            maxWattInt = Integer.parseInt(maxWattage);

            queryString = "INSERT INTO test (test_type, max_watt, MET, vo2max, borgvalue) " +
                    "VALUES ('" + testType + "', '" + maxWattInt + "', '" +
                    METFloat + "', '" + vo2maxInt + "', '" +  borgValue +"');";
        }else if(input.equals("6 Minuten wandeltest")){
            meterInt = Integer.parseInt(meter);
            speedTime  = Integer.parseInt(speed);

            queryString = "INSERT INTO test (test_type, meter, " +
                    "speed, MET, vo2max, borgvalue) VALUES ('" + testType + "', '" +
                    meterInt + "', '" + speedTime + "', '" + METFloat + "', '" +
                    vo2maxInt + "', '" +  borgValue +"');";
        }else if(input.equals("Shuttle wandeltest")){
            levelInt  = Integer.parseInt(level);
            speedTime  = Integer.parseInt(speed);

            queryString = "INSERT INTO test (test_type, " +
                    "level, speed, MET, vo2max, borgvalue) VALUES ('" + testType + "', '" +
                    levelInt + "', '" + speedTime + "', '" + METFloat + "', '" +
                    vo2maxInt + "', '" +  borgValue +"');";
        }

        try{
            rehappDB.execSQL(queryString);
        }
        catch (Exception e){
            Log.e("CONTACTS ERROR", "Error inputting training");
        }

        Toast.makeText(this, "Testgegevens opgeslagen", Toast.LENGTH_LONG).show();
        AchievementTracker.trackAchievement(this, "test");

        Intent intent = new Intent(this, MyWorkouts.class);
        intent.putExtra("FROM_TEST", true);
        startActivity(intent);
    }
}
