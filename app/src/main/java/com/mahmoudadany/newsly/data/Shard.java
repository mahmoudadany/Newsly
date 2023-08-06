package com.mahmoudadany.newsly.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

public class Shard {
    private static Shard instance=null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Shard(Context context){
        sharedPreferences= context.getSharedPreferences("shard",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public synchronized static Shard getInstance(Context context){
        if(instance==null){
            instance=new Shard(context);
        }
        return instance;
    }

   public void addInShard(Boolean mode){
        editor.putBoolean("mode",mode);
        editor.apply();
    }

   public Boolean getFromShard(){
        return sharedPreferences.getBoolean("mode",false);
    }

}
