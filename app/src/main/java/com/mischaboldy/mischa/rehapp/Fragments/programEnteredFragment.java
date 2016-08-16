package com.mischaboldy.mischa.rehapp.Fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 16/08/16.
 */
public class ProgramEnteredFragment extends Fragment {

    public static final String PREFS_NAME = "TrainingProgramPrefsFile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_program_entered, container, false);
        ImageView programImageView = (ImageView)view.findViewById(R.id.program_image_view);

        Integer imageResource = 0;
        SharedPreferences TrainingProgramPreferences = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String chosenProgram = TrainingProgramPreferences.getString("chosenProgram", "");

        if(chosenProgram.equals("firstProgram")){
            imageResource = R.drawable.program_one_base;
        }else if (chosenProgram.equals("secondProgram")){
            imageResource = R.drawable.program_two_base;
        }

        programImageView.setImageResource(imageResource);

        return view;
    }
}
