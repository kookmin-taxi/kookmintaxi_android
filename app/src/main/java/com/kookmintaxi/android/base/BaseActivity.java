package com.kookmintaxi.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by JaewookAhn on 15/02/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {


    private int layoutResId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(layoutResId <= 0) {
            throw new RuntimeException("You must set XML layout.");
        }
        init();
        super.onCreate(savedInstanceState);
        setContentView(layoutResId);
        initView();
        initMenu();
    }

    protected abstract void init();
    protected abstract void initView();

    protected void initMenu() {}

    public void setLayoutResId(int layoutResId) {
        this.layoutResId = layoutResId;
    }


}
