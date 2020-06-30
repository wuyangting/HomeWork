package com.example.todaynews.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.todaynews.activity.DragActivity;
import com.example.todaynews.activity.HomePagerAdapter;
import com.example.todaynews.R;
import com.example.todaynews.fragment.homefragment.LikeFrgament;
import com.example.todaynews.fragment.homefragment.RecommendDFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private TabLayout mTabHome;
    private ViewPager mViewpageHome;
    ArrayList<String> tabs = new ArrayList<>();
    private ArrayList<Fragment> fragments;
    private HomePagerAdapter adapter;
    private TextView mSkip;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, null);
        initTabs();
        initFragments();
        initView(inflate);
        initLithener();
//设置展示第一个Tab
        setTabShow();
        return inflate;
    }

    private void setTabShow() {
        for (int i = 1; i < tabs.size(); i++) {
            mTabHome.getTabAt(i).select();
        }
        mTabHome.getTabAt(0).select();
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        for (int i = 0; i < tabs.size(); i++) {
            if(i<tabs.size()/2){
                fragments.add(new RecommendDFragment());
            }else {
                fragments.add(new LikeFrgament());
            }

        }
    }

    private void initTabs() {
        tabs.add("推荐");
        tabs.add("关注");
        tabs.add("电影院");
        tabs.add("我的");
        tabs.add("你的");
        tabs.add("我们的");
        tabs.add("大家的");
        tabs.add("你的");
    }

    private void initLithener() {
        mTabHome.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView customView = (TextView) tab.getCustomView();
                customView.setTextSize(25);
                customView.setTextColor(Color.RED);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView customView = (TextView) tab.getCustomView();
                customView.setTextSize(18);
                customView.setTextColor(Color.GRAY);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initView(@NonNull final View itemView) {
        mTabHome = (TabLayout) itemView.findViewById(R.id.home_tab);
        mViewpageHome = (ViewPager) itemView.findViewById(R.id.home_viewpage);
        adapter = new HomePagerAdapter(getChildFragmentManager(), fragments);
        mViewpageHome.setAdapter(adapter);

        mTabHome.setupWithViewPager(mViewpageHome);
//        initTab();
        setTabView();

        mSkip = (TextView) itemView.findViewById(R.id.skip);
        mSkip.setOnClickListener(this);
    }

    private void initTab() {
        for (int i = 0; i < tabs.size(); i++) {
            mTabHome.addTab(mTabHome.newTab().setText(tabs.get(i)));
        }
    }

    private void setTabView() {
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tabAt = mTabHome.getTabAt(i);
            tabAt.setCustomView(getTabView(i));
        }
    }

    private View getTabView(int i) {
        TextView tabView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tab_layout, null);
        tabView.setText(tabs.get(i));
        return tabView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skip:
                // TODO 20/06/30
                Intent intent = new Intent(getActivity(), DragActivity.class);
                intent.putExtra("tabList",tabs);
                startActivityForResult(intent,200);
                //需要加动画

                break;
            default:
                break;
        }
    }
}
