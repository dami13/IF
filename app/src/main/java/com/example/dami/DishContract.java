package com.example.dami.anif.Data;

import android.provider.BaseColumns;

/**
 * Created by dami on 10.06.2017.
 */

public final class DishContract {

    private DishContract() {
    };

    public static final class DishEntry implements BaseColumns {

        //fields for table Day
        public final static String TABLE_NAME = "day";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_KCL = "kcl";
        public final static String COLUMN_GRAMS  = "grams";
        public final static String COLUMN_PROTEINS  = "proteins";
        public final static String COLUMN_CARBOHYDRATES  = "carbohydrates";
        public final static String COLUMN_FATS  = "fats";
        public final static String COLUMN_IS_FAVORITE  = "isFavorite";
        public final static String COLUMN_IS_IN_DAY = "isInDay";
    }
}
