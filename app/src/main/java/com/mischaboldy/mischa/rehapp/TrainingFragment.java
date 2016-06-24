package com.mischaboldy.mischa.rehapp;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    boolean cycleChecked;
    boolean walkChecked;
    boolean cardioChecked;
    boolean hometrainerChecked;
    boolean jogChecked;
    boolean nordicWalkingChecked;
    String intensityTypeValue;
    String intensityAmountValue;
    String trainingAmountValue;

    public static TrainingFragment newInstance(boolean cycle, boolean walk,boolean cardio,
                                               boolean hometrainer, boolean jog, boolean nordicWalking,
                                               String intensityType, String intensityAmount, String trainingAmount) {
        TrainingFragment fragmentDemo = new TrainingFragment();
        Bundle args = new Bundle();
        args.putBoolean("cycle", cycle);
        args.putBoolean("walk", walk);
        args.putBoolean("cardio", cardio);
        args.putBoolean("hometrainer", hometrainer);
        args.putBoolean("jog", jog);
        args.putBoolean("nordicWalking", nordicWalking);
        args.putString("intensityType", intensityType);
        args.putString("intensityAmount", intensityAmount);
        args.putString("trainingAmount", trainingAmount);

        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        cycleChecked = getArguments().getBoolean("cycle", false);
        walkChecked = getArguments().getBoolean("walk", false);
        cardioChecked = getArguments().getBoolean("cardio", false);
        hometrainerChecked = getArguments().getBoolean("hometrainer", false);
        jogChecked = getArguments().getBoolean("jog", false);
        nordicWalkingChecked = getArguments().getBoolean("nordicWalking", false);
        intensityTypeValue= getArguments().getString("intensityType", "pre-training");
        intensityAmountValue= getArguments().getString("intensityAmount", "laag");
        trainingAmountValue= getArguments().getString("trainingAmount", "1 keer per week");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.training_fragment, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText("Mijn training");

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

        cycle.setChecked(cycleChecked);
        walk.setChecked(walkChecked);
        cardio.setChecked(cardioChecked);
        hometrainer.setChecked(hometrainerChecked);
        jog.setChecked(jogChecked);
        nordicWalking.setChecked(nordicWalkingChecked);

        return view;
    }
}
