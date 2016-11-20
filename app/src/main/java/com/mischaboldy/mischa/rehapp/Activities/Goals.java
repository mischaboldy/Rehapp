package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 05/07/16.
 */
public class Goals extends AppCompatActivity {

    private static final String PREFS_NAME = "GoalsPrefsFile";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        SharedPreferences goalsPreferences = getSharedPreferences(PREFS_NAME, 0);
        boolean goalChosen = goalsPreferences.getBoolean("chosen", false);
        String goal = goalsPreferences.getString("goal", "goal_1");

        Integer goalImage = getResources().getIdentifier(goal,
                "drawable","com.mischaboldy.mischa.rehapp");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.goals_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        Button goalButton = (Button) findViewById(R.id.choose_goal_button);
        ImageButton currentGoalImageView = (ImageButton) findViewById(R.id.current_goal_image_view);

        if(goalChosen){
            goalButton.setVisibility(View.GONE);
            currentGoalImageView.setVisibility(View.VISIBLE);
            currentGoalImageView.setImageResource(goalImage);
        }else{
            goalButton.setVisibility(View.VISIBLE);
            currentGoalImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.help_button) {
            InfoBoxHelper.openBox(this, "goals");
        }
        return super.onOptionsItemSelected(item);
    }

    public void createGoal(View view) {
        Intent intent = new Intent(this, SetGoal.class);
        startActivity(intent);
    }

    public void setGoal(View view) {
        Intent intent = new Intent(this, SetGoal.class);
        startActivity(intent);
    }
}