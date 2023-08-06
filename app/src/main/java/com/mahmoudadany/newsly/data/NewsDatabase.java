package com.mahmoudadany.newsly.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mahmoudadany.newsly.pojo.Data;

@Database(entities = Data.class,version = 1)
public abstract class NewsDatabase extends RoomDatabase {

    private static  NewsDatabase instance=null;
    public abstract DaoInterface getDao();
    public static synchronized NewsDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext()
                    ,NewsDatabase.class,"newsDatabase")
                    .build();
        }
        return instance;
    }
}
