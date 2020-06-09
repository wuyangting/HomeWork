package com.example.timeandclass;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.timeandclass.fragment.ClassFragment;
import com.example.timeandclass.fragment.PersonFragment;
import com.example.timeandclass.fragment.ShouYeFragment;
import com.example.timeandclass.fragment.YueKeClassFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mFrame = (FrameLayout) findViewById(R.id.frame);
        mShouyeMain = (RadioButton) findViewById(R.id.main_shouye);
        mClassMain = (RadioButton) findViewById(R.id.main_class);
        mYueClassMain = (RadioButton) findViewById(R.id.main_yue_class);
        mPersonMain = (RadioButton) findViewById(R.id.main_person);
        mGroupRadio = (RadioGroup) findViewById(R.id.radio_group);
        mShouyeMain.setOnCheckedChangeListener(this);
        mClassMain.setOnCheckedChangeListener(this);
        mYueClassMain.setOnCheckedChangeListener(this);
        mPersonMain.setOnCheckedChangeListener(this);
        fragmentManager =getSupportFragmentManager();
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


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (buttonView.getId()){

                case R.id.main_shouye:
                        fragmentTransaction.show(shouYeFragment)
                                .hide(classFragment)
                                .hide(tueKeClassFragment)
                                .hide(personFragment);
                        fragmentTransaction.commit();
                    break;
                case R.id.main_class:
                    fragmentTransaction.show(classFragment)
                            .hide(personFragment)
                            .hide(tueKeClassFragment)
                            .hide(shouYeFragment);
                        fragmentTransaction.commit();
                    break;
                case R.id.main_yue_class:
                    fragmentTransaction.show(tueKeClassFragment)
                            .hide(classFragment)
                            .hide(personFragment)
                            .hide(shouYeFragment);
                        fragmentTransaction.commit();
                    break;
                case R.id.main_person:
                    fragmentTransaction.show(personFragment)
                            .hide(classFragment)
                            .hide(tueKeClassFragment)
                            .hide(shouYeFragment);
                        fragmentTransaction.commit();
                    break;

            }


        }
    }
}
