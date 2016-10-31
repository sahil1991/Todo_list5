package com.example.lenovo.todo_list4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lenovo on 29-10-2016.
 */
public class TaskDatabaseHelper {
    private static final String TAG = TaskDatabaseHelper.class.getSimpleName();

    // database configuration
    // if you want the onUpgrade to run then change the database_version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase.db";

    // table configuration
    private static final String TABLE_NAME = "task_table";         // Table name
    private static final String Task_TABLE_COLUMN_ID = "_id";     // a column named "_id" is required for cursor
    private static final String Task_TABLE_COLUMN_NAME = "task_name";
    private static final String Task_TABLE_COLUMN_DATE= "task_date";

    public DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;

    // this is a wrapper class. that means, from outside world, anyone will communicate with PersonDatabaseHelper,
    // but under the hood actually DatabaseOpenHelper class will perform database CRUD operations
    public TaskDatabaseHelper(Context aContext) {

        openHelper = new DatabaseOpenHelper(aContext);
        database = openHelper.getWritableDatabase();
    }

    public void insertData (String taskname, String taskdate) {

        // we are using ContentValues to avoid sql format errors

        ContentValues contentValues = new ContentValues();

        contentValues.put(Task_TABLE_COLUMN_NAME, taskname);
        contentValues.put(Task_TABLE_COLUMN_DATE, taskdate);

        database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllData () {

        String buildSQL = "SELECT * FROM " + TABLE_NAME;

        Log.d(TAG, "getAllData SQL: " + buildSQL);

        return database.rawQuery(buildSQL, null);
    }
    public void deleteItem(long id)

    {   String a=String.valueOf(id);
        SQLiteDatabase db4= openHelper.getWritableDatabase();
        db4.execSQL("DELETE FROM "+ TABLE_NAME+" WHERE "+ Task_TABLE_COLUMN_ID+"= "+ a+ "" );
    }

    // this DatabaseOpenHelper class will actually be used to perform database related operation

    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        public DatabaseOpenHelper(Context aContext) {
            super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            // Create your tables here

            String buildSQL = "CREATE TABLE " + TABLE_NAME + "( " + Task_TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    Task_TABLE_COLUMN_NAME + " TEXT, " + Task_TABLE_COLUMN_DATE + " TEXT )";

            Log.d(TAG, "onCreate SQL: " + buildSQL);

            sqLiteDatabase.execSQL(buildSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            // Database schema upgrade code goes here

            String buildSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;

            Log.d(TAG, "onUpgrade SQL: " + buildSQL);

            sqLiteDatabase.execSQL(buildSQL);       // drop previous table

            onCreate(sqLiteDatabase);               // create the table from the beginning
        }

    }


}
