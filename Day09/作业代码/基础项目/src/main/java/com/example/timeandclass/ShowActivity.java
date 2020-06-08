package com.example.timeandclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.timeandclass.helper.SharedPrefHelper;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    private ViewPager mViewpager;
    private ArrayList<View> integers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initImages();
        initView();
    }

    private void initImages() {

        integers = new ArrayList<>();
        integers.add(LayoutInflater.from(this).inflate(R.layout.page_view1,null));
        integers.add(LayoutInflater.from(this).inflate(R.layout.page_view2,null));
        View inflate = LayoutInflater.from(this).inflate(R.layout.page_view3, null);
        integers.add(inflate);
        TextView pass = inflate.findViewById(R.id.pass);
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefHelper.getInstance().isFirst(false);
                Intent intent = new Intent(ShowActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        PageAdapter pageAdapter = new PageAdapter(integers);
        mViewpager.setAdapter(pageAdapter);
        if(!SharedPrefHelper.getInstance().getIsFirst()){
            Intent intent = new Intent(ShowActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}