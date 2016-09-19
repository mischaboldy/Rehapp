package com.mischaboldy.mischa.rehapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by mischa on 16/08/16.
 */
public class InfoBoxHelper {

    public static void openBox(Context context, String boxChoice) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String message = "";

        switch (boxChoice) {
            case "mainView":
                message = "test";
                break;
            case "myProfile":
                message = "Dit is uw profiel. Hier kunt u uw persoonlijke gegevens invullen. Deze gegevens heeft Rehapp nodig om zaken zoals uw normaalwaarden te berekenen. Vult u deze daarom alstublieft in.";
                break;
            case "goals":
                message = "test";
                break;
            case "setGoals":
                message = "test";
                break;
            case "Buddies":
                message = "test";
                break;
            case "myWorkouts":
                message = "Hier kunt u al uw workouts inkijken en nieuwe workouts invoeren. U kunt individuele workouts verwijderen door op het rode kruis in de bovenhoek te drukken.";
                break;
            case "enterWorkout":
                message = "test";
                break;
            case "saveWorkout":
                message = "test";
                break;
            case "registerWorkout":
                message = "test";
                break;
            case "myCalendar":
                message = "Dit is uw agenda. Als u een trainingsprogramma hebt geselecteerd dan kan u hier zien wanneer u uw workouts staan ingepland. U kunt hier ook uw planning aanpassen als u dat wilt.";
                break;
            case "trainingprogram":
                message = "test";
                break;
            case "settings":
                message = "test";
                break;
            case "info":
                message = "test";
                break;
            case "help":
                message = "test";
                break;
            case "ViewBuddies":
                message = "test";
                break;
        }

        builder.setMessage(message)
                .setTitle("Meer informatie")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
}
