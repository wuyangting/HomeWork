package com.example.timeandclass;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.timeandclass.fragment.ClassFragment;
import com.example.timeandclass.fragment.PersonFragment;
import com.example.timeandclass.fragment.ShouYeFragment;
import com.example.timeandclass.fragment.YueKeClassFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private FrameLayout mFrame;
    private RadioButton mShouyeMain;
    private RadioButton mClassMain;
    private RadioButton mYueClassMain;
    private RadioButton mPersonMain;
    private RadioGroup mGroupRadio;
    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;
    private ShouYeFragment shouYeFragment;
    private ClassFragment classFragment;
    private YueKeClassFragment tueKeClassFragment;
    private PersonFragment personFragment;
    private BottomNavigationView mNavigationBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        mNavigationBottom.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.main_shouye:
                        fragmentTransaction.show(shouYeFragment)
                                .hide(classFragment)
                                .hide(tueKeClassFragment)
                                .hide(personFragment)
                                .commit();
                        break;
                    case R.id.main_class:
                        fragmentTransaction.show(classFragment)
                                .hide(shouYeFragment)
                                .hide(tueKeClassFragment)
                                .hide(personFragment)
                                .commit();
                        break;
                    case R.id.main_yue_class:
                        fragmentTransaction.show(tueKeClassFragment)
                                .hide(classFragment)
                                .hide(shouYeFragment)
                                .hide(personFragment)
                                .commit();
                        break;
                    case R.id.main_person:
                        fragmentTransaction.show(personFragment)
                                .hide(classFragment)
                                .hide(tueKeClassFragment)
                                .hide(shouYeFragment)
                                .commit();
                        break;
                }
            }
        });
    }

    private void initView() {
        mNavigationBottom = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mFrame = (FrameLayout) findViewById(R.id.frame);
        fragmentManager = getSupportFragmentManager();
        initFragments();

    }

    private void initFragments() {
        shouYeFragment = new ShouYeFragment();
        classFragment = new ClassFragment();
        tueKeClassFragment = new YueKeClassFragment();
        personFragment = new PersonFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, shouYeFragment).add(R.id.frame, classFragment)
                .add(R.id.frame, tueKeClassFragment)
                .add(R.id.frame, personFragment)
                .hide(classFragment)
                .hide(tueKeClassFragment)
                .hide(personFragment);
        fragmentTransaction.commit();
    }


}
