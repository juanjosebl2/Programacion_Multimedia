package com.barrera1997.retrofit;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Juan on 10/11/2017.
 */

public class Controller implements Callback<Chuck> {
    static final String BASE_URL = "http://api.chucknorris.io/";

    public void start() {
        /*Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ChuckService chuckService = retrofit.create(ChuckService.class);
        Call <Chuck> call = chuckService.getChuck();
        call.enqueue(this);*/
    }
    @Override
    public void onResponse(Call<Chuck> call, Response<Chuck> response) {
        if(response.isSuccessful()) {
            Chuck chiste = response.body();
            //System.out.println(chiste.getValue()); un texto en la vista
            //Log.i(chiste.getValue(), chiste.getValue());

        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Chuck> call, Throwable t) {
        t.printStackTrace();
    }
}
