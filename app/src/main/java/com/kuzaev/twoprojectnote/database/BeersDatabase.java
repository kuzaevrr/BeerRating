package com.kuzaev.twoprojectnote.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kuzaev.twoprojectnote.dao.BeerDAO;
import com.kuzaev.twoprojectnote.object.Beer;

@Database(entities = {Beer.class}, version = 1, exportSchema = false)
public abstract class BeersDatabase extends RoomDatabase {

    private static BeersDatabase database;
    private static final String DB_NAME = "beers.db";
    private static final Object LOCK = new Object();

    public static BeersDatabase getInstance(Context context){
        synchronized (LOCK){
            if(database == null){
                database = Room.databaseBuilder(context, BeersDatabase.class, DB_NAME)
                        .build();
            }
        }
        return  database;
    }

    public abstract BeerDAO beerDAO();
}
