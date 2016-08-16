package com.mischaboldy.mischa.rehapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mischaboldy.mischa.rehapp.Activities.MyLevel;

/**
 * Created by mischa on 05/07/16.
 */
public class ExperienceTracker {


    public static final String PREFS_NAME = "ExperienceFile";

    static SharedPreferences experience;
    static SharedPreferences.Editor editor;
    static String experienceTranslated;
    public static void addExperience(Context context, Integer experienceAmount, String experienceType) {
        experience = context.getSharedPreferences(PREFS_NAME, 0);
        editor = experience.edit();
        Integer oldExperience = experience.getInt(experienceType, 0);
        Integer newExperience = oldExperience + experienceAmount;
        editor.putInt(experienceType , newExperience);
        editor.apply();

        if(experienceType.equals("WorkoutExperience")){
            experienceTranslated = "workout punten";
        }else{
            experienceTranslated = "schema punten";

        }
        createDialog(context, experienceAmount.toString() + " " + experienceTranslated).show();
    }

    public static Integer[] getExperience(Context context) {
        experience = context.getSharedPreferences(PREFS_NAME, 0);
        editor = experience.edit();

        Integer[] experienceArray = new Integer[2];
        experienceArray[0] = experience.getInt("WorkoutExperience", 0);;
        experienceArray[1] = experience.getInt("ScheduleExperience", 0);;

        return experienceArray;
    }

        public static void removeExperience(Context context) {
        experience = context.getSharedPreferences(PREFS_NAME, 0);
        editor = experience.edit();

        editor.putInt("WorkoutExperience", 0);
        editor.putInt("ScheduleExperience", 0);
        editor.apply();
    }

    public static AlertDialog createDialog(final Context context, String experienceString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Gefeliciteerd! U heeft "+ experienceString + " verdient.")
                .setTitle("Punten verdient")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.see_experience, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, MyLevel.class);
                        context.startActivity(intent);
                    }
                });

        return builder.create();
    }
}