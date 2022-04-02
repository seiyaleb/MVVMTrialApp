package com.myapp.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;

import com.myapp.R;
import com.myapp.model.CountryRepository;
import com.myapp.model.room.Country;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private CountryRepository repository;
    private final LiveData<List<Country>> countries;

    public CountryViewModel(Application application) {
        super(application);
        repository = new CountryRepository(application);
        countries = repository.getAll();
    }

    //初期データ追加
    public void loadData() {

        //repository.deleteAll();

        Country country = new Country();
        country.setName("America");
        Country country2 = new Country();
        country2.setName("Japan");

        repository.insert(country);
        repository.insert(country2);
    }

    //データ全取得
    public LiveData<List<Country>> getAll() {
        return countries;
    }

    //データ追加
    public void insert(Country country) {
        repository.insert(country);
    }

    //データ削除
    public void delete(Country country) {
        repository.delete(country);
    }
}
