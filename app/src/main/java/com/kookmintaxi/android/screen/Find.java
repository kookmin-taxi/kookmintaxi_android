package com.kookmintaxi.android.screen;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kookmintaxi.android.R;
import com.kookmintaxi.android.base.BaseActivity;
import com.kookmintaxi.android.net.req.Taxi;
import com.kookmintaxi.android.net.res.Common;
import com.kookmintaxi.android.pref.SessionPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Find extends BaseActivity implements View.OnClickListener {


    public static final String TAG = "Find";
    public static final String SUMMARY = "summary";
    public static final String FILTER = "filter";
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String DEPARTURE_TIME = "departure_time";
    public static final String GENDER = "gender";
    public static final String MAX_PERSON = "max_person";

    private static final int LAYOUT_RESOURCE_ID = R.layout.activity_find;

    private TextView summary, filter;
    private Button confirm, cancel;
    private RecyclerView personList;

    private Taxi.TaxiService taxiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutResId(LAYOUT_RESOURCE_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        taxiService = Taxi.getInstance();
        if(getIntent() != null) {
            String from = getIntent().getStringExtra(FROM);
            String to = getIntent().getStringExtra(TO);
            String departure_time = getIntent().getStringExtra(DEPARTURE_TIME);
            String gender = getIntent().getStringExtra(GENDER);
            String max_person = getIntent().getStringExtra(MAX_PERSON);
            taxiService.findreg(SessionPref.getInstance(this).getSession().access_token,
                    from, to, departure_time, null, gender, max_person, new Callback<Common>() {
                        @Override
                        public void onResponse(Call<Common> call, Response<Common> response) {
                            if(response.isSuccessful()) {

                            } else {
                                onFailure(call, new Throwable(""));
                            }
                        }

                        @Override
                        public void onFailure(Call<Common> call, Throwable t) {
                            Toast.makeText(Find.this, getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    protected void initView() {
        summary = (TextView) findViewById(R.id.find_route_summary);
        filter = (TextView) findViewById(R.id.find_filter_option);
        //  set summary and filter option text
        if(getIntent() != null) {
            summary.setText(getIntent().getStringExtra(SUMMARY));
            filter.setText(getIntent().getStringExtra(FILTER));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_confirm:
                break;
            case R.id.find_cancel:
                finish();
                break;
        }
    }
}
