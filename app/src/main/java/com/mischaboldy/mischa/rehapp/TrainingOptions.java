package com.mischaboldy.mischa.rehapp;

import java.lang.reflect.Array;

/**
 * Created by mischa on 21/06/16.
 */
public class TrainingOptions {

    public static String[] getProgram(String trainingChoices) {

        String[] results = new String[3];

        switch (trainingChoices) {
            case "pre-training laag":
                results[0] = "2 weken 40-50%";
                results[2] ="2";
                break;
            case "pre-training middel":
                results[0] = "2 weken 40%";
                results[2] ="2";
                break;
            case "pre-training hoog":
                results[0] = "2 weken 40%";
                results[2] ="2";
                break;

            case "duurtraining laag":
                results[0] = "50%";
                results[2] ="8";
                break;
            case "duurtraining middel":
                results[0] = "60-70%";
                results[2] ="8";
                break;
            case "duurtraining hoog":
                results[0] = "80%";
                results[2] ="8";
                break;

            case "intervaltraining laag":
                results[0] = "4x4 minuten, 70%";
                results[1] = "rust 3 minuten 40%";
                results[2] ="6";
                break;
            case "intervaltraining middel":
                results[0] = "4x4 minuten, 80%";
                results[1] = "rust 3 minuten 40-50%";
                results[2] ="6";
                break;
            case "intervaltraining hoog":
                results[0] = "4x4 minuten, 90%";
                results[1] = "rust 3 minuten 50%";
                results[2] ="6";
                break;

            case "gewichtsreductietraining laag":
                results[0] = "40%";
                results[2] ="8";
                break;
            case "gewichtsreductietraining middel":
                results[0] = "50%";
                results[2] ="8";
                break;
            case "gewichtsreductietraining hoog":
                results[0] = "60%";
                results[2] ="8";
                break;
        }

        return results;
    }
}
