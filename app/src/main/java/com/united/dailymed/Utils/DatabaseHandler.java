package com.united.dailymed.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.united.dailymed.Model.PillModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    //Database Name
    private static final String NAME = "dailymed_pills";
    //Table Name
    private static final String PILLS_TABLE = "pills";
    private static final String ID = "id";
    private static final String PILL = "pill";
    private static final String PILLTIME = "pilltime";
    private static final String STATUS ="status";
    //Database creation quarry
    private static final String CREATE_PILLS_TABLE = "CREATE TABLE " + PILLS_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PILL + " TEXT, " + PILLTIME + " TEXT, "
            + STATUS + " INTEGER)";

    //Create SQlite Database reference
    private SQLiteDatabase db;

    //Create Constructor
    public DatabaseHandler(Context context){
        super(context,NAME,null,VERSION);
    }

    //Create Table
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_PILLS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop the older tables
        db.execSQL("DROP TABLE IF EXISTS  PILLS_TABLE");
        //Create table again
        onCreate(db);
    }

    //Get access to modify database
    public void openDatabase(){
        db = this.getWritableDatabase();
    }

    //insert Pills
    public void insertPill(PillModel pill){
        ContentValues cv = new ContentValues();
        cv.put(PILL,pill.getPill());
        cv.put(PILLTIME,pill.getPillTime());
        cv.put(STATUS, 0);
        db.insert(PILLS_TABLE, null, cv);
    }

    //get all details from database
    public List<PillModel> getAllPills(){
        List<PillModel> pillList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(PILLS_TABLE, null,null,null,null,null,null,null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        PillModel  pill = new PillModel();
                        pill.setId(cur.getInt(cur.getColumnIndex(ID)));
                        pill.setPill(cur.getString(cur.getColumnIndex(PILL)));
                        pill.setPillTime(cur.getString(cur.getColumnIndex(PILLTIME)));
                        pill.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                        pillList.add(pill);
                    }while (cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            //assert cur != null;
            cur.close();
        }
        return pillList;
    }

    public void updateStatus(int id,int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS,status);
        db.update(PILLS_TABLE, cv, ID + "=?", new String[]{String.valueOf(id)});
    }

    public void updatePill(int id, String pill){
        ContentValues cv = new ContentValues();
        cv.put(PILL,pill);
        db.update(PILLS_TABLE, cv, ID + "=?", new String[] {String.valueOf(id)});
    }

    public void updatePillTime(int id, String pilltime){
        ContentValues cv = new ContentValues();
        cv.put(PILLTIME,pilltime);
        db.update(PILLS_TABLE, cv, ID + "=?", new String[] {String.valueOf(id)});
    }


    public void deletePill(int id){
        db.delete(PILLS_TABLE, ID + "=?", new String[] {String.valueOf(id)});
    }

}
