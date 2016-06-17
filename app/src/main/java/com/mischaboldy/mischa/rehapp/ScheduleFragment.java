package com.mischaboldy.mischa.rehapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mischa on 23/06/16.
 */
public class ScheduleFragment extends Fragment {

    String title;
    String intensity;
    String rest;
    String trainingAmount;
    String numberOfWorkouts;

    public static ScheduleFragment newInstance(String title, String intensity, String rest,
            String trainingAmount, String numberOfWorkouts) {
        ScheduleFragment scheduleFragment = new ScheduleFragment();
        Bundle args = new Bundle();

        args.putString("title", title);
        args.putString("intensity", intensity);
        args.putString("rest", rest);
        args.putString("trainingAmount", trainingAmount);
        args.putString("numberOfWorkouts", numberOfWorkouts);

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.schedule_fragment, container, false);

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
        return view;
    }

}
