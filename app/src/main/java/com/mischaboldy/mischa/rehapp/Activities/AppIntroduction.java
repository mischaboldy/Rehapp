package com.mischaboldy.mischa.rehapp.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 08/07/16.
 */
public class AppIntroduction extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_introduction);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.app_name);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        //temporary fix to not show introduction:
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }


    public void startRehapp(View view) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
