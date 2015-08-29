package com.beaconsolutions.maestroid.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Chamil Prabodha on 26/08/2015.
 */
public class AppSQLiteHelper extends SQLiteOpenHelper {

    private static AppSQLiteHelper instance = null;
    private static Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Gamedata";
    private static final String LEVEL_COMPLETION_TIME_TABLE = "LevelCompletionTime";
    private static final String LEVEL_ID = "level_id";
    private static final String TIME_TAKEN = "time_taken";

    public AppSQLiteHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    public static AppSQLiteHelper getInstance(Context context){
        if(instance == null)
            instance = new AppSQLiteHelper(context);

        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_LEVEL_COMPLETION_TIME_TABLE =
                "CREATE TABLE IF NOT EXISTS "+LEVEL_COMPLETION_TIME_TABLE+"("+
                        "attempt_no INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "level_id INTEGER, "+
                        "time_taken INTEGER) ";

        db.execSQL(CREATE_LEVEL_COMPLETION_TIME_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABlE IF EXISTS "+LEVEL_COMPLETION_TIME_TABLE);
        this.onCreate(db);
    }

    public void addTime(int level_id, long time){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues  values = new ContentValues();
        values.put(LEVEL_ID, level_id);
        values.put(TIME_TAKEN, time);

        db.insert(LEVEL_COMPLETION_TIME_TABLE, null, values);

        db.close();
    }

    public Long getMinTime(int level_id){

        Long time = 0L;

        String query = "SELECT MIN("+TIME_TAKEN+") FROM `"+LEVEL_COMPLETION_TIME_TABLE+"` WHERE `"+LEVEL_ID+"` = '"+level_id+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query , null);

        if(cursor.moveToFirst()){
            time = cursor.getLong(0);
        }

        return time;
    }

    public int getAttempts(int level_id){

        int count = 0;

        String query = "SELECT COUNT(*) FROM `"+LEVEL_COMPLETION_TIME_TABLE+"` WHERE `"+LEVEL_ID+"` = '"+level_id+"'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            count = cursor.getInt(0);
        }

        return count;
    }

    public Long getLastTime(int level_id){

        Long time = 0L;

        String query = "SELECT `"+TIME_TAKEN+"` FROM `"+LEVEL_COMPLETION_TIME_TABLE+"` WHERE `"+LEVEL_ID+"` = '"+level_id+"'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                time = cursor.getLong(0);
            }while(cursor.moveToNext());
        }

        return time;
    }

    public ArrayList<Long> getLevelTime(int level_id){

        ArrayList<Long> times = new ArrayList<>();

        String query = "SELECT `"+TIME_TAKEN+"` FROM `"+LEVEL_COMPLETION_TIME_TABLE+"` WHERE `"+LEVEL_ID+"` = '"+level_id+"'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                times.add(cursor.getLong(0));
            }while(cursor.moveToNext());
        }

        return times;
    }
}
