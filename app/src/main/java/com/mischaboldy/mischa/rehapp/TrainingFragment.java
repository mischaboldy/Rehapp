package com.mischaboldy.mischa.rehapp;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by mischa on 23/06/16.
 */
public class TrainingFragment extends Fragment {

    public static final String PREFS_NAME = "TrainingPrefsFile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_training, container, false);
        SharedPreferences trainingPreferences = getActivity().getSharedPreferences(PREFS_NAME, 0);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText(R.string.my_training_title);

        Typeface titleTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        CheckBox cycle = (CheckBox) view.findViewById(R.id.cycle_button);
        CheckBox walk = (CheckBox) view.findViewById(R.id.walk_button);
        CheckBox cardio = (CheckBox) view.findViewById(R.id.cardio_button);
        CheckBox hometrainer = (CheckBox) view.findViewById(R.id.hometrainer_button);
        CheckBox jog = (CheckBox) view.findViewById(R.id.jog_button);
        CheckBox nordicWalking = (CheckBox) view.findViewById(R.id.nordic_walking_button);

        Spinner intensityTypeSpinner = (Spinner) view.findViewById(R.id.intensity_type_spinner);
        Spinner intensityAmountSpinner = (Spinner) view.findViewById(R.id.intensity_amount_spinner);
        Spinner trainingAmountSpinner = (Spinner) view.findViewById(R.id.training_amount_spinner);

        boolean cycleChecked = trainingPreferences.getBoolean("cycle", false);
        boolean walkChecked = trainingPreferences.getBoolean("walk", false);
        boolean cardioChecked = trainingPreferences.getBoolean("cardio", false);
        boolean hometrainerChecked = trainingPreferences.getBoolean("hometrainer", false);
        boolean jogChecked = trainingPreferences.getBoolean("jog", false);
        boolean nordicWalkingChecked = trainingPreferences.getBoolean("nordicWalking", false);
        String intensityType = trainingPreferences.getString("intensityType", "pre-training");
        String intensityAmount = trainingPreferences.getString("intensityAmount", "laag");
        String numberOfWorkouts = trainingPreferences.getString("numberOfWorkouts", "1 keer per week");

        intensityTypeSpinner.setSelection(getIndex(intensityTypeSpinner, intensityType));
        intensityAmountSpinner.setSelection(getIndex(intensityAmountSpinner, intensityAmount));
        trainingAmountSpinner.setSelection(getIndex(trainingAmountSpinner, numberOfWorkouts));

        cycle.setChecked(cycleChecked);
        walk.setChecked(walkChecked);
        cardio.setChecked(cardioChecked);
        hometrainer.setChecked(hometrainerChecked);
        jog.setChecked(jogChecked);
        nordicWalking.setChecked(nordicWalkingChecked);

        return view;
    }

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }
}
