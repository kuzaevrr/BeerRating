package com.kuzaev.twoprojectnote.contract;

import android.provider.BaseColumns;

public class BeersContract {

    public static final class BeersEntry implements BaseColumns {

        public static final String TABLE_NAME = "beers";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_RATING = "rating";

        public static final String TYPE_TEXT = "TEXT";
        public static final String TYPE_INTEGER = "INTEGER";

        public static final String CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " ( " + _ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " " + TYPE_TEXT + ", "
                + COLUMN_PRICE + " " + TYPE_INTEGER + ", "
                + COLUMN_RATING + " " + TYPE_INTEGER + " )";

        public static final String DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
