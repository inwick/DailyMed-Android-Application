package com.united.dailymed;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DietDBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "dailymed";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "dietplan";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String DATE_COL = "date";

    // below variable is for our course name column
    private static final String BFIRST_COL = "bfirst";

    // below variable is for our id column.
    private static final String LUNCH_COL = "lunch";

    // below variable is for our id column.
    private static final String DINNER_COL = "dinner";





    // creating a constructor for our database handler.
    public DietDBHandler (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE_COL + " TEXT,"
                + BFIRST_COL + " TEXT,"
                + LUNCH_COL + " TEXT,"
                + DINNER_COL + " TEXT)";


        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addDietPlan(String date, String bfirst,String lunch, String dinner) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(DATE_COL, date);
        values.put(BFIRST_COL, bfirst);
        values.put(LUNCH_COL, lunch);
        values.put(DINNER_COL, dinner);
        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    // we have created a new method for reading all the courses.
    public ArrayList<DietModel> getAllDietPlans() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<DietModel> DietArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                DietArrayList.add(new DietModel(cursorCourses.getInt(0),cursorCourses.getString(1),
                        cursorCourses.getString(2),cursorCourses.getString(3),cursorCourses.getString(4)
                ));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return DietArrayList;
    }


    // below is the method for updating our courses
    public void updateDiet(String id,String date,String bfirst,String lunch,String dinner) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ID_COL, id);
        values.put(DATE_COL, date);
        values.put(BFIRST_COL, bfirst);
        values.put(LUNCH_COL, lunch);
        values.put(DINNER_COL, dinner);


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "id=?", new String[]{id});
        db.close();
    }

    public void deletePlan(String id) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "id=?", new String[]{id});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}