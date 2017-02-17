package com.kookmintaxi.android.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kookmintaxi.android.R;
import com.kookmintaxi.android.adapter.SearchResultAdapter;
import com.kookmintaxi.android.base.BaseActivity;

public class PlaceFinder extends BaseActivity {


    public static final String TAG = "PlaceFinder";
    public static final String WHERE = "where";
    public static final String SELECTED = "selected";
    public static final int FIND_PLACE_RESULT_CODE = 1703;
    private static final int LAYOUT_RESOURCE_ID = R.layout.activity_place_finder;

    private EditText search;
    private RecyclerView resultList;
    private LinearLayoutManager llManager;
    private SearchResultAdapter adapter;

    private Intent result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutResId(LAYOUT_RESOURCE_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        llManager = new LinearLayoutManager(this);
        adapter = new SearchResultAdapter(new SearchResultAdapter.Callback() {
            @Override
            public void onSelect(String item) {
                Bundle data = new Bundle();
                data.putString(SELECTED, item);
                data.putInt(WHERE, getIntent().getIntExtra(WHERE, 0));
                result.putExtras(data);
                setResult(RESULT_OK, result);
                finish();
            }
        });
        result = new Intent();
    }

    @Override
    protected void initView() {
        search = (EditText) findViewById(R.id.search);
        if(getIntent() != null) {
            switch (getIntent().getIntExtra(WHERE, 0)) {
                case R.id.main_from:
                    search.setHint(getString(R.string.input_search_from_place));
                    break;
                case R.id.main_to:
                    search.setHint(getString(R.string.input_search_to_place));
                    break;
            }
        }
        resultList = (RecyclerView) findViewById(R.id.place_list);
        resultList.setLayoutManager(llManager);
        resultList.setAdapter(adapter);
    }
}
