package com.kookmintaxi.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kookmintaxi.android.R;

/**
 * Created by JaewookAhn on 16/02/2017.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResult> {


    private static final int ITEMVIEW_LAYOUT_RESOURCE = R.layout.item_search_result;

    @Override
    public SearchResult onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(ITEMVIEW_LAYOUT_RESOURCE, parent, false);
        return new SearchResult(view);
    }

    @Override
    public void onBindViewHolder(SearchResult holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchResult extends RecyclerView.ViewHolder {
        public SearchResult(View itemView) {
            super(itemView);
        }
    }
}
