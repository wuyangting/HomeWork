package com.example.todaynews.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.todaynews.R;

import java.util.ArrayList;

public class DragActivity extends AppCompatActivity {

    private ArrayList<String> tabList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        Intent intent = getIntent();
        tabList = intent.getStringArrayListExtra("tabList");

    }
}