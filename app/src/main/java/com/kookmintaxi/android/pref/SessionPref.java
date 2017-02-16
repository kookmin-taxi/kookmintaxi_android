package com.kookmintaxi.android.pref;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JaewookAhn on 17/02/2017.
 */

public class SessionPref {


    public static final String SESSION_PREF = "session_pref";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String USER_ID = "user_id";

    private static SessionPref instance;

    private Context mContext;
    private SharedPreferences pref;

    private SessionPref(Context context) {
        instance = this;
        mContext = context;
        pref = mContext.getSharedPreferences(SESSION_PREF, Context.MODE_PRIVATE);
    }

    public static SessionPref getInstance(Context context) {
        return instance != null ? instance : new SessionPref(context);
    }

    public boolean setSession(String access_token, String user_id) {
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(ACCESS_TOKEN, access_token);
        edit.putString(USER_ID, user_id);
        return edit.commit();
    }

    public String getSession() {
        return pref.getString(ACCESS_TOKEN, "");
    }


}
