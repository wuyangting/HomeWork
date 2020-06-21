package com.example.slideconflicttest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.slideconflicttest.R;
import com.example.slideconflicttest.VpAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class DownOneFragment extends Fragment {
    private TabLayout mTablayout;
    private ViewPager mViewpager;
    private List<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_downone, container, false);
        initView(inflate);
        initFragment();
        initVp();
        return inflate;
    }

    private void initVp() {
        VpAdapter vpAdapter = new VpAdapter(getChildFragmentManager(), mFragments);
        mTablayout.setupWithViewPager(mViewpager);
        mViewpager.setAdapter(vpAdapter);
        mTablayout.getTabAt(0).setText("UpOne");
        mTablayout.getTabAt(1).setText("UpTwo");

    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        UpOneFragment upOneFragment = new UpOneFragment();
        UpTwoFragment upTwoFragment = new UpTwoFragment();
        mFragments.add(upOneFragment);
        mFragments.add(upTwoFragment);
    }

    private void initView(@NonNull final View itemView) {
        mTablayout = (TabLayout) itemView.findViewById(R.id.tablayout);
        mViewpager = (ViewPager) itemView.findViewById(R.id.viewpager);
    }
}
