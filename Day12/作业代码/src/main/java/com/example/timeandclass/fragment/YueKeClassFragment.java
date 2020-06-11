package com.example.timeandclass.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.timeandclass.R;
import com.example.timeandclass.fragment.yueke_fragment.DaiShnagKeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class YueKeClassFragment extends Fragment {
    private TabLayout mTabYueke;
    private FrameLayout mFrameYueke;
    private FragmentManager supportFragmentManager;
    private ArrayList<String> tabs;
    private DaiShnagKeFragment daiShnagKeFragment;
    private DaiShnagKeFragment alreadyShnagKeFragment;
    private DaiShnagKeFragment alreadyCancel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.yueke, null);
        initTab();
        initView(inflate);
        initFragments();
        initListener();
        return inflate;
    }

    private void initFragments() {
        daiShnagKeFragment = new DaiShnagKeFragment();
        Bundle daiShnagKeFragmentBundle = new Bundle();
        daiShnagKeFragmentBundle.putInt("Id",0);
        daiShnagKeFragment.setArguments(daiShnagKeFragmentBundle);
        alreadyShnagKeFragment = new DaiShnagKeFragment();
        Bundle alreadyShnagKeFragmentBundle = new Bundle();
        alreadyShnagKeFragmentBundle.putInt("Id",1);
        alreadyShnagKeFragment.setArguments(alreadyShnagKeFragmentBundle);
        alreadyCancel = new DaiShnagKeFragment();
        Bundle alreadyCancelBundle = new Bundle();
        alreadyCancelBundle.putInt("Id",2);
        alreadyCancel.setArguments(alreadyCancelBundle);
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(R.id.yueke_frame, daiShnagKeFragment);
        transaction.add(R.id.yueke_frame, alreadyShnagKeFragment);
        transaction.add(R.id.yueke_frame, alreadyCancel);
        transaction.hide(alreadyShnagKeFragment).hide(alreadyCancel);
        transaction.commit();
    }

    private void initTab() {
        tabs = new ArrayList<>();
        tabs.add("待上课");
        tabs.add("以上课");
        tabs.add("已取消");
    }

    private void initListener() {
        mTabYueke.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        transaction.show(daiShnagKeFragment)
                                .hide(alreadyShnagKeFragment)
                                .hide(alreadyCancel)
                                .commit();
                        break;
                    case 1:
                        transaction.show(alreadyShnagKeFragment)
                                .hide(daiShnagKeFragment)
                                .hide(alreadyCancel)
                                .commit();
                        break;
                    case 2:
                        transaction.show(alreadyCancel)
                                .hide(alreadyShnagKeFragment)
                                .hide(daiShnagKeFragment)
                                .commit();
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView(@NonNull final View itemView) {
        mTabYueke = (TabLayout) itemView.findViewById(R.id.yueke_tab);
        mFrameYueke = (FrameLayout) itemView.findViewById(R.id.yueke_frame);
        supportFragmentManager = getActivity().getSupportFragmentManager();
        for (int i = 0; i < tabs.size(); i++) {
            mTabYueke.addTab(mTabYueke.newTab().setText(tabs.get(i)));
        }
    }
}
