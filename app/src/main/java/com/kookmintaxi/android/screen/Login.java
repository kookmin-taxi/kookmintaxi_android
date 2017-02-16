package com.kookmintaxi.android.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kookmintaxi.android.R;
import com.kookmintaxi.android.base.BaseActivity;
import com.kookmintaxi.android.net.req.Auth;

public class Login extends BaseActivity implements View.OnClickListener {


    private static final int LAYOUT_RESOURCE_ID = R.layout.activity_login;

    private EditText id, pw;

    private Auth.AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutResId(LAYOUT_RESOURCE_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        authService = Auth.getInstance();
    }

    @Override
    protected void initView() {
        id = (EditText) findViewById(R.id.login_input_id);
        pw = (EditText) findViewById(R.id.login_input_pw);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                break;
        }
    }
}
