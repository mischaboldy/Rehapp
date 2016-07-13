package com.mischaboldy.mischa.rehapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by mischa on 29/06/16.
 */
public class DatabaseHelper {

    private static SQLiteDatabase rehappDB = null;

    public static ArrayList<String> getTrainingData(Context context, String dataType, String table) {
        rehappDB = context.openOrCreateDatabase("RehappDatabase.sqlite", context.MODE_PRIVATE, null);

        ArrayList<String> list = new ArrayList<String>();

        Cursor cursor = rehappDB.rawQuery("SELECT * FROM " + table, null);

        int column = cursor.getColumnIndex(dataType);

        cursor.moveToFirst();

        if (cursor != null && (cursor.getCount() > 0)) {

            do {

                String returnData = cursor.getString(column);

                list.add(returnData);

            } while (cursor.moveToNext());

        }

        return list;

    }

    public static void createDatabase(Context context){
        try{
            rehappDB = context.openOrCreateDatabase("RehappDatabase.sqlite", context.MODE_PRIVATE, null);

            rehappDB.execSQL("CREATE TABLE IF NOT EXISTS training " +
                    "(id integer primary key, heart_fq_rest integer, heart_fq_max integer," +
                    "blood_pressure_rest integer, blood_pressure_max integer, " +
                    "blood_sugar integer, sao2 integer, dt datetime default current_timestamp)");

            rehappDB.execSQL("CREATE TABLE IF NOT EXISTS test " +
                    "(id integer primary key, test_type integer, max_watt integer, meter integer, " +
                    "level integer, speed time, MET float, vo2max integer, borgvalue integer, " +
                    "dt datetime default current_timestamp)");

            rehappDB.execSQL("CREATE TABLE IF NOT EXISTS workouts " +
                    "(id integer primary key, workout VARCHAR, duration integer, borgvalue integer, " +
                    "dt datetime default current_timestamp)");

        }
        catch (Exception e){
            Log.e("CONTACTS ERROR", "Error creating database");
        }
    }

    public static void clearTable(Context context, String tableName){
        rehappDB = context.openOrCreateDatabase("RehappDatabase.sqlite", context.MODE_PRIVATE, null);
        rehappDB.delete(tableName, null, null);
    }

    public static void insertIntoDatabase(String query){
        rehappDB.execSQL(query);
    }
}
