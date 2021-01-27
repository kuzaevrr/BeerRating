package com.kuzaev.twoprojectnote.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.kuzaev.twoprojectnote.contract.BeersContract;

public class BeersDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "beers.db";
    public static final int DB_VERSION = 1;

    public BeersDBHelper(@NonNull Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BeersContract.BeersEntry.CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BeersContract.BeersEntry.DROP_COMMAND);
        onCreate(db);
    }
}
