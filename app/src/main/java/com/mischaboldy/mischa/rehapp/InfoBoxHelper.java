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
                message = "Dit is het overzicht. In dit scherm kunt u statistieken bekijken over uw voortgang.";
                break;
            case "myProfile":
                message = "Dit is uw profiel. Hier kunt u uw persoonlijke gegevens invullen. Deze gegevens heeft Rehapp nodig om zaken zoals uw normaalwaarden te berekenen. Vult u deze daarom alstublieft in.";
                break;
            case "goals":
                message = "Dit zijn uw doelen. In dit overzicht kunt u uw huidige doel bekijke en uw behaalde doelen. Wilt u een doel toevoegen of aanpassen? Klik dan op uw huidige doel.";
                break;
            case "setGoals":
                message = "Hier kunt u uw doel vervangen of een nieuw doel selecteren. Klik op een doel om het te selecteren.";
                break;
            case "Buddies":
                message = "Hier kunt u op zoek naar een maatje bij u in de buurt. Vind moeiteloos lotgenoten om samen mee te bewegen.";
                break;
            case "myWorkouts":
                message = "Hier kunt u al uw workouts inkijken en nieuwe workouts invoeren. U kunt individuele workouts verwijderen door op het rode kruis in de bovenhoek te drukken.";
                break;
            case "enterWorkout":
                message = "Om een workout in te voeren moet u twee dingen selecteren: de gekozen workout en de borgscore op dit moment. Na de workout voert u opnieuw een borgscore in.";
                break;
            case "saveWorkout":
                message = "Wanneer u met uw workout begint drukt u op start. Wanneer u klaar bent zet u de timer stil en klikt u op workout opslaan.";
                break;
            case "registerWorkout":
                message = "Geef hier nu opnieuw een borscore op voor dit moment.";
                break;
            case "myCalendar":
                message = "Dit is uw agenda. Als u een trainingsprogramma hebt geselecteerd dan kan u hier zien wanneer u uw workouts staan ingepland. U kunt hier ook uw planning aanpassen als u dat wilt.";
                break;
            case "trainingprogram":
                message = "Hier kunt u een trainingsprogramma selecteren. Geef uw voorkeuren aan en klik op trainingsprogramma selecteren.";
                break;
            case "settings":
                message = "Hier kunt u algemene instellinge voor de applicatie aanpassen.";
                break;
            case "info":
                message = "In dit scherm kunt u extra informatie vinden. Klik op de verschillende balkjes om meer te lezen.";
                break;
            case "help":
                message = "Hier kunt u hulp vinden bij het gebruik van de applicatie. Klik op de verschillende balkjes om meer te lezen.";
                break;
            case "ViewBuddies":
                message = "Dit zijn uw maatjes.";
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
