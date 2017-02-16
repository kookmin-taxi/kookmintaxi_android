package com.kookmintaxi.android.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kookmintaxi.android.R;
import com.kookmintaxi.android.base.BaseActivity;
import com.kookmintaxi.android.net.req.Taxi;

public class Main extends BaseActivity implements View.OnClickListener {


    public static final String TAG = "Main";
    public static final String WHERE = "where";
    public static final int FIND_PLACE_REQ_CODE = 1702;
    private static final int LAYOUT_RESOURCE_ID = R.layout.activity_main;


    private TextView mainIntroText, predict_cost, find_filter, from, to, personCount;
    private Button findStart, setDetail, plus, minus;

    private LinearLayout filterDetailContainer;

    private Taxi.TaxiService taxiService;
    private String str_from, str_to;

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
    }

    void checkRouteState() {

    }

    /**
     * select from point for result
     */
    void findPlace(int id) {
        Intent i = new Intent(this, PlaceFinder.class);
        i.putExtra(WHERE, id);
        startActivityForResult(i, FIND_PLACE_REQ_CODE);
    }

    void controlDetail() {
        if(filterDetailContainer.getVisibility() == View.GONE) {
            filterDetailContainer.setVisibility(View.VISIBLE);
        } else {
            filterDetailContainer.setVisibility(View.GONE);
        }
    }

    void maxCountPlus() {
        int count = Integer.parseInt(personCount.getText().toString());
        if(count < 4) {
            personCount.setText(String.valueOf(++count));
            //  update find filter text
            String[] filters = find_filter.getText().toString().split(" / ");
            find_filter.setText(filters[0] + " / " + filters[1] + " / 최대인원 " + count + "명");
        }

    }

    void maxCountMinus() {
        int count = Integer.parseInt(personCount.getText().toString());
        if(count > 2) {
            personCount.setText(String.valueOf(--count));
            //  update find filter text
            String[] filters = find_filter.getText().toString().split(" / ");
            find_filter.setText(filters[0] + " / " + filters[1] + " / 최대인원 " + count + "명");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_find_start:
                startActivity(new Intent(this, Find.class));
                break;
            case R.id.main_from:
                findPlace(R.id.main_from);
                break;
            case R.id.main_to:
                findPlace(R.id.main_to);
                break;
            case R.id.main_btn_set_detail:
                controlDetail();
                break;
            case R.id.plus:
                maxCountPlus();
                break;
            case R.id.minus:
                maxCountMinus();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  insert select place
        if(requestCode == FIND_PLACE_REQ_CODE
                && resultCode == PlaceFinder.FIND_PLACE_RESULT_CODE
                && data != null) {
            checkRouteState();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
