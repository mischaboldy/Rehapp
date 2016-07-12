package com.mischaboldy.mischa.rehapp.Fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 29/06/16.
 */
public class TestProgressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_test_progress, container, false);

        return theView;
    }
}





//        LineChart mLineChart = (LineChart) view.findViewById(R.id.chart);
//
//        ArrayList<Entry> testTypeValues = new ArrayList<Entry>();
//        ArrayList<Entry> maxWattValues = new ArrayList<Entry>();
//        ArrayList<Entry> meterValues = new ArrayList<Entry>();
//        ArrayList<Entry> levelValues = new ArrayList<Entry>();
//        ArrayList<Entry> speedValues = new ArrayList<Entry>();
//        ArrayList<Entry> METValues = new ArrayList<Entry>();
//        ArrayList<Entry> vo2maxValues = new ArrayList<Entry>();
//        ArrayList<Entry> borgValues = new ArrayList<Entry>();
//
//        ArrayList<String> testTypeData = DatabaseHelper.getTrainingData(getContext(), "test_type", "test");
//        ArrayList<String> maxWattData = DatabaseHelper.getTrainingData(getContext(), "max_watt", "test");
//        ArrayList<String> meterData = DatabaseHelper.getTrainingData(getContext(), "meter", "test");
//        ArrayList<String> levelData = DatabaseHelper.getTrainingData(getContext(), "level", "test");
//        ArrayList<String> speedData = DatabaseHelper.getTrainingData(getContext(), "speed", "test");
//        ArrayList<String> METData = DatabaseHelper.getTrainingData(getContext(), "MET", "test");
//        ArrayList<String> vo2maxData = DatabaseHelper.getTrainingData(getContext(), "vo2max", "test");
//        ArrayList<String> borgValueData = DatabaseHelper.getTrainingData(getContext(), "borgvalue", "test");
//
//
//
//        for(int i = 0; i<testTypeData.size(); i++){

//
////            Entry testTypeEntry = new Entry(Float.parseFloat(testTypeData.get(i)), i);
//            Entry maxWattEntry = new Entry(Float.parseFloat(maxWattData.get(i)), i);
//            Entry meterEntry = new Entry(Float.parseFloat(meterData.get(i)), i);
//            Entry levelEntry = new Entry(Float.parseFloat(levelData.get(i)), i);
//            Entry speedEntry = new Entry(Float.parseFloat(speedData.get(i)), i);
//            Entry METEntry = new Entry(Float.parseFloat(METData.get(i)), i);
//            Entry vo2maxEntry = new Entry(Float.parseFloat(vo2maxData.get(i)), i);
//            Entry borgValueEntry = new Entry(Float.parseFloat(borgValueData.get(i)), i);
//
////            testTypeValues.add(testTypeEntry);
//            maxWattValues.add(maxWattEntry);
//            meterValues.add(meterEntry);
//            levelValues.add(levelEntry);
//            speedValues.add(speedEntry);
//            METValues.add(METEntry);
//            vo2maxValues.add(vo2maxEntry);
//            borgValues.add(borgValueEntry);
//        }

//        LineDataSet testTypeComp = new LineDataSet(testTypeValues, "");
//        testTypeComp.setAxisDependency(YAxis.AxisDependency.LEFT);
//        heartFqRestComp.setColor(R.color.blue);

//        LineDataSet maxWattComp = new LineDataSet(maxWattValues, "max watt");
//        maxWattComp.setAxisDependency(YAxis.AxisDependency.LEFT);
////        heartFqMaxComp.setColor(R.color.red);
//
//        LineDataSet meterComp = new LineDataSet(meterValues, "meter");
//        meterComp.setAxisDependency(YAxis.AxisDependency.LEFT);
////        bloodPressureRestComp.setColor(R.color.black);
//
//        LineDataSet levelComp = new LineDataSet(levelValues, "level");
//        levelComp.setAxisDependency(YAxis.AxisDependency.LEFT);
////        bloodPressureMaxComp.setColor(R.color.yellow);
//
//        LineDataSet speedComp = new LineDataSet(speedValues, "snelheid");
//        speedComp.setAxisDependency(YAxis.AxisDependency.LEFT);
////        bloodSugarComp.setColor(R.color.green);
//
//        LineDataSet METComp = new LineDataSet(METValues, "MET");
//        METComp.setAxisDependency(YAxis.AxisDependency.LEFT);
////        sao2Comp.setColor(R.color.purple);
//
//        LineDataSet vo2maxComp = new LineDataSet(vo2maxValues, "vo2max");
//        vo2maxComp.setAxisDependency(YAxis.AxisDependency.LEFT);
////        sao2Comp.setColor(R.color.purple);
//
//        LineDataSet borgValueComp = new LineDataSet(borgValues, "borg score");
//        borgValueComp.setAxisDependency(YAxis.AxisDependency.LEFT);
////        sao2Comp.setColor(R.color.purple);
//
//        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//        dataSets.add(maxWattComp);
//        dataSets.add(meterComp);
//        dataSets.add(levelComp);
//        dataSets.add(speedComp);
//        dataSets.add(METComp);
//        dataSets.add(vo2maxComp);
//        dataSets.add(borgValueComp);
//
//        ArrayList<String> xValues = new ArrayList<String>();
//
//        xValues  = DatabaseHelper.getTrainingData(getContext(), "dt", "test");
//
//        LineData data = new LineData(xValues, dataSets);
//        mLineChart.setData(data);
//        mLineChart.invalidate(); // refresh
