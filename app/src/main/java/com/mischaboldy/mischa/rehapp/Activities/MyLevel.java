package com.mischaboldy.mischa.rehapp.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.ExperienceTracker;
import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.R;
import com.mischaboldy.mischa.rehapp.TextProgressBar;

/**
 * Created by mischa on 05/07/16.
 */
public class MyLevel extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_level);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.my_level_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        TextProgressBar textProgress;
        textProgress = (TextProgressBar)findViewById(R.id.progressBar1);

        TextView totalExpTextView = (TextView) findViewById(R.id.experience_total_text_view);
        TextView mixedExpTextView = (TextView) findViewById(R.id.experience_mixed_text_view);

        Integer [] ExperienceArray = ExperienceTracker.getExperience(this);
        totalExpTextView.setText(ExperienceArray[0] + ExperienceArray[1] + " / 456 Ervaring") ;
        mixedExpTextView.setText(ExperienceArray[0] + " workout punten & \n" + ExperienceArray[1] + " schema punten") ;
        textProgress.setMax(456);
        textProgress.setProgress(ExperienceArray[0]); //
        textProgress.setSecondaryProgress(ExperienceArray[1]); //green
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.help_button){
            InfoBoxHelper.openBox(this, "myLevel");
        }
        return super.onOptionsItemSelected(item);
    }
}