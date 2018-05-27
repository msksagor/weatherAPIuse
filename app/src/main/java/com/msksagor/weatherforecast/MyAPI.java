package com.msksagor.weatherforecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MyAPI {
    @GET("daily?id=524901&appid=b1b15e88fa797225412429c1c50c122a1")
    Call<Example> getApi();


}
