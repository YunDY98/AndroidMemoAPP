package com.example.mymemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    String sql;
    public static final String DATABASE_NAME = "folderDB";
    public static final String TABLE_NAME = "folderTBL";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "memo_string";
    public static final String COL_3 = "create_time";
    public static final String COL_4 = "time_in_milli";



    public DBHelper(Context context, String title, CursorFactory factory, int version) {
        super(context, title, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sql = "CREATE TABLE folderTBL (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " memo_string TEXT,create_time TEXT,time_in_milli integer default 0);";
        db.execSQL(sql);




    }
    public boolean updateData(String memo_string){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,memo_string);

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {  });
        return true;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS folderTBL");
        onCreate(db);


    }


}