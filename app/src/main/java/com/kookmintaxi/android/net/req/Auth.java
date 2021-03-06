package com.kookmintaxi.android.net.req;

import com.kookmintaxi.android.net.RetrofitFactory;
import com.kookmintaxi.android.net.res.Common;
import com.kookmintaxi.android.net.res.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by JaewookAhn on 15/02/2017.
 */

public class Auth {


    private static AuthService instance;

    public interface AuthService {


        @POST("/auth/login")
        @FormUrlEncoded
        Call<Login> login(@Field("id") String id,
                          @Field("pw") String pw);

        @POST("/auth/register")
        @FormUrlEncoded
        Call<Login> register(@Field("name") String name,
                             @Field("grade") String grade,
                             @Field("male") boolean male,
                             @Field("phone_num") String phoneNum);

        @POST("/auth/auth")
        @FormUrlEncoded
        Call<Common> check(@Field("id") String id,
                   @Field("pw") String pw);
    }

    static AuthService create() {
        instance = RetrofitFactory.getInstance().getRetrofit().create(AuthService.class);
        return instance;
    }

    public static AuthService getInstance() {
        return instance != null ? instance : create();
    }

}
