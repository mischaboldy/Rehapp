package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 17/09/16.
 */
public class SetGoal extends AppCompatActivity{
    private static final String PREFS_NAME = "GoalsPrefsFile";
    private String goal_chosen = "goal_1_base";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.set_goal_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);
    }

    public void goalRadioButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.goal_1_button: {
                goal_chosen = "goal_1_base";
                break;
            }
            case R.id.goal_2_button: {
                goal_chosen = "goal_2_base";
                break;
            }
            case R.id.goal_3_button: {
                goal_chosen = "goal_3_base";
                break;
            }
        }
    }

    public void saveGoal(View view) {
        SharedPreferences goalsPreferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = goalsPreferences.edit();
        editor.putBoolean("chosen", true);
        editor.putString("goal", goal_chosen);
        editor.apply();
        Intent intent = new Intent(this, Goals.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.help_button){
            InfoBoxHelper.openBox(this, "setGoals");
        }
        return super.onOptionsItemSelected(item);
    }
}
