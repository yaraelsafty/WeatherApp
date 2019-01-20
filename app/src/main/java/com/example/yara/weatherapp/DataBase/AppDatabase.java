package com.example.yara.weatherapp.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

/**
 * Created by Yara on 18-Jan-19.
 */
@Database(entities = {WeatherEntry.class},version = 1,exportSchema = false)
public abstract class  AppDatabase extends RoomDatabase {
    private static final String TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME ="Weather";
    private static AppDatabase sInstance;

    public static AppDatabase getsInstance(Context context){
        if (sInstance ==null){
            synchronized (LOCK){
                Log.d(TAG,"creating new database instance");
                sInstance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(TAG,"Getting database instance");

        return sInstance;
    }
    public abstract WeatherDao weatherDao ();
}
