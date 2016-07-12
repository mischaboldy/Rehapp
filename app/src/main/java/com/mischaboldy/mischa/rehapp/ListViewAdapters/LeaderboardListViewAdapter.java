package com.mischaboldy.mischa.rehapp.ListViewAdapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.R;
import com.mischaboldy.mischa.rehapp.Users;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardListViewAdapter extends ArrayAdapter<Users> {

    private final Context mContext;
    private final ArrayList<Users> leaderList;

    public LeaderboardListViewAdapter(Context context, ArrayList<Users> leaderList){
        super(context, R.layout.leaderboard_row_layout, leaderList);

        this.mContext = context;
        this.leaderList = leaderList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater theInflater = LayoutInflater.from(mContext);
        final View theView = theInflater.inflate(R.layout.leaderboard_row_layout,
                parent, false);

        ImageView leaderboardImageView = (ImageView) theView.findViewById(R.id.leaderboard_image_view);

        TextView medalsTextView = (TextView) theView.findViewById(R.id.leaderboard_medals_text_view);
        TextView usernameTextView = (TextView) theView.findViewById(R.id.leaderboard_user_name_text_view);

        leaderboardImageView.setImageResource(R.drawable.smilie_1);

        usernameTextView.setText("Gebruiker: " + leaderList.get(position).getUserName());
        Typeface titleTypeFace = Typeface.createFromAsset(theView.getContext().getAssets(), "fonts/KeepCalm-Medium.ttf");
        usernameTextView.setTypeface(titleTypeFace);

        medalsTextView.setText("Medailles: " + leaderList.get(position).getMedals());

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userName = sharedPref.getString("name", "");
        if(leaderList.get(position).getUserName().equals(userName)){
            theView.setBackgroundColor(theView.getResources().getColor(R.color.green));
        }

        return theView;
    }
}
