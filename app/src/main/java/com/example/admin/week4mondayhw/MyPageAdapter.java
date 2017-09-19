package com.example.admin.week4mondayhw;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Admin on 9/18/2017.
 */

class MyPageAdapter extends FragmentPagerAdapter {

    private List<WeatherFragment> fragments;


    public MyPageAdapter(FragmentManager fm, List<WeatherFragment> fragments) {

        super(fm);

        this.fragments = fragments;

    }

    @Override

    public Fragment getItem(int position) {

        return this.fragments.get(position);

    }


    @Override

    public int getCount() {

        return this.fragments.size();

    }


}