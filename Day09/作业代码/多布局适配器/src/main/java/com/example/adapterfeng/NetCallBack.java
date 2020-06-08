package com.example.adapterfeng;

public interface NetCallBack {
    void onSuccess(Bean bean);
    void onFail(String msg);
}
