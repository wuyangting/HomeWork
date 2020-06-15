package com.example.timeandclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PassLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mVerifyLoginEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_login);
        initView();
    }

    private void initView() {
        mVerifyLoginEt = (TextView) findViewById(R.id.et_verify_login);
        mVerifyLoginEt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_verify_login:
                // TODO 20/06/15
                Intent intent = new Intent(this, LoginActivity.class
                );
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}