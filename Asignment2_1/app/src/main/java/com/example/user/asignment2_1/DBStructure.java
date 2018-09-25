package com.example.user.asignment2_1;

import android.provider.BaseColumns;

/**
 * Created by USER on 25-Apr-18.
 */

public class DBStructure {
    public static abstract class tableEntry implements BaseColumns {
        public static final String TABLE_NAME = "usage";
        public static final String COLUMN_ID = "usageid";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_HOURUSAGE = "hourusage";
        public static final String COLUMN_RESID = "resid";
        public static final String COLUMN_WM_USAGE = "wmusage";
        public static final String COLUMN_AC_USAGE = "acusage";
        public static final String COLUMN_FRIDGE_USAGE = "fridgeusage";
        public static final String COLUMN_TEMPERATURE = "temperature";

    }
}
