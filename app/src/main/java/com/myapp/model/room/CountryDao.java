package com.myapp.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM country order by id desc")
    LiveData<List<Country>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Country country);

    @Delete
    void delete(Country country);

    @Query("DELETE from country")
    void deleteAll();
}
