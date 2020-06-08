package com.example.adapterfeng;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{

    private RecyclerView mRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        Pre pre = new Pre();
        pre.getData(this,"294");
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        mRec = (RecyclerView) findViewById(R.id.rec);
        mRec.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));

    }

    @Override
    public void onSuccess(Bean bean) {
        List<Bean.DataBean.DatasBean> datas = bean.getData().getDatas();
        for (int i = 0; i < datas.size(); i++) {
            Bean.DataBean.DatasBean datasBean = datas.get(i);
            if(i%2==0){
                datasBean.setType1(Bean.DataBean.DatasBean.TYPE_LEFT);
            }else {
                datasBean.setType1(Bean.DataBean.DatasBean.TYPE_RIGHT);
            }
        }
        RecAdapter recAdapter = new RecAdapter(datas);
        mRec.setAdapter(recAdapter);
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
