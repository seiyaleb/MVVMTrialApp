package com.myapp.model.room;

import androidx.room.*;

@Database(entities = {Country.class}, version = 2,exportSchema = false)
public abstract class CountryDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();
}
