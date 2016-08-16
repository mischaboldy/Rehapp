package com.mischaboldy.mischa.rehapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 16/08/16.
 */
public class ProgramNotEnteredFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_program_not_entered, container, false);

        return view;
    }
}
