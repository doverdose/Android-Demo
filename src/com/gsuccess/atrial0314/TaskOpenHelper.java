package com.gsuccess.atrial0314;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @author Eric
 */
public class TaskOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "todolist";
    public static final String TASK_TABLE_NAME = "task";
    public static final String TITLE_COLUMN = "title";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String DAY_COLUMN = "day";
    public static final String FREQUENCY_COLUMN = "frequency";
    public static final String CATEGORY_COLUMN = "category";
    private static final String TASK_TABLE_CREATE =
            "CREATE TABLE " + TASK_TABLE_NAME + " (" +
            "_id INTEGER PRIMARY KEY," +
            TITLE_COLUMN + " VARCHAR(255)," +
            DESCRIPTION_COLUMN + " TEXT," +
            DAY_COLUMN + " VARCHAR(255)," +
            FREQUENCY_COLUMN + " VARCHAR(255)," +
            CATEGORY_COLUMN+ " VARCHAR(255));";

    TaskOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASK_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE_NAME);
        db.execSQL(TASK_TABLE_CREATE);
    }

}
