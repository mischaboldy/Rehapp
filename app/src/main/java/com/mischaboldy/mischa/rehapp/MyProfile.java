package com.mischaboldy.mischa.rehapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by mischa on 27/06/16.
 */
public class MyProfile extends AppCompatActivity {

    public static final String PREFS_NAME = "ProfileInfoFile";
    boolean profileSaved;
    SQLiteDatabase rehappDB;

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
    Integer borgValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences profileInfo = getSharedPreferences(PREFS_NAME, 0);

        if (profileInfo != null){
            profileSaved= profileInfo.getBoolean("profileSaved", false);
        }else{
            profileSaved = false;
        }

        if(profileSaved){
            setFragment("showProfile");
        }else{
            setFragment("enterProfile");
        }
    }

    public void saveProfile(View view) {
        SharedPreferences trainingPreferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = trainingPreferences.edit();

        EditText name = (EditText) findViewById(R.id.name_input_edit_text);
        EditText dob = (EditText) findViewById(R.id.dob_input_edit_text);
        Spinner sexSpinner = (Spinner) findViewById(R.id.sex_spinner);
        EditText weight = (EditText) findViewById(R.id.weight_input_edit_text);
        EditText height = (EditText) findViewById(R.id.height_input_edit_text);

        editor.putString("name", String.valueOf(name.getText()));
        editor.putString("dob", String.valueOf(dob.getText()));
        editor.putString("sex", String.valueOf(sexSpinner.getSelectedItem()));
        editor.putString("weight", String.valueOf(weight.getText()));
        editor.putString("height", String.valueOf(height.getText()));

        editor.putBoolean("profileSaved", true);
        editor.apply();

        setFragment("showProfile");
    }

    public void updateProfile(View view) {
        SharedPreferences profileInfo = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = profileInfo.edit();
        editor.putBoolean("profileSaved", false);
        editor.apply();

        setFragment("enterProfile");
    }

    public void setFragment(String chosenFragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(chosenFragment.equals("showProfile")){
            ShowProfileFragment fragment = new ShowProfileFragment();
            fragmentTransaction.replace(android.R.id.content, fragment);
        }else if(chosenFragment.equals("enterProfile")){
            EnterProfileFragment fragment = new EnterProfileFragment();
            fragmentTransaction.replace(android.R.id.content, fragment);
        }else if(chosenFragment.equals("enterTraining")){
            EnterTrainingFragment fragment = new EnterTrainingFragment();
            fragmentTransaction.replace(android.R.id.content, fragment);
        }else if(chosenFragment.equals("enterTest")){
            EnterTestFragment fragment = new EnterTestFragment();
            fragmentTransaction.replace(android.R.id.content, fragment);
        }

        fragmentTransaction.commit();
    }

    public void input_training_info(View view) {
        setFragment("enterTraining");
    }

    public void input_test_info(View view) {
        setFragment("enterTest");
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

            setFragment("showProfile");
        }
    }

    public void saveTest(View view) {

        Spinner testTypeSpinner = (Spinner) findViewById(R.id.test_type_spinner);
        EditText maxWattageEditText = (EditText) findViewById(R.id.max_watt_input_edit_text);
        EditText meterEditText = (EditText) findViewById(R.id.six_minute_meter_edit_text);
        EditText levelEditText = (EditText) findViewById(R.id.shuttle_level_input_edit_text);
        EditText speedEditText = (EditText) findViewById(R.id.speed_edit_text);
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
        SeekBar borgschaleSlider = (SeekBar)findViewById(R.id.borgschale_slider);

        METFloat  = Float.parseFloat(MET);
        vo2maxInt  = Integer.parseInt(vo2max);
        borgValue = borgschaleSlider.getProgress() + 6;

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

        setFragment("showProfile");
    }

    public void showProgress(View view) {
        Intent intent = new Intent(this, ShowProgress.class);
        startActivity(intent);
    }
}
