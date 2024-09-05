package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class databaselite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "alarmlist.db";
    private static final String TABLE_NAME = "alarmList";
    private static final String ID = "_id";
    private static final String COURSE_NAME = "_Coursename";
    private static final String TIME = "time";
    private static final String DAY = "day";
    private static final int VERSION_NUMBER = 2;
    private Context context;
    private static final String CREATE = "CREATE TABLE "+TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COURSE_NAME+" VARCHAR(255) ,"+TIME+" VARCHAR(255), "+DAY+" VARCHAR(255) );";

    public databaselite(@Nullable Context context){
        //}, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            Toast.makeText(context, "Successfully created", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(CREATE);
                }
        catch(Exception e){
            Toast.makeText(context, "Exception"+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            Toast.makeText(context, "Onupgrade is called",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(CREATE);
            onCreate(sqLiteDatabase);
        }
        catch(Exception e){
            Toast.makeText(context, "Exception"+e, Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(String Course_name,String time,String Day){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_NAME,Course_name);
        contentValues.put(TIME,time);
        contentValues.put(DAY,Day);
        long id = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return id;
    }

    public Cursor showAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }

    public Integer delete(String c,String time,String Day){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,COURSE_NAME+" = ? AND "+TIME+" = ? AND "+DAY+" = ? ",new String[]{c,time,Day});
    }

}
