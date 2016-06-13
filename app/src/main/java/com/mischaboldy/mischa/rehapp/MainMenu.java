package com.mischaboldy.mischa.rehapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
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
        Toast.makeText(this, "open my training",
                Toast.LENGTH_LONG).show();
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
