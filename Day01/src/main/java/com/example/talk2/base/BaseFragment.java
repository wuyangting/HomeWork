package com.example.talk2.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends BasePre> extends Fragment implements BaseView{
   public P pre;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayout(), null);
        initView(inflate);
        pre=initPre();
        if(pre!=null){
            pre.bindView(this);
        }
        initData();
        initListener();
        return inflate;
    }

    protected abstract void initData();
    protected abstract void initListener();

    protected abstract P initPre();

    protected abstract void initView(View inflate);

    protected abstract int getLayout();
}
