package com.example.talk2.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePre> extends AppCompatActivity  implements BaseView{
  public   P pre;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        pre=initPre();
        if(pre!=null){
            pre.bindView(this);
        }
        initData();
        initListener();
    }

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract P initPre();


    protected abstract int getLayout();
}
