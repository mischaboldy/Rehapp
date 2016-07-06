package com.mischaboldy.mischa.rehapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.mischaboldy.mischa.rehapp.R;
import com.mischaboldy.mischa.rehapp.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by mischa on 29/06/16.
 */
public class WorkoutProgressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_progress, container, false);

        LineChart mLineChart = (LineChart) view.findViewById(R.id.chart);

        ArrayList<Entry> workoutValues = new ArrayList<Entry>();
        ArrayList<Entry> durationValues = new ArrayList<Entry>();
        ArrayList<Entry> borgValues = new ArrayList<Entry>();


        ArrayList<String> heartFqRestData = DatabaseHelper.getTrainingData(getContext(), "workout", "workouts");
        ArrayList<String> durationData = DatabaseHelper.getTrainingData(getContext(), "duration", "workouts");
        ArrayList<String> borgValueData = DatabaseHelper.getTrainingData(getContext(), "borgvalue", "workouts");

        for(int i = 0; i<heartFqRestData.size(); i++){
            Entry durationEntry = new Entry(Float.parseFloat(durationData.get(i)), i);
            Entry borgValueEntry = new Entry(Float.parseFloat(borgValueData.get(i)), i);

            durationValues.add(durationEntry);
            borgValues.add(borgValueEntry);

        }

        LineDataSet durationComp = new LineDataSet(durationValues, "trainingsduur");
        durationComp.setAxisDependency(YAxis.AxisDependency.LEFT);
//        heartFqRestComp.setColor(R.color.blue);

        LineDataSet borgValueComp = new LineDataSet(borgValues, "borg score");
        borgValueComp.setAxisDependency(YAxis.AxisDependency.LEFT);
//        heartFqMaxComp.setColor(R.color.red);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(durationComp);
        dataSets.add(borgValueComp);

        ArrayList<String> xValues = new ArrayList<String>();

        xValues  = DatabaseHelper.getTrainingData(getContext(), "dt", "training");

        LineData data = new LineData(xValues, dataSets);
        mLineChart.setData(data);
        mLineChart.invalidate(); // refresh

        return view;
    }
}
