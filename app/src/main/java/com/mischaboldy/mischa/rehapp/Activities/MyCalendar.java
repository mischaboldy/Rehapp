package com.mischaboldy.mischa.rehapp.Activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;

import com.mischaboldy.mischa.rehapp.R;

import java.util.Calendar;

/**
 * Created by mischa on 10/07/16.
 */
public class MyCalendar extends AppCompatActivity {

    // The indices for the projection array above.
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
    private static final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.my_calendar_title);
        Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( this, new String[] {  Manifest.permission.READ_CALENDAR  },
                    MY_PERMISSIONS_REQUEST_READ_CALENDAR);
        }else{

//            long calID = 1;
//            long startMillis = 0;
//            long endMillis = 0;
//            Calendar beginTime = Calendar.getInstance();
//            beginTime.set(2016, 7, 11, 7, 30);
//            startMillis = beginTime.getTimeInMillis();
//            Calendar endTime = Calendar.getInstance();
//            endTime.set(2016, 7, 11, 10, 45);
//            endMillis = endTime.getTimeInMillis();
//
//            ContentResolver cr2 = getContentResolver();
//            ContentValues values = new ContentValues();
//            values.put(Events.DTSTART, startMillis);
//            values.put(Events.DTEND, endMillis);
//            values.put(Events.TITLE, "Jazzercise");
//            values.put(Events.DESCRIPTION, "Group workout");
//            values.put(Events.CALENDAR_ID, calID);
//            values.put(Events.EVENT_TIMEZONE, "Europe/Amsterdam");
//            Uri uri2 = cr2.insert(Events.CONTENT_URI, values);

            // Retrieve ID for new event
            // get the event ID that is the last element in the Uri
//            long eventID = Long.parseLong(uri2.getLastPathSegment());

//            printCalendarIds();
        }


    }

    private void printCalendarIds() {
        Uri uri = Calendars.CONTENT_URI;
        String[] projection = new String[] {
                Calendars._ID,
                Calendars.ACCOUNT_NAME,
                Calendars.CALENDAR_DISPLAY_NAME,
                Calendars.NAME,
                Calendars.CALENDAR_COLOR
        };

        Cursor cur = managedQuery(uri, projection, null, null, null);
//
//            // Use the cursor to step through the returned records
        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

            Log.w("callID", String.valueOf(calID));
            Log.w("displayName", displayName);
            Log.w("accountName", accountName);
            Log.w("Ownername", ownerName);

        }
    }
}
