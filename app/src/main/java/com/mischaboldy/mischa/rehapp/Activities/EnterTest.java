package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mischaboldy.mischa.rehapp.DatabaseHelper;
import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 12/07/16.
 */
public class EnterTest extends AppCompatActivity {

    SQLiteDatabase rehappDB;
    EditText meterEditText;
    EditText heartFqRestEditText;
    EditText heartFqMaxEditText;
    EditText heartFqRecEditText;

    String meter;
    String heartFqRest;
    String heartFqMax;
    String heartFqRec;

    Integer meterInt;
    Integer heartFqRestInt;
    Integer heartFqMaxInt;
    Integer heartFqRecInt;

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

        meterEditText = (EditText) findViewById(R.id.six_minute_meter_edit_text);
        heartFqRestEditText = (EditText) findViewById(R.id.heart_fq_rest_input_edit_text);
        heartFqMaxEditText = (EditText) findViewById(R.id.heart_fq_max_input_edit_text);
        heartFqRecEditText = (EditText) findViewById(R.id.heart_fq_rec_input_edit_text);

        TextView testInfoTextView = (TextView) findViewById(R.id.title_test_info_form_text_view);
        TextView testInfoSubTextView = (TextView) findViewById(R.id.subtitle_test_info_form_text_view);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.enter_test_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);
        testInfoTextView.setTypeface(titleTypeFace);
        testInfoSubTextView.setTypeface(titleTypeFace);

    }

    public void saveTest(View view) {

        meter = meterEditText.getText().toString();
        heartFqRest = heartFqRestEditText.getText().toString();
        heartFqMax = heartFqMaxEditText.getText().toString();
        heartFqRec = heartFqRecEditText.getText().toString();

        if (TextUtils.isEmpty(meter) || TextUtils.isEmpty(heartFqRest) ||
                TextUtils.isEmpty(heartFqMax) || TextUtils.isEmpty(heartFqRec)){
            Toast.makeText(this, "Vul alstublieft alle gegevens in", Toast.LENGTH_LONG).show();
        }else{
            saveTestToDatabase();
        }
    }

    private void saveTestToDatabase() {
        meterInt  = Integer.parseInt(meter);
        heartFqRestInt  = Integer.parseInt(heartFqRest);
        heartFqMaxInt = Integer.parseInt(heartFqMax);
        heartFqRecInt = Integer.parseInt(heartFqRec);

        String queryString = "INSERT INTO test (meter, heartfqrest, heartfqmax, heartfqrec) VALUES ('" + meterInt + "', '" +
                heartFqRestInt + "', '" + heartFqMaxInt + "', '" + heartFqRecInt + "');";

        DatabaseHelper.insertIntoDatabase(queryString);

        Toast.makeText(this, "Testgegevens opgeslagen", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, SuggestTrainingProgram.class);
        startActivity(intent);
    }
}
