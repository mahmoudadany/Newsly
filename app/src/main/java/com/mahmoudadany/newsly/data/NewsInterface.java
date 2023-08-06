package com.mahmoudadany.newsly.data;

import com.mahmoudadany.newsly.pojo.NewsModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {

    @GET("news")
Observable<NewsModel>getNews(@Query("access_key") String apikey);
    @GET("news")
Observable<NewsModel>getNews(
        @Query("access_key") String apikey,
        @Query("countries")String countries
    );
//    @GET("news")
//Observable<NewsModel>getNews(
//        @Query("access_key") String apikey,
//        @Query("countries")String country,
//        @Query("categories") String categories
//    );
//    @GET("news")
//Observable<NewsModel>getNews(@Query("access_key") String apikey);

}
