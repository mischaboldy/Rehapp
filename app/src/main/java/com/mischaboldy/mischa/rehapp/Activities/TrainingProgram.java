package com.mischaboldy.mischa.rehapp.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.DatabaseHelper;
import com.mischaboldy.mischa.rehapp.Fragments.ProgramEnteredFragment;
import com.mischaboldy.mischa.rehapp.Fragments.ProgramNotEnteredFragment;
import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.R;

import java.util.ArrayList;


public class TrainingProgram extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_program);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.title_training_program);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ArrayList<String> testTypeData = DatabaseHelper.getTrainingData(this, "meter", "test");

        if (testTypeData.size() > 0) {
            ProgramEnteredFragment fragment = new ProgramEnteredFragment();
            fragmentTransaction.replace(R.id.program_info_fragment, fragment).commit();
        } else {
            ProgramNotEnteredFragment fragment = new ProgramNotEnteredFragment();
            fragmentTransaction.replace(R.id.program_info_fragment, fragment).commit();
        }
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

    public void enterTest(View view) {
        Intent intent = new Intent(this, EnterTest.class);
        startActivity(intent);
    }

    public void changeProgram(View view) {
        Intent intent = new Intent(this, EnterTrainingProgram.class);
        startActivity(intent);
    }
}