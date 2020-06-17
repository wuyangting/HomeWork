package com.example.timeandclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mPassSkipLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mPassSkipLogin = (TextView) findViewById(R.id.login_pass_skip);
        mPassSkipLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_pass_skip:
                Intent intent = new Intent(this, PassLoginActivity.class);
                startActivity(intent);
                // TODO 20/06/15
                break;
            default:
                break;
        }
    }
}