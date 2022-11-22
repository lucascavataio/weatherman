package com.example.temphumprojectlucascavataio;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InfoApiService {

    @GET("readTempHum.php")
    Call<Read> readTempHum();

    @POST("sendCheck.php")
    Call<Read> sendTempHumLightAdvice(@Query("tempOk") String tempOk, @Query("humOk") String humOk);

    @GET("ledTest.php")
    Call<Read> executeTest();
}
