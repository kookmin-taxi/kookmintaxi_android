package com.kookmintaxi.android.net.req;

import com.kookmintaxi.android.net.RetrofitFactory;
import com.kookmintaxi.android.net.res.Common;
import com.kookmintaxi.android.net.res.TaxiFindResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by JaewookAhn on 15/02/2017.
 */

public class Taxi {


    private static TaxiService instance;

    public interface TaxiService {

        @POST("/room/join")
        @FormUrlEncoded
        Call<Common> findreg(@Field("access_token") String accessToken,
                     @Field("from") String from,
                     @Field("to") String to,
                     @Field("departure_time") String departureTime,
                     @Field("due_time") String dueTime,
                     @Field("gender") String gender,
                     @Field("max_person") String maxPerson);

        @POST("/room/view")
        @FormUrlEncoded
        Call<TaxiFindResult> find(@Query("find_token") String findToken);

        @POST("/room/out")
        @FormUrlEncoded
        Call<Common> cancelFind(@Field("access_token") String access_token);

        @POST("/room/stop_request")
        @FormUrlEncoded
        Call<Common> confirm(@Field("access_token") String access_token);
    }

    static TaxiService create() {
        instance = RetrofitFactory.getInstance().getRetrofit().create(TaxiService.class);
        return instance;
    }

    public static TaxiService getInstance() {
        return instance != null ? instance : create();
    }
}
