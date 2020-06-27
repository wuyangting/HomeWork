package com.example.talk;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.talk.activity.AddFriendsActivity;
import com.example.talk.adapter.MainPageAdapter;
import com.example.talk.base.BaseActivity;
import com.example.talk.base.BasePre;
import com.example.talk.fragment.DontTaiFragment;
import com.example.talk.fragment.MessageFragment;
import com.example.talk.fragment.PeopleFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.api.BasicCallback;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mTool;
    private ViewPager mViewpageMain;
    private TabLayout mTabRecommend;
    private NavigationView mNavigation;
    private DrawerLayout mDraw;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabs;
    private ImageView mBarImgMain;
    private ArrayList<Integer> images;
    private TextView mBarTextMain;
    private TextView mBarRightMain;
    private PopupWindow popupWindow;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBarRightMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_popwindows, null);
                TextView addFriends = inflate.findViewById(R.id.addfriends);
                addFriends.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Intent intent = new Intent(MainActivity.this, AddFriendsActivity.class);
startActivity(intent);
                    }
                });
                popupWindow = new PopupWindow(inflate, 200, 120);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(mBarRightMain);

            }
        });
    }

    @Override
    protected void initView() {

        mTool = (Toolbar) findViewById(R.id.tool);
        mViewpageMain = (ViewPager) findViewById(R.id.main_viewpage);
        mTabRecommend = (TabLayout) findViewById(R.id.main_tab);
        mNavigation = (NavigationView) findViewById(R.id.main_navigation);
        mBarImgMain = (ImageView) findViewById(R.id.main_bar_img);
        mBarImgMain.setOnClickListener(this);
        mDraw = (DrawerLayout) findViewById(R.id.draw);
        mDraw.setOnClickListener(this);
        mBarTextMain = (TextView) findViewById(R.id.main_bar_text);
        mBarRightMain = (TextView) findViewById(R.id.main_bar_right);
        mTabRecommend.setSelectedTabIndicator(0);
        initFragment();
        initTabs();
        initImages();
        setSupportActionBar(mTool);
        mNavigation.setItemIconTintList(null);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDraw, mTool, R.string.open, R.string.close);
        mDraw.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
        MainPageAdapter adapter = new MainPageAdapter(getSupportFragmentManager(), fragments, tabs);
        mViewpageMain.setAdapter(adapter);
        mTabRecommend.setupWithViewPager(mViewpageMain);
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = mTabRecommend.getTabAt(i);
            //设置自定义Tab布局
            tab.setCustomView(getTabView(i));
        }
        mViewpageMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    mBarTextMain.setText("消息");
                    mBarRightMain.setText("＋");
                } else if (position == 1) {
                    mBarTextMain.setText("联系人");
                    mBarRightMain.setText("添加");
                } else {
                    mBarTextMain.setText("动态");
                    mBarRightMain.setText("更多");
                }
                if(popupWindow!=null&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initImages() {
        images = new ArrayList<>();
        images.add(R.drawable.messageselect);
        images.add(R.drawable.peopleselect);
        images.add(R.drawable.dongtaiselect);
    }

    private void initTabs() {
        tabs = new ArrayList<>();
        tabs.add("消息");
        tabs.add("联系人");
        tabs.add("动态");

    }

    //根据索引获取对应的tab的自定义view
    private View getTabView(int position) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.tab, null);
        TextView tv = inflate.findViewById(R.id.tv);
        ImageView iv = inflate.findViewById(R.id.iv);
        tv.setText(tabs.get(position));
        iv.setImageResource(images.get(position));
        return inflate;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new MessageFragment());
        fragments.add(new PeopleFragment());
        fragments.add(new DontTaiFragment());
    }

    @Override
    protected BasePre initPre() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.draw:
                // TODO 20/05/28

                break;
            case R.id.main_bar_img:// TODO 20/05/28
                mDraw.openDrawer(mNavigation);
                break;
            default:
                break;
        }
    }
}
