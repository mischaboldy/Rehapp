package com.mischaboldy.mischa.rehapp.ListViewAdapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.R;

/**
 * Created by mischa on 05/07/16.
 */
public class MedalsListViewAdapter extends ArrayAdapter<String> {

    String PREFS_NAME = "MedalsFile";
    private Context mContext;


    public MedalsListViewAdapter(Context context, String[] medals) {
        super(context, R.layout.row_layout_medals, medals);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater theInflater = LayoutInflater.from(getContext());
        final View theView = theInflater.inflate(R.layout.row_layout_medals,
                parent, false);

        TextView titleTextView = (TextView) theView.findViewById(R.id.medal_title_text_view);
        TextView descriptionTextView = (TextView) theView.findViewById(R.id.medal_description_text_view);
        TextView achievedTextView = (TextView) theView.findViewById(R.id.medal_achieved_text_view);
        ImageView medalImageView = (ImageView) theView.findViewById(R.id.medal_image_view);

        String medal = getItem(position);
        String title = "";
        String description = "";
        Integer image = 0;
        Integer color = 0;
        String achieved;

        switch (medal) {
            case "workoutMedal":
                title = "Workout medaille";
                image = R.drawable.medal_workout;
                description ="Deze medaille behaalt u wanneer u een workout invoert";
                break;
            case "trainingMedal":
                title = "Trainingsmedaille";
                image = R.drawable.medal_training;
                description ="Deze medaille behaalt u wanneer u een training invoert";
                break;
            case "testMedal":
                title = "Test medaille";
                image = R.drawable.medal_test;
                description ="Deze medaille behaalt u wanneer u een test heeft invoert";
                break;
            case "helpMedal":
                title = "Hulp medaille";
                image = R.drawable.medal_help;
                description ="Deze medaille behaalt u wanneer u het help scherm heeft bekeken";
                break;
            case "infoMedal":
                title = "Informatie medaille";
                image = R.drawable.medal_info;
                description ="Deze medaille behaalt u wanneer u het informatie scherm heeft bekeken";
                break;
        }

        SharedPreferences medals = mContext.getSharedPreferences(PREFS_NAME, 0);

        if (medals.getBoolean(medal, false)){
            achieved = "Medaille behaald";
            color = R.color.green;
        }
        else{
            achieved = "Nog niet behaald";
            color = R.color.red;
        }

        medalImageView.setImageResource(image);
        titleTextView.setText(title);
        Typeface titleTypeFace = Typeface.createFromAsset(theView.getContext().getAssets(), "fonts/KeepCalm-Medium.ttf");
        titleTextView.setTypeface(titleTypeFace);
        descriptionTextView.setText(description);
        achievedTextView.setText(achieved);
        achievedTextView.setTextColor(ContextCompat.getColor(mContext, color));
        return theView;
    }
}
