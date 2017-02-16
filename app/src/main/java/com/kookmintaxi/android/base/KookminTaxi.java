package com.kookmintaxi.android.base;

import android.app.Application;

import com.kookmintaxi.android.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by JaewookAhn on 17/02/2017.
 */

public class KookminTaxi extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/KoPubDotumMedium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
}
