package com.mischaboldy.mischa.rehapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mischaboldy.mischa.rehapp.Activities.MyMedals;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by mischa on 05/07/16.
 */
public class AchievementTracker {


    public static final String PREFS_NAME = "MedalsFile";
    static SharedPreferences medals;
    static SharedPreferences.Editor editor;

    public static void trackAchievement(Context context, String achievementName) {
        medals = context.getSharedPreferences(PREFS_NAME, 0);
        editor = medals.edit();

        if (!medals.getBoolean(achievementName + "Medal", false)) {
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => " + c.getTime());

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String formattedDate = df.format(c.getTime());

            createDialog(context).show();
            editor.putBoolean(achievementName + "Medal", true);
            editor.putString(achievementName + "Date", formattedDate);

            editor.apply();
        }
    }

    public static void removeMedals(Context context) {
        medals = context.getSharedPreferences(PREFS_NAME, 0);
        editor = medals.edit();

        editor.putBoolean("infoMedal", false);
        editor.putBoolean("testMedal", false);
        editor.putBoolean("trainingMedal", false);
        editor.putBoolean("workoutMedal", false);
        editor.putBoolean("helpMedal", false);
        editor.apply();
    }

    public static AlertDialog createDialog(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Gefeliciteerd! U heeft een nieuwe medaille verdient")
                .setTitle("Medaille")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.see_medal, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, MyMedals.class);
                        context.startActivity(intent);
                    }
                });

        return builder.create();
    }
}