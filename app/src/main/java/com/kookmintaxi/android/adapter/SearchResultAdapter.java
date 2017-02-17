package com.kookmintaxi.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kookmintaxi.android.R;

import java.util.ArrayList;

/**
 * Created by JaewookAhn on 16/02/2017.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResult> {


    private static final int ITEMVIEW_LAYOUT_RESOURCE = R.layout.item_search_result;

    public interface Callback {
        void onSelect(String item);
    }


    private Callback callback;
    private ArrayList<String> data;
    private String[] point_list = { "국민대학교", "길음역", "성신여대입구역",
            "혜화역", "불광역", "신촌역", "시청역", "광화문역", "압구정역", "잠실역" };

    public SearchResultAdapter(Callback callback) {
        this.callback = callback;
        data = new ArrayList<>();
        for(String point : point_list) {
            data.add(point);
        }
    }

    @Override
    public SearchResult onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(ITEMVIEW_LAYOUT_RESOURCE, parent, false);
        return new SearchResult(view);
    }

    @Override
    public void onBindViewHolder(SearchResult holder, final int position) {
        holder.title.setText(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback != null)
                    callback.onSelect(data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SearchResult extends RecyclerView.ViewHolder {
        public TextView title;
        public SearchResult(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
