package com.myapp.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myapp.model.room.AppDatabaseSingleton;
import com.myapp.model.room.Country;
import com.myapp.model.room.CountryDao;

import java.util.List;

public class CountryRepository {

    private CountryDao countryDao;
    private LiveData<List<Country>> countries;

    public CountryRepository(Application application) {
        countryDao = AppDatabaseSingleton.getInstance(application).countryDao();
        countries = countryDao.getAll();
    }

    //データ全取得
    public LiveData<List<Country>> getAll() {
        return countries;
    }

    //データ追加
    public void insert(Country country) {
        AppDatabaseSingleton.databaseWriteExecutor.execute(() -> {
            countryDao.insert(country);
        });
    }

    //データ削除
    public void delete(Country country) {
        AppDatabaseSingleton.databaseWriteExecutor.execute(() -> {
            countryDao.delete(country);
        });
    }

    //データ全削除
    //public void deleteAll() {
        //AppDatabaseSingleton.databaseWriteExecutor.execute(() -> {
            //countryDao.deleteAll();
        //});
    //}
}
