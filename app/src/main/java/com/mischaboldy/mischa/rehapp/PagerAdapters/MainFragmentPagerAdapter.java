package com.mischaboldy.mischa.rehapp.PagerAdapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mischaboldy.mischa.rehapp.Fragments.TestProgressFragment;
import com.mischaboldy.mischa.rehapp.Fragments.TrainingProgressFragment;
import com.mischaboldy.mischa.rehapp.Fragments.WorkoutProgressFragment;

/**
 * Created by mischa on 30/06/16.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public MainFragmentPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                TrainingProgressFragment tab1 = new TrainingProgressFragment();
                return tab1;
            case 1:
                TestProgressFragment tab2 = new TestProgressFragment();
                return tab2;
            case 2:
                WorkoutProgressFragment tab3 = new WorkoutProgressFragment();
                return tab3;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}