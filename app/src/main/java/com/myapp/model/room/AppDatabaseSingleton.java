package com.myapp.model.room;

import android.content.Context;

import androidx.room.*;

import com.myapp.model.room.CountryDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppDatabaseSingleton {

    private static CountryDatabase instance = null;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CountryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        instance = Room.databaseBuilder(context,
                CountryDatabase.class, "CountryDatabase").build();
        return instance;
    }
}
