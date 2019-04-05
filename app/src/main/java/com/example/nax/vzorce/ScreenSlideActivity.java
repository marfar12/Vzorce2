package com.example.nax.vzorce;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ScreenSlideActivity extends FragmentPagerAdapter {

    public ScreenSlideActivity(FragmentManager fm) {
        super(fm);
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Vzorce();
            case 1: return new Graf();
            default: return null;
        }
    }
}
