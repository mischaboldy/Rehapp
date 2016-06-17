package com.mischaboldy.mischa.rehapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText("Rehapp");

        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);
    }

    // Menu icons are inflated just as they were with actionbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void open_my_profile(View view) {
        Toast.makeText(this, "open my profile",
                Toast.LENGTH_LONG).show();
    }

    public void open_my_medals(View view) {
        Toast.makeText(this, "open my medals",
                Toast.LENGTH_LONG).show();
    }

    public void open_my_training(View view) {
        Intent intent = new Intent(this, MyTraining.class);
        startActivity(intent);
    }

    public void open_settings(View view) {
        Toast.makeText(this, "open my settings",
                Toast.LENGTH_LONG).show();
    }

    public void open_extra_info(View view) {
        Toast.makeText(this, "open my extra info",
                Toast.LENGTH_LONG).show();
    }


    public void open_help(View view) {
        Toast.makeText(this, "open help",
                Toast.LENGTH_LONG).show();
    }

}
