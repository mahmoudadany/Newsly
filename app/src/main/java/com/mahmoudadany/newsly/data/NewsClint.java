package com.mahmoudadany.newsly.data;

import com.mahmoudadany.newsly.R;
import com.mahmoudadany.newsly.pojo.NewsModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.internal.Util;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsClint {
    private  final String BASE_URL="http://api.mediastack.com/v1/";
    private static NewsClint instance=null;
    private NewsInterface newsInterface;
    private Retrofit retrofit;
    private  NewsClint(){
       retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

       newsInterface=retrofit.create(NewsInterface.class);
    }

    public synchronized static NewsClint getInstance(){
        if (instance==null){
            instance=new NewsClint();
        }
        return instance;
    }

    public Observable<NewsModel> getWorldNews(){
        String key="5799a5ecc09db6ff338a502ecff1d9eb";
       return newsInterface.getNews(key);
    }


    public Observable<NewsModel> getAllEgyptNews(){
        String key="5799a5ecc09db6ff338a502ecff1d9eb";
        return newsInterface.getNews(key,"eg");
    }
//    public Observable<NewsModel> getCategoryNews(String category){
//        String key="5799a5ecc09db6ff338a502ecff1d9eb";
//        return newsInterface.getNews(key,"eg");
//    }
}
