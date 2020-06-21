package com.example.slideconflicttest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class VpAdapter extends FragmentPagerAdapter {

    @NonNull
    private final FragmentManager mFm;
    private final List<Fragment> mFragments;

    public VpAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFm = fm;
        mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
