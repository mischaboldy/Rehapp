package com.mischaboldy.mischa.rehapp.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.InfoBoxHelper;
import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 20/08/16.
 */
public class RegisterWorkout extends AppCompatActivity {

    private Button butnstart;
    private TextView time;
    private long starttime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedtime = 0L;
    private int t = 1;
    private int secs = 0;
    private int mins = 0;
    private int milliseconds = 0;
    private Handler handler = new Handler();
    private String Workout;
    private String BorgValueStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_workout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.register_workout_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Workout = extras.getString("Workout");
            BorgValueStart = extras.getString("BorgValueStart");
        }

            butnstart = (Button) findViewById(R.id.start);
            time = (TextView) findViewById(R.id.timer);

            butnstart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (t == 1) {
                        butnstart.setText("Pause");
                        starttime = SystemClock.uptimeMillis();
                        handler.postDelayed(updateTimer, 0);
                        t = 0;
                    } else {
                        butnstart.setText("Workout voortzetten");
                        time.setTextColor(Color.BLUE);
                        timeSwapBuff += timeInMilliseconds;
                        handler.removeCallbacks(updateTimer);
                        t = 1;
                    }}
            });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.help_button){
            InfoBoxHelper.openBox(this, "registerWorkout");
        }
        return super.onOptionsItemSelected(item);
    }

    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
            updatedtime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedtime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            milliseconds = (int) (updatedtime % 1000);
            time.setText("" + mins + ":" + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            time.setTextColor(Color.RED);
            handler.postDelayed(this, 0);
        }
    };

    public void saveWorkout(View view) {
        Intent intent = new Intent(this, SaveWorkout.class);
        intent.putExtra("Workout", Workout);
        intent.putExtra("BorgValueStart", BorgValueStart);
        intent.putExtra("Time", mins);
        startActivity(intent);
    }
}
