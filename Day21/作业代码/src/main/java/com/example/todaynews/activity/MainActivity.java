package com.example.todaynews.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.todaynews.R;
import com.example.todaynews.fragment.HomeFragment;
import com.example.todaynews.fragment.LiveFragment;
import com.example.todaynews.fragment.MyFragment;
import com.example.todaynews.fragment.WatchMovieFragment;
import com.example.todaynews.myview.MyViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNaviBottom;
    private MyViewPager mMainpager;
    private ArrayList<Fragment> fragments;
    private HomePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initView();
        initListener();
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new WatchMovieFragment());
        fragments.add(new LiveFragment());
        fragments.add(new MyFragment());
    }

    private void initListener() {
        mNaviBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.action_home:
                    mMainpager.setCurrentItem(0);
                    break;
                case R.id.action_fangyingting:
                    mMainpager.setCurrentItem(1);
                    break;
                case R.id.action_live:
                    mMainpager.setCurrentItem(2);
                    break;
                case R.id.action_mine:
                    mMainpager.setCurrentItem(3);
                    break;
            }
                return true;
            }
        });
    }

    private void initView() {
        mNaviBottom = (BottomNavigationView) findViewById(R.id.bottom_navi);
        mMainpager = (MyViewPager) findViewById(R.id.mainpager);
        adapter = new HomePagerAdapter(getSupportFragmentManager(), fragments);
        mMainpager.setAdapter(adapter);
    }
}
