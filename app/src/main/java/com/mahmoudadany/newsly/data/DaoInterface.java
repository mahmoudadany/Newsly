package com.mahmoudadany.newsly.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mahmoudadany.newsly.pojo.Data;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DaoInterface {

    @Insert
   Completable insertNewData(Data data);

    @Query("select * from newsDataTable")
    Single<List<Data>> getAllNews();

    @Query("delete from newsDataTable where id=:id")
    Completable deleteItem(int id);

}
