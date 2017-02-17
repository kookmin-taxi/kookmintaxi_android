package com.kookmintaxi.android.screen;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kookmintaxi.android.R;
import com.kookmintaxi.android.adapter.SearchResultAdapter;
import com.kookmintaxi.android.base.BaseActivity;
import com.kookmintaxi.android.net.req.Taxi;

public class Main extends BaseActivity implements View.OnClickListener {


    public static final String TAG = "Main";
    public static final int FIND_PLACE_REQ_CODE = 1702;
    private static final int LAYOUT_RESOURCE_ID = R.layout.activity_main;


    private TextView mainIntroText, predict_cost, find_filter, from, to, personCount;
    private Button findStart, setDetail, plus, minus, gender_all, gender_male, gender_female;
    private Button[] departure_time = new Button[3];
    private int[] departure_time_ids = {
            R.id.main_btn_10_min, R.id.main_btn_30_min, R.id.main_btn_1_hour
    };

    private LinearLayout filterDetailContainer;

    private Taxi.TaxiService taxiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutResId(LAYOUT_RESOURCE_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        taxiService = Taxi.getInstance();
    }

    @Override
    protected void initView() {
        mainIntroText = (TextView) findViewById(R.id.main_intro_text);
        mainIntroText.setText(Html.fromHtml(getString(R.string.app_main_text)));
        from = (TextView) findViewById(R.id.main_from);
        from.setOnClickListener(this);
        to = (TextView) findViewById(R.id.main_to);
        to.setOnClickListener(this);
        setDetail = (Button) findViewById(R.id.main_btn_set_detail);
        setDetail.setOnClickListener(this);
        predict_cost = (TextView) findViewById(R.id.main_predic_cost);
        find_filter = (TextView) findViewById(R.id.main_find_filter_label);
        findStart = (Button) findViewById(R.id.main_btn_find_start);
        findStart.setOnClickListener(this);
        filterDetailContainer = (LinearLayout) findViewById(R.id.main_filter_detail_container);
        personCount = (TextView) findViewById(R.id.person_count);
        plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(this);
        minus = (Button) findViewById(R.id.minus);
        minus.setOnClickListener(this);
        for(int i = 0; i < 3; i++) {
            departure_time[i] = (Button) findViewById(departure_time_ids[i]);
            departure_time[i].setOnClickListener(this);
        }
        gender_all = (Button) findViewById(R.id.main_btn_gender_all);
        gender_all.setOnClickListener(this);
        gender_male = (Button) findViewById(R.id.main_btn_gender_male);
        gender_male.setOnClickListener(this);
        gender_female = (Button) findViewById(R.id.main_btn_gender_female);
        gender_female.setOnClickListener(this);
    }

    void checkRouteState(String point) {

    }

    /**
     * select from point for result
     */
    void findPlace(int id) {
        Intent i = new Intent(this, PlaceFinder.class);
        i.putExtra(PlaceFinder.WHERE, id);
        startActivityForResult(i, FIND_PLACE_REQ_CODE);
    }

    void controlDetail() {
        if(filterDetailContainer.getVisibility() == View.GONE) {
            setDetail.setText(R.string.btn_main_unset_detail);
            filterDetailContainer.setVisibility(View.VISIBLE);
        } else {
            setDetail.setText(R.string.btn_main_set_detail);
            filterDetailContainer.setVisibility(View.GONE);
        }
    }

    void plusMaxCount() {
        int count = Integer.parseInt(personCount.getText().toString());
        if(count < 4) {
            personCount.setText(String.valueOf(++count));
            //  update find filter text
            String[] filters = find_filter.getText().toString().split(" / ");
            find_filter.setText(filters[0] + " / " + filters[1] + " / 최대인원 " + count + "명");
        }

    }

    void minusMaxCount() {
        int count = Integer.parseInt(personCount.getText().toString());
        if(count > 2) {
            personCount.setText(String.valueOf(--count));
            //  update find filter text
            String[] filters = find_filter.getText().toString().split(" / ");
            find_filter.setText(filters[0] + " / " + filters[1] + " / 최대인원 " + count + "명");
        }
    }

