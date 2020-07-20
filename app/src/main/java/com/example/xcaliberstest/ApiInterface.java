package com.example.xcaliberstest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String Baseurl="https://chroniclingamerica.loc.gov/search/titles/results/" ;

    @GET("?state=&county=&city=&year1=1690&year2=2019&terms=&frequency=&language=&ethnicity=&labor=&material_type=&lccn=&rows=20&format=json")
    Call<ResponseBody> getItems();


    @GET("lccn/sn88078778.json")
    Call<UrlBody> getString();

}
