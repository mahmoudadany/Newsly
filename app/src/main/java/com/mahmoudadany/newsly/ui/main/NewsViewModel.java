package com.mahmoudadany.newsly.ui.main;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mahmoudadany.newsly.data.NewsClint;
import com.mahmoudadany.newsly.pojo.NewsModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {
    public MutableLiveData<NewsModel> liveDataEgyptNews = new MutableLiveData<>();
    public MutableLiveData<NewsModel> liveDataWorldNews = new MutableLiveData<>();
//    public MutableLiveData<NewsModel> liveDataCategoryNews = new MutableLiveData<>();

    public void EgyptNews() {
        NewsClint clint = NewsClint.getInstance();
        Observable observable = clint.getAllEgyptNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer observer = new Observer<NewsModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull NewsModel newsModels) {
                liveDataEgyptNews.setValue(newsModels);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "adanyerror onError: " + e);
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public void WorldNews() {
        NewsClint clint = NewsClint.getInstance();
        Observable observable = clint.getWorldNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer observer = new Observer<NewsModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull NewsModel newsModels) {
                liveDataWorldNews.setValue(newsModels);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "adanyerror onError: " + e);
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

//    public void CategoryNew(String category) {
//        NewsClint clint = NewsClint.getInstance();
//        Observable observable = clint.getCategoryNews(category)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//        Observer observer = new Observer<NewsModel>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull NewsModel newsModels) {
//                liveDataCategoryNews.setValue(newsModels);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d(TAG, "adanyerror onError: " + e);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        observable.subscribe(observer);
//    }
}