    void startFind() {
        Intent i = new Intent(this, Find.class);
        i.putExtra(Find.SUMMARY, "출발: " + from.getText().toString()
                + " / 도착: " + to.getText().toString());
        i.putExtra(Find.FILTER, find_filter.getText().toString());
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        String[] filter;
        switch (view.getId()) {
            case R.id.main_btn_find_start:
                startFind();
                break;
            case R.id.main_from:
            case R.id.main_to:
                findPlace(view.getId());
                break;
            case R.id.main_btn_set_detail:
                controlDetail();
                break;
            //  max count handle
            case R.id.plus:
                plusMaxCount();
                break;
            case R.id.minus:
                minusMaxCount();
                break;
            //  departure time handle
            case R.id.main_btn_10_min:
                departure_time[0].setBackgroundResource(R.color.colorPrimary);
                departure_time[0].setTextColor(Color.WHITE);
                departure_time[1].setBackgroundResource(R.drawable.square_line_border);
                departure_time[1].setTextColor(Color.BLACK);
                departure_time[2].setBackgroundResource(R.drawable.square_line_border);
                departure_time[2].setTextColor(Color.BLACK);
                filter = find_filter.getText().toString().split(" / ");
                find_filter.setText("출발시간 지금부터 " + departure_time[0].getText().toString()
                        + " / " + filter[1] + " / " + filter[2]);
                break;
            case R.id.main_btn_30_min:
                departure_time[0].setBackgroundResource(R.drawable.square_line_border);
                departure_time[0].setTextColor(Color.BLACK);
                departure_time[1].setBackgroundResource(R.color.colorPrimary);
                departure_time[1].setTextColor(Color.WHITE);
                departure_time[2].setBackgroundResource(R.drawable.square_line_border);
                departure_time[2].setTextColor(Color.BLACK);
                filter = find_filter.getText().toString().split(" / ");
                find_filter.setText("출발시간 지금부터 " + departure_time[1].getText().toString()
                        + " / " + filter[1] + " / " + filter[2]);
                break;
            case R.id.main_btn_1_hour:
                departure_time[0].setBackgroundResource(R.drawable.square_line_border);
                departure_time[0].setTextColor(Color.BLACK);
                departure_time[1].setBackgroundResource(R.drawable.square_line_border);
                departure_time[1].setTextColor(Color.BLACK);
                departure_time[2].setBackgroundResource(R.color.colorPrimary);
                departure_time[2].setTextColor(Color.WHITE);
                filter = find_filter.getText().toString().split(" / ");
                find_filter.setText("출발시간 지금부터 " + departure_time[2].getText().toString()
                        + " / " + filter[1] + " / " + filter[2]);
                break;
            //  gender option handle
            case R.id.main_btn_gender_all:
                gender_all.setBackgroundResource(R.color.colorPrimary);
                gender_male.setBackgroundResource(R.drawable.square_line_border);
                gender_female.setBackgroundResource(R.drawable.square_line_border);
                gender_all.setTextColor(Color.WHITE);
                gender_male.setTextColor(Color.BLACK);
                gender_female.setTextColor(Color.BLACK);
                filter = find_filter.getText().toString().split(" / ");
                find_filter.setText(filter[0]
                        + " / " + gender_all.getText().toString() + " / " + filter[2]);
                break;
            case R.id.main_btn_gender_male:
                gender_all.setBackgroundResource(R.drawable.square_line_border);
                gender_male.setBackgroundResource(R.color.colorPrimary);
                gender_female.setBackgroundResource(R.drawable.square_line_border);
                gender_all.setTextColor(Color.BLACK);
                gender_male.setTextColor(Color.WHITE);
                gender_female.setTextColor(Color.BLACK);
                filter = find_filter.getText().toString().split(" / ");
                find_filter.setText(filter[0]
                        + " / " + gender_male.getText().toString() + " / " + filter[2]);
                break;
            case R.id.main_btn_gender_female:
                gender_all.setBackgroundResource(R.drawable.square_line_border);
                gender_male.setBackgroundResource(R.drawable.square_line_border);
                gender_female.setBackgroundResource(R.color.colorPrimary);
                gender_all.setTextColor(Color.BLACK);
                gender_male.setTextColor(Color.BLACK);
                gender_female.setTextColor(Color.WHITE);
                filter = find_filter.getText().toString().split(" / ");
                find_filter.setText(filter[0]
                        + " / " + gender_female.getText().toString() + " / " + filter[2]);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  insert select place
        if(requestCode == FIND_PLACE_REQ_CODE
                && resultCode == RESULT_OK
                && data != null) {
            String point = data.getExtras().getString(PlaceFinder.SELECTED);
            switch (data.getExtras().getInt(PlaceFinder.WHERE)) {
                case R.id.main_from:
                    from.setText(point);
                    if(point.equals("국민대학교")) {
                        from.setTextColor(Color.RED);
                    } else {
                        from.setTextColor(Color.BLACK);
                    }
                    break;
                case R.id.main_to:
                    to.setText(point);
                    if(point.equals("국민대학교")) {
                        to.setTextColor(Color.RED);
                    } else {
                        to.setTextColor(Color.BLACK);
                    }
                    break;
            }
            checkRouteState(point);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
