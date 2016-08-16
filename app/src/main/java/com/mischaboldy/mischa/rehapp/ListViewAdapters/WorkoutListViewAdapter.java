package com.mischaboldy.mischa.rehapp.ListViewAdapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mischaboldy.mischa.rehapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WorkoutListViewAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private ArrayList<String> mValues = new ArrayList<String>();

    public WorkoutListViewAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.row_layout_workouts, values);
        this.mContext = context;
        this.mValues = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final LayoutInflater theInflater =  LayoutInflater.from(getContext());

        final View theView = theInflater.inflate(R.layout.row_layout_workouts,
                parent, false);

        String workout = getItem(position);
        String[] workoutArray = workout.split(":");
        final Integer id = Integer.parseInt(workoutArray[0]);
        String workout_type = workoutArray[1] + "_bare";
        String duration = workoutArray[2] + " minuten";
        String borgValueStart = workoutArray[3];
        String borgValueEnd = workoutArray[4];

        Date date = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(workoutArray[5]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");

        String dateString = fmtOut.format(date);
        String workoutText = duration + "\n" + borgValueStart + "\n" + borgValueEnd + "\n" + dateString;

        TextView workoutRowTextView = (TextView)theView.findViewById(R.id.workout_row_text_view);
        ImageView workoutRoWImageView = (ImageView) theView.findViewById(R.id.workout_row_image_view);

        Integer workoutImage = theView.getResources().getIdentifier(workout_type,
                "drawable","com.mischaboldy.mischa.rehapp");

        workoutRoWImageView.setImageResource(workoutImage);
        workoutRowTextView.setText(workoutText);

        //Handle buttons and add onClickListeners
        ImageButton deleteButton = (ImageButton)theView.findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                removeWorkout(id);
                notifyDataSetChanged();
                mValues.remove(position);
                Toast.makeText(getContext(), "Workout verwijderd",
                        Toast.LENGTH_SHORT).show();
            }
        });


        return theView;
    }

    private void removeWorkout(int id) {
        SQLiteDatabase rehappDB = mContext.openOrCreateDatabase("RehappDatabase.sqlite", mContext.MODE_PRIVATE, null);
        rehappDB.execSQL("DELETE FROM workouts WHERE id = " + id + ";");
    }


}
