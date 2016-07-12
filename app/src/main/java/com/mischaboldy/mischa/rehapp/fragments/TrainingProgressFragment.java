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
        import com.mischaboldy.mischa.rehapp.DatabaseHelper;
        import com.mischaboldy.mischa.rehapp.R;

        import java.util.ArrayList;

/**
 * Created by mischa on 29/06/16.
 */
public class TrainingProgressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_training_progress, container, false);

        LineChart mLineChart = (LineChart) theView.findViewById(R.id.chart);

        ArrayList<Entry> heartFqRestValues = new ArrayList<Entry>();
        ArrayList<Entry> heartFqMaxValues = new ArrayList<Entry>();
        ArrayList<Entry> bloodPressureRestValues = new ArrayList<Entry>();
        ArrayList<Entry> bloodPressureMaxValues = new ArrayList<Entry>();
        ArrayList<Entry> bloodSugarValues = new ArrayList<Entry>();
        ArrayList<Entry> sao2Values = new ArrayList<Entry>();

        ArrayList<String> heartFqRestData = DatabaseHelper.getTrainingData(getContext(), "heart_fq_rest", "training");
        ArrayList<String> heartFqMaxData = DatabaseHelper.getTrainingData(getContext(), "heart_fq_max", "training");
        ArrayList<String> bloodPressureRestData = DatabaseHelper.getTrainingData(getContext(), "blood_pressure_rest", "training");
        ArrayList<String> bloodPressureMaxData = DatabaseHelper.getTrainingData(getContext(), "blood_pressure_max", "training");
        ArrayList<String> bloodSugarData = DatabaseHelper.getTrainingData(getContext(), "blood_sugar", "training");
        ArrayList<String> sao2Data = DatabaseHelper.getTrainingData(getContext(), "sao2", "training");

        for(int i = 0; i<heartFqRestData.size(); i++){
            Entry heartFqRestEntry = new Entry(Float.parseFloat(heartFqRestData.get(i)), i);
            Entry heartFqMaxEntry = new Entry(Float.parseFloat(heartFqMaxData.get(i)), i);
            Entry bloodPressureRestEntry = new Entry(Float.parseFloat(bloodPressureRestData.get(i)), i);
            Entry bloodPressureMaxEntry = new Entry(Float.parseFloat(bloodPressureMaxData.get(i)), i);
            Entry bloodSugarEntry = new Entry(Float.parseFloat(bloodSugarData.get(i)), i);
            Entry sao2Entry = new Entry(Float.parseFloat(sao2Data.get(i)), i);

            heartFqRestValues.add(heartFqRestEntry);
            heartFqMaxValues.add(heartFqMaxEntry);
            bloodPressureRestValues.add(bloodPressureRestEntry);
            bloodPressureMaxValues.add(bloodPressureMaxEntry);
            bloodSugarValues.add(bloodSugarEntry);
            sao2Values.add(sao2Entry);
        }

        LineDataSet heartFqRestComp = new LineDataSet(heartFqRestValues, "Hart frequentie in rust");
        heartFqRestComp.setAxisDependency(YAxis.AxisDependency.LEFT);
//        heartFqRestComp.setColor(R.color.blue);

        LineDataSet heartFqMaxComp = new LineDataSet(heartFqMaxValues, "Hart frequentie max");
        heartFqMaxComp.setAxisDependency(YAxis.AxisDependency.LEFT);
//        heartFqMaxComp.setColor(R.color.red);

        LineDataSet bloodPressureRestComp = new LineDataSet(bloodPressureRestValues, "bloeddruk in rust");
        bloodPressureRestComp.setAxisDependency(YAxis.AxisDependency.LEFT);
//        bloodPressureRestComp.setColor(R.color.black);

        LineDataSet bloodPressureMaxComp = new LineDataSet(bloodPressureMaxValues, "bloeddruk in rust");
        bloodPressureMaxComp.setAxisDependency(YAxis.AxisDependency.LEFT);
//        bloodPressureMaxComp.setColor(R.color.yellow);

        LineDataSet bloodSugarComp = new LineDataSet(bloodSugarValues, "bloedsuiker");
        bloodSugarComp.setAxisDependency(YAxis.AxisDependency.LEFT);
//        bloodSugarComp.setColor(R.color.green);

        LineDataSet sao2Comp = new LineDataSet(sao2Values, "sao2");
        sao2Comp.setAxisDependency(YAxis.AxisDependency.LEFT);
//        sao2Comp.setColor(R.color.purple);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(heartFqRestComp);
        dataSets.add(heartFqMaxComp);
        dataSets.add(bloodPressureRestComp);
        dataSets.add(bloodPressureMaxComp);
        dataSets.add(bloodSugarComp);
        dataSets.add(sao2Comp);

        ArrayList<String> xValues = new ArrayList<String>();

        xValues  = DatabaseHelper.getTrainingData(getContext(), "dt", "training");

        LineData data = new LineData(xValues, dataSets);
        mLineChart.setData(data);
        mLineChart.invalidate(); // refresh

        return theView;
    }
}
