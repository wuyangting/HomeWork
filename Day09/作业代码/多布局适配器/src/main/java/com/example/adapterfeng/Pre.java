package com.example.adapterfeng;

public class Pre {

    private final Model model;

    public Pre() {
        model = new Model();
    }

    public void getData(final MainView mainActivity, String s) {
        model.getData(mainActivity, s,new NetCallBack() {
            @Override
            public void onSuccess(Bean bean) {
                mainActivity.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
mainActivity.onFail(msg);
            }
        });
    }
}
