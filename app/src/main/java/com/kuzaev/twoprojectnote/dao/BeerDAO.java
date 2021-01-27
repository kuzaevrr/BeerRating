package com.kuzaev.twoprojectnote.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kuzaev.twoprojectnote.object.Beer;

import java.util.List;

@Dao
public interface BeerDAO {

    @Query("SELECT * FROM beers ORDER BY ratingBeer DESC")
    LiveData<List<Beer>> getAllNote();

    @Insert
    void insertBeer(Beer beer);

    @Delete
    void deleteBeer(Beer beer);

    @Query("DELETE FROM beers")
    void deletedAllBeer();
}
