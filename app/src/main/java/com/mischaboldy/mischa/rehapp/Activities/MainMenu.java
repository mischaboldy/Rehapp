package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.DatabaseHelper;
import com.mischaboldy.mischa.rehapp.R;

public class MainMenu extends AppCompatActivity {

    //to do: beginnen met test invoeren! -> trainingsgegevens herleiden en een advies geven voor trainingsprogramma.
    // workout: starten borgscore eerst, timer lopen, zien wie er aan het sporten zijn LIVE updates -> eindigen weer borgscore. en toevoegen aan lijst.
    // informatie icoontjes in menubalk met dialogs per scherm.
    // mainmenu aanpassen naar menu in menubalk, startpagina veranderen naar -> mijn workouts en voortgang!


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        DatabaseHelper.createDatabase(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.app_name);

        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);
    }

    public void open_my_profile(View view) {
        Intent intent = new Intent(this, MyProfile.class);
        startActivity(intent);
    }

    public void open_my_medals(View view) {
        Intent intent = new Intent(this, MyMedals.class);
        startActivity(intent);
    }

    public void open_my_training(View view) {
        Intent intent = new Intent(this, MyWorkouts.class);
        startActivity(intent);
    }

    public void open_settings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void open_extra_info(View view) {
        Intent intent = new Intent(this, Info.class);
        startActivity(intent);    }


    public void open_help(View view) {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);    }

}
