package com.barrera1997.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Juan on 09/11/2017.
 */

public interface ChuckService {
    @GET("jokes/random")
    Call <Chuck> getChuck();
}
