package com.mischaboldy.mischa.rehapp.ListViewAdapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mischaboldy.mischa.rehapp.R;
import com.mischaboldy.mischa.rehapp.Users;

import java.util.ArrayList;

public class LeaderboardListViewAdapter extends ArrayAdapter<Users> {

    private final Context mContext;
    private final ArrayList<Users> leaderList;

    public LeaderboardListViewAdapter(Context context, ArrayList<Users> leaderList){
        super(context, R.layout.row_layout_leaderboard, leaderList);

        this.mContext = context;
        this.leaderList = leaderList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater theInflater = LayoutInflater.from(mContext);
        final View theView = theInflater.inflate(R.layout.row_layout_leaderboard,
                parent, false);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        ImageView leaderboardImageView = (ImageView) theView.findViewById(R.id.leaderboard_image_view);
        TextView medalsTextView = (TextView) theView.findViewById(R.id.leaderboard_medals_text_view);
        TextView usernameTextView = (TextView) theView.findViewById(R.id.leaderboard_user_name_text_view);
        String userName = sharedPref.getString("name", "");
        String name = leaderList.get(position).getUserName();


        if(leaderList.get(position).getUserName().equals(userName)){
            theView.setBackground(theView.getResources().getDrawable(R.drawable.background_user_medal));
            if(name.equals("")){
                name = "geen gebruikersnaam";
            }
        }

        String imageName = "medal_nr_" + (position + 1);
        int id = mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
        leaderboardImageView.setImageResource(id);


        usernameTextView.setText(name);
        Typeface titleTypeFace = Typeface.createFromAsset(theView.getContext().getAssets(), "fonts/KeepCalm-Medium.ttf");
        usernameTextView.setTypeface(titleTypeFace);

        medalsTextView.setText(leaderList.get(position).getMedals() + " medailles verdiend" );
        return theView;
    }
}
