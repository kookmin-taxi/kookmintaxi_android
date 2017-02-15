package com.kookmintaxi.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JaewookAhn on 15/02/2017.
 */

public abstract class BaseFragment extends Fragment {


    private int layoutResId;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        init();
        rootView = inflater.inflate(layoutResId, container, false);
        initView();
        return rootView;
    }

    protected abstract void init();
    protected abstract void initView();

    public View findViewById(int resId) {
        return rootView.findViewById(resId);
    }

    public void setLayoutResId(int layoutResId) {
        this.layoutResId = layoutResId;
    }

}
