package com.mischaboldy.mischa.rehapp;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mischa on 23/06/16.
 */
public class ScheduleFragment extends Fragment {

    String title;
    String intensity;
    String rest;
    String trainingAmount;
    String numberOfWorkouts;
    ArrayList<String> workouts;

    public static ScheduleFragment newInstance(String title, String intensity, String rest,
                                               String trainingAmount, String numberOfWorkouts,
                                               ArrayList<String> workouts) {

        ScheduleFragment scheduleFragment = new ScheduleFragment();
        Bundle args = new Bundle();

        args.putString("title", title);
        args.putString("intensity", intensity);
        args.putString("rest", rest);
        args.putString("trainingAmount", trainingAmount);
        args.putString("numberOfWorkouts", numberOfWorkouts);
        args.putStringArrayList("workouts", workouts);

        scheduleFragment.setArguments(args);
        return scheduleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        title= getArguments().getString("title", "pre-training laag");
        intensity= getArguments().getString("intensity", "50%");
        rest= getArguments().getString("rest", "");
        trainingAmount= getArguments().getString("trainingAmount", "2");
        numberOfWorkouts= getArguments().getString("numberOfWorkouts", "2 keer per week");
        workouts= getArguments().getStringArrayList("workouts");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.schedule_fragment, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        appTitle.setText("Mijn training");

        Typeface titleTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/KeepCalm-Medium.ttf");
        appTitle.setTypeface(titleTypeFace);

        TextView training_title_textview = (TextView) view.findViewById(R.id.training_title_textview);
        TextView training_intensity_textview = (TextView) view.findViewById(R.id.training_intensity_textview);
        TextView training_rest_textview = (TextView) view.findViewById(R.id.training_rest_textview);
        TextView training_amount_textview = (TextView) view.findViewById(R.id.training_amount_textview);
        TextView number_of_workouts_textview = (TextView) view.findViewById(R.id.number_of_workouts_textview);

        training_title_textview.setText(title);
        training_intensity_textview.setText(intensity + " VO2max");
        training_rest_textview.setText(rest);
        training_amount_textview.setText(trainingAmount + " weeks trainingsprogramma");
        number_of_workouts_textview.setText(numberOfWorkouts);

        ListAdapter listAdapter = new workoutListViewAdapter(getActivity().getApplicationContext()
                , workouts);

        ListView listView = (ListView) view.findViewById(R.id.workout_list_view);

        listView.setAdapter(listAdapter);

        return view;
    }

}
