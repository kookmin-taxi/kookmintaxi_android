package com.kookmintaxi.android.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kookmintaxi.android.R;
import com.kookmintaxi.android.base.BaseActivity;
import com.kookmintaxi.android.net.req.Auth;
import com.kookmintaxi.android.net.res.Common;
import com.kookmintaxi.android.pref.SessionPref;
import com.kookmintaxi.android.util.AlertUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseActivity implements View.OnClickListener {


    private static final int LAYOUT_RESOURCE_ID = R.layout.activity_login;

    private EditText id, pw;
    private Button login;

    private Auth.AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutResId(LAYOUT_RESOURCE_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        checkSession();
        authService = Auth.getInstance();
    }

    @Override
    protected void initView() {
        id = (EditText) findViewById(R.id.login_input_id);
        pw = (EditText) findViewById(R.id.login_input_pw);
        login = (Button) findViewById(R.id.login_btn);
        login.setOnClickListener(this);
    }

    void checkSession() {
        if(!SessionPref.getInstance(this).getSession().equals("")) {
            startActivity(new Intent(Login.this, Main.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if(id.getText().toString().equals("") || pw.getText().toString().equals("")) {
                    return;
                }
                authService.check(id.getText().toString(), pw.getText().toString())
                        .enqueue(new Callback<Common>() {
                            @Override
                            public void onResponse(Call<Common> call, Response<Common> response) {
                                if(response.isSuccessful() && response.body().status) {
                                    //  move to register screen
                                    startActivity(new Intent(Login.this, Main.class));
                                } else {
                                    onFailure(call, new Throwable(""));
                                }
                            }

                            @Override
                            public void onFailure(Call<Common> call, Throwable t) {
                                AlertUtils.simpleAlert(Login.this, getString(R.string.login_failed));
                            }
                        });
                break;
        }
    }
}
