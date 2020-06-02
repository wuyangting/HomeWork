package com.example.talk2.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.talk2.R;
import com.example.talk2.base.BaseActivity;
import com.example.talk2.pre.LoginPre;
import com.example.talk2.view.LoginView;

public class LoginActivity extends BaseActivity<LoginPre> implements LoginView, View.OnClickListener {

    private EditText mUsernameLogin;
    private EditText mPassWordLogin;
    private Button mSubmitLogin;
    private Button mOkLogin;

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

                // TODO 20/06/02
                break;
            case R.id.login_ok:

                // TODO 20/06/02
                break;
            default:
                break;
        }
    }
}