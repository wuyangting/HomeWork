package com.example.talk.activity;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.talk.MainActivity;
import com.example.talk.R;
import com.example.talk.base.BaseActivity;
import com.example.talk.helper.SharedPrefHelper;
import com.example.talk.pre.LoginPre;
import com.example.talk.view.LoginView;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.api.LoginEvent;
import cn.jiguang.analytics.android.api.RegisterEvent;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class LoginActivity extends BaseActivity<LoginPre> implements LoginView, View.OnClickListener {

    private EditText mUsernameLogin;
    private EditText mPassWordLogin;
    private Button mSubmitLogin;
    private Button mOkLogin;
    private Handler handler=new Handler();
    private SharedPrefHelper sharedPrefHelper;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

        mUsernameLogin = (EditText) findViewById(R.id.login_username);
        mPassWordLogin = (EditText) findViewById(R.id.login_passWord);
        mSubmitLogin = (Button) findViewById(R.id.login_submit);
        mSubmitLogin.setOnClickListener(this);
        mOkLogin = (Button) findViewById(R.id.login_ok);
        mOkLogin.setOnClickListener(this);
        sharedPrefHelper = SharedPrefHelper.getInstance();
        if (!sharedPrefHelper.getUserId().equals("")) {
            mUsernameLogin.setText(sharedPrefHelper.getUserId());
            Toast.makeText(this, sharedPrefHelper.getUserId(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected LoginPre initPre() {
        return new LoginPre();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_submit:
                JMessageClient.register(mUsernameLogin.getText().toString(), mPassWordLogin.getText().toString(), new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        Log.e("s=======1:", i + "，" + s);
                        switch (i) {
                            case 0:
                                showToast( "注册成功");
                                initLogin(mUsernameLogin.getText().toString(),mPassWordLogin.getText().toString(),1);
                                RegisterEvent event = new RegisterEvent("userName", true);
                                JAnalyticsInterface.onEvent(LoginActivity.this,event);
                                break;
                            case 898001:
                                showToast( "用户名已存在");
                                break;
                            case 871301:
                                showToast("密码格式错误");
                                break;
                            case 871304:
                                showToast( "密码错误");
                                break;
                            default:
                                showToast( s);
                                break;
                        }
                    }
                });
                // TODO 20/06/02
                break;
            case R.id.login_ok:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initLogin(mUsernameLogin.getText().toString(),mPassWordLogin.getText().toString(),0);
                    }
                }, 500);
                // TODO 20/06/02
                break;
            default:
                break;
        }
    }
    private void initLogin(String userName, String passWord, final int type){
//        showProgressDialog("正在登陆...");
        JMessageClient.login(userName, passWord, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
//                dismissProgressDialog();
                switch (i) {
                    case 801003:
                        showToast( "用户名不存在");
                        break;
                    case 871301:
                        showToast( "密码格式错误");
                        break;
                    case 801004:
                        showToast( "密码错误");
                        handler.sendEmptyMessage(-1);
                        break;
                    case 0:
                        showToast( "登陆成功");

                        startActivity( new Intent(LoginActivity.this, MainActivity.class));
                        sharedPrefHelper.setUserId(mUsernameLogin.getText().toString());
                        sharedPrefHelper.setUserPW(mPassWordLogin.getText().toString());
//                        initUserInfo(mUsernameLogin.getText().toString(),type);
                        //登录成功计数
                        LoginEvent event = new LoginEvent("userName", true);
                        JAnalyticsInterface.onEvent(LoginActivity.this,event);

                        break;
                    default:
                        break;
                }
            }
        });
    }
}