package com.example.slideconflicttest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.slideconflicttest.fragment.DownOneFragment;
import com.example.slideconflicttest.fragment.DownThreeFragment;
import com.example.slideconflicttest.fragment.DownTwoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mMainVp;
    private TabLayout mMainTab;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        initVp();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        DownOneFragment downOneFragment = new DownOneFragment();
        DownTwoFragment downTwoFragment = new DownTwoFragment();
        DownThreeFragment downThreeFragment = new DownThreeFragment();
        mFragments.add(downOneFragment);
        mFragments.add(downTwoFragment);
        mFragments.add(downThreeFragment);
    }

    private void initVp() {
//        mMainTab.addTab(mMainTab.newTab().setText("one"));
//        mMainTab.addTab(mMainTab.newTab().setText("two"));
//        mMainTab.addTab(mMainTab.newTab().setText("three"));
        mMainTab.setupWithViewPager(mMainVp);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), mFragments);
        mMainVp.setAdapter(vpAdapter);
        mMainTab.getTabAt(0).setText("one");
        mMainTab.getTabAt(1).setText("two");
        mMainTab.getTabAt(2).setText("three");

    }

    private void initView() {
        mMainVp = (ViewPager) findViewById(R.id.vp_main);
        mMainTab = (TabLayout) findViewById(R.id.tab_main);
    }
}
