package com.example.temphumprojectlucascavataio;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TempHumApiAdapter extends AppCompatActivity {

    private static InfoApiService API_SERVICE;

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static InfoApiService getApiService(String ip) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        System.out.println(ip);
        Log.d("ip",ip+"");
        String baseUrl;

        System.out.println(ip);

        if(ip != null){
            baseUrl = "http://"+ip+"/api/";
        } else {
            baseUrl = "http://192.168.1.33/api/";
        }


        if (API_SERVICE == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
            API_SERVICE = retrofit.create(InfoApiService.class);
        }

        return API_SERVICE;
    }

}
