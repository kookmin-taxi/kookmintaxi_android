package com.kookmintaxi.android.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.kookmintaxi.android.R;
import com.kookmintaxi.android.adapter.SearchResultAdapter;
import com.kookmintaxi.android.base.BaseActivity;

public class PlaceFinder extends BaseActivity {


    public static final String TAG = "PlaceFinder";
    public static final String WHERE = "where";
    public static final int FIND_PLACE_RESULT_CODE = 1703;
    private static final int LAYOUT_RESOURCE_ID = R.layout.activity_place_finder;

    private EditText search;
    private RecyclerView resultList;
    private LinearLayoutManager llManager;
    private SearchResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutResId(LAYOUT_RESOURCE_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        llManager = new LinearLayoutManager(this);
        adapter = new SearchResultAdapter();
    }

    @Override
    protected void initView() {
        search = (EditText) findViewById(R.id.search);
        if(getIntent() != null) {
            search.setHint(getIntent().getStringExtra(WHERE));
        }
        resultList = (RecyclerView) findViewById(R.id.place_list);
        resultList.setLayoutManager(llManager);
        resultList.setAdapter(adapter);
    }
}
