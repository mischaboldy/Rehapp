package com.mischaboldy.mischa.rehapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 28/06/16.
 */
public class EnterTestFragment extends Fragment {

    Integer borgValue = 6;
    EditText maxWattage;
    EditText meter;
    EditText level;
    EditText speed;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_enter_test_info, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        maxWattage = (EditText) view.findViewById(R.id.max_watt_input_edit_text);
        meter = (EditText) view.findViewById(R.id.six_minute_meter_edit_text);
        level = (EditText) view.findViewById(R.id.shuttle_level_input_edit_text);
        speed = (EditText) view.findViewById(R.id.speed_edit_text);

        TextView appTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        appTitle.setText(R.string.my_profile_title);

        setCorrectEditTexts("Fietstest", view);

        final Spinner testTypeSpinner = (Spinner) view.findViewById(R.id.test_type_spinner);

        testTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setCorrectEditTexts(testTypeSpinner.getSelectedItem().toString(), view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        final TextView borgValueTextView = (TextView)view.findViewById(R.id.borg_value_text_view);
        final ImageView borgValueImageView = (ImageView)view.findViewById(R.id.borg_value_image_view);

        borgValueImageView.setImageResource(R.drawable.smilie_1);

        SeekBar borgschaleSlider = (SeekBar)view.findViewById(R.id.borgschale_slider);
        borgschaleSlider.setProgress(0);
        borgschaleSlider.setMax(14);
        borgschaleSlider.incrementProgressBy(1);
        borgschaleSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                borgValue = progress + 6;
                borgValueTextView.setText(String.valueOf(borgValue));

                if(borgValue == 6){
                    borgValueImageView.setImageResource(R.drawable.smilie_1);
                }else if(borgValue == 9){
                    borgValueImageView.setImageResource(R.drawable.smilie_2);
                }else if(borgValue == 11){
                    borgValueImageView.setImageResource(R.drawable.smilie_3);
                }else if(borgValue == 13){
                    borgValueImageView.setImageResource(R.drawable.smilie_4);
                }else if(borgValue == 15){
                    borgValueImageView.setImageResource(R.drawable.smilie_5);
                }else if(borgValue == 17){
                    borgValueImageView.setImageResource(R.drawable.smilie_6);
                }else if(borgValue == 19){
                    borgValueImageView.setImageResource(R.drawable.smilie_7);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

    private void setCorrectEditTexts(String testType, View view) {
        if(testType.equals("Fietstest")){
            maxWattage.setVisibility(View.VISIBLE);
            meter.setVisibility(View.GONE);
            level.setVisibility(View.GONE);
            speed.setVisibility(View.GONE);
        }else if(testType.equals("6 Minuten wandeltest")){
            maxWattage.setVisibility(View.GONE);
            meter.setVisibility(View.VISIBLE);
            level.setVisibility(View.GONE);
            speed.setVisibility(View.VISIBLE);
        }else if(testType.equals("Shuttle wandeltest")){
            maxWattage.setVisibility(View.GONE);
            meter.setVisibility(View.GONE);
            level.setVisibility(View.VISIBLE);
            speed.setVisibility(View.VISIBLE);
        }

    }
}
