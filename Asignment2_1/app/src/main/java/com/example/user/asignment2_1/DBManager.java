package com.example.user.asignment2_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.math.BigDecimal;

import java.text.DecimalFormat;

/**
 * Created by USER on 25-Apr-18.
 */

public class DBManager {
    private String[] columns = {
            DBStructure.tableEntry.COLUMN_ID,
            DBStructure.tableEntry.COLUMN_DATE,
            DBStructure.tableEntry.COLUMN_HOURUSAGE,
            DBStructure.tableEntry.COLUMN_RESID,
            DBStructure.tableEntry.COLUMN_WM_USAGE,
            DBStructure.tableEntry.COLUMN_AC_USAGE,
            DBStructure.tableEntry.COLUMN_FRIDGE_USAGE,
            DBStructure.tableEntry.COLUMN_TEMPERATURE};



    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "usage.db";
    private final Context context;
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBStructure.tableEntry.TABLE_NAME + " (" +
                    DBStructure.tableEntry._ID + " INTEGER PRIMARY KEY," +
                    DBStructure.tableEntry.COLUMN_ID + INTEGER_TYPE + COMMA_SEP +
                    DBStructure.tableEntry.COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
                    DBStructure.tableEntry.COLUMN_HOURUSAGE + INTEGER_TYPE + COMMA_SEP +
                    DBStructure.tableEntry.COLUMN_RESID + INTEGER_TYPE + COMMA_SEP +
                    DBStructure.tableEntry.COLUMN_WM_USAGE + REAL_TYPE + COMMA_SEP +
                    DBStructure.tableEntry.COLUMN_AC_USAGE + REAL_TYPE + COMMA_SEP +
                    DBStructure.tableEntry.COLUMN_FRIDGE_USAGE + REAL_TYPE + COMMA_SEP +
                    DBStructure.tableEntry.COLUMN_TEMPERATURE + REAL_TYPE +
                    " );";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBStructure.tableEntry.TABLE_NAME;

    private MySQLiteOpenHelper myDBHelper;
    private SQLiteDatabase db;

    public DBManager open() throws SQLException {
        db = myDBHelper.getWritableDatabase();
        return this;
    }
    public void close() {

        myDBHelper.close();
    }


    public DBManager(Context ctx) {
        this.context = ctx;
        myDBHelper = new MySQLiteOpenHelper(context);
    }


    //getting a row by usageID
    public String getLineByUsageId(int usageId){
        db = myDBHelper.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + DBStructure.tableEntry.TABLE_NAME + " WHERE "
                + DBStructure.tableEntry.COLUMN_ID +  " = " + usageId;

        return selectQuery;
    }


    //Insert Data
    public long insertUsage(Integer usageid, String date, Integer hourusage, Integer resid, double wmusage, double acusage, double fridgeusage, double temp){
        ContentValues values = new ContentValues();
        values.put(DBStructure.tableEntry.COLUMN_ID, usageid);
        values.put(DBStructure.tableEntry.COLUMN_DATE, date);
        values.put(DBStructure.tableEntry.COLUMN_HOURUSAGE, hourusage);
        values.put(DBStructure.tableEntry.COLUMN_RESID, resid);
        values.put(DBStructure.tableEntry.COLUMN_WM_USAGE, wmusage);
        values.put(DBStructure.tableEntry.COLUMN_AC_USAGE, acusage);
        values.put(DBStructure.tableEntry.COLUMN_FRIDGE_USAGE, fridgeusage);
        values.put(DBStructure.tableEntry.COLUMN_TEMPERATURE, temp);
        return db.insert(DBStructure.tableEntry.TABLE_NAME, null, values);
    }

    //Delete Data
    public int deleteUsage(Integer rowId) {
        String[] selectionArgs = { String.valueOf(rowId) };
        String selection = DBStructure.tableEntry.COLUMN_ID + " LIKE ?";
        return db.delete(DBStructure.tableEntry.TABLE_NAME, selection,selectionArgs );
    }

    //Delete All Data
    public void deleteAll()
    {
        db.execSQL("delete from "+ DBStructure.tableEntry.TABLE_NAME);
    }


    //Retrieve all records
    public Cursor getAllUsers() {
        return db.query(DBStructure.tableEntry.TABLE_NAME, columns, null, null, null, null, null);
    }

    //Retrieve one record
    public Cursor getOneRecord(String query){
        return db.rawQuery(query, null);
    }


    private static class MySQLiteOpenHelper extends SQLiteOpenHelper {
        public MySQLiteOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }
}
