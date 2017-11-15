package com.barrera1997.retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Juan on 13/11/2017.
 */

public interface Chuck2Service {

    @GET("jokes/random")
    Call<ArrayList<Chuck>> getChuck();
}
