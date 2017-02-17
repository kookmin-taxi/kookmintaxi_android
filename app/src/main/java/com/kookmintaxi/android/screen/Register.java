package com.kookmintaxi.android.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kookmintaxi.android.R;
import com.kookmintaxi.android.base.BaseActivity;

public class Register extends BaseActivity implements View.OnClickListener {


    private static final int LAYOUT_RESOURCE_ID = R.layout.activity_register;

    private EditText name, phone;
    private TextView[] grades = new TextView[4], gender = new TextView[2];
    private int[] grade_ids = {
            R.id.register_grade_1,
            R.id.register_grade_2,
            R.id.register_grade_3,
            R.id.register_grade_4
    }, gender_ids = {
            R.id.register_male_check,
            R.id.register_female_check
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutResId(LAYOUT_RESOURCE_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        name = (EditText) findViewById(R.id.register_input_name);
        phone = (EditText) findViewById(R.id.register_input_phone);
        for(int i = 0; i < 2; i++) {
            gender[i] = (TextView) findViewById(gender_ids[i]);
            gender[i].setOnClickListener(this);
        }
        for(int i = 0; i< 4; i++) {
            grades[i] = (TextView) findViewById(grade_ids[i]);
            grades[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
