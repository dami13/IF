package com.example.dami.anif.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dami.anif.Data.DishContract.DishEntry;

/**
 * Created by dami on 10.06.2017.
 */

public class DishDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Dish.db";

    public DishDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE_DAY =
                "CREATE TABLE " + DishEntry.TABLE_NAME + " ("
                        + DishEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + DishEntry.COLUMN_NAME + " TEXT NOT NULL, "
                        + DishEntry.COLUMN_KCL + " INTEGER NOT NULL, "
                        + DishEntry.COLUMN_GRAMS + " INTEGER NOT NULL DEFAULT 100, "
                        + DishEntry.COLUMN_PROTEINS + " INTEGER NOT NULL, "
                        + DishEntry.COLUMN_CARBOHYDRATES + " INTEGER NOT NULL, "
                        + DishEntry.COLUMN_FATS  + " INTEGER NOT NULL, "
                        + DishEntry.COLUMN_IS_FAVORITE  + " INTEGER NOT NULL DEFAULT 0, "
                        + DishEntry.COLUMN_IS_IN_DAY + " INTEGER NOT NULL DEFAULT 0 "+ ");";

        db.execSQL(SQL_CREATE_TABLE_DAY);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        String SQL_DELETE_ENTRIES_DAY =
                "DROP TABLE IF EXISTS " + DishEntry.TABLE_NAME;

        db.execSQL(SQL_DELETE_ENTRIES_DAY);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
