
package com.united.dailymed.Utils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class WaterDBHandler extends SQLiteOpenHelper {
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "dailymed_water";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "drinkwater";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our id column.
    private static final String gender_COL = "Gender";

    // below variable is for our id column.
    private static final String activities_COL = "Activities";

    // below variable is for our course name column
    public static final String total_COL = "Total";

    // below variable is for our course name column
    public static final String drank_COL = "Drank";

    // below variable is for our course name column
    public static final String remaining_COL = "Remaining";

    // below variable is for our course name column
    //public static final String drinkname_COL = "DrinkName";

    // creating a constructor for our database handler.
    public WaterDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, "
                + gender_COL + " TEXT,"
                + activities_COL+ " TEXT,"
                + total_COL + " DOUBLE,"
                + drank_COL + " DOUBLE,"
                + remaining_COL + " DOUBLE)";


        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }




    /**************************************** Method to insert Values To water Table *******************************************/
    public long addWater(String gender,String activities,Double total){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(gender_COL,gender);
        values.put(activities_COL,activities);
        values.put(total_COL,total);
        values.put(drank_COL,0.00);
        values.put(remaining_COL,total);


        long newRowId = db.insert(TABLE_NAME,null,values);
        return newRowId;
    }





    /* Method to update after drinking water */
    public int updateInfo( Double amount, Double remain) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
//        values.put(ID_COL, id);
        values.put(drank_COL,amount );
        values.put(remaining_COL,remain );


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
//        db.update(TABLE_NAME, values, "id=?", new String[]{id});
//        db.close();




        String selection =ID_COL+ " LIKE ?";
        String[] selectionArgs = {"1"};

        int count = db.update(
                TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        return count;
    }


    /* Method to retrieve Total from water Table */
    public Cursor getInfo(SQLiteDatabase sqLiteDatabase){
        //SQLiteDatabase db = getReadableDatabase();


        String[] projection = {
                total_COL,
                drank_COL,
                remaining_COL
        };

        String selection = ID_COL +"=?";
        String[] selectionArgs = {"1"};

        return sqLiteDatabase.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

    }
    //end of retrieve method




    /* Method to retrieve drank amount */
    public Double getDrank(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                drank_COL
        };

        String selection = ID_COL + " LIKE ?";
        String[] selectionArgs = {"1"};


        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        Double amountDrank = Double.parseDouble(cursor.getString(0));


        cursor.close();

        return amountDrank;

    }//END of retrieve drank amount



    /* Method to retrieve remaining amount */
    public Double getRemainingAmt(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                remaining_COL
        };

        String selection = ID_COL + " LIKE ?";
        String[] selectionArgs = {"1"};


        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        Double amountDrank = Double.parseDouble(cursor.getString(0));


        cursor.close();

        return amountDrank;
    }//end of method to retrieve drank amount




    /* METHOD TO UPDATE Goal */
    public int updateTotal(Double total){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(total_COL,total);






        String selection = ID_COL + " LIKE ?";
        String[] selectionArgs = {"1"};

        int count = db.update(
                TABLE_NAME,
                contentValues,
                selection,
                selectionArgs
        );

        return count;





    }//end of method update weight




//    /***************************************************** METHOD TO UPDATE GENDER ******************************************************************/
//    public int updateGdr(Integer gender){
//        SQLiteDatabase db = getReadableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(gender_COL_COL,gender);
//
//
//        String selection =ID_COL + " LIKE ?";
//        String[] selectionArgs = {"1"};
//
//        int count = db.update(
//                TABLE_NAME,
//                contentValues,
//                selection,
//                selectionArgs
//        );
//
//        return count;
//
//    }//end of method update weight







//    /***************************************************** METHOD TO RETRIEVE WEIGHT ******************************************************************/
//    public Integer getResetInfo(){
//        SQLiteDatabase db = getReadableDatabase();
//
//        String[] projection = {
//
//               weight_COL
//        };
//
//        String selection = ID_COL + " LIKE ?";
//        String[] selectionArgs = {"1"};
//
//
//        Cursor cursor = db.query(
//                TABLE_NAME,
//                projection,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null
//        );
//
//        cursor.moveToFirst();
//        Integer weight = Integer.parseInt(cursor.getString(0));
//        cursor.close();
//
//        return weight;
//
//    }//End of method


    /********************************************************* METHOD TO UPDATE Activities*******************************************************************/
    public int updateActivities(String activities){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(activities_COL,activities);


        String selection = ID_COL + " LIKE ?";
        String[] selectionArgs = {"1"};

        int count = db.update(
                TABLE_NAME,
                contentValues,
                selection,
                selectionArgs
        );

        return count;

    }//end of update time method


    /********************************************************* METHOD TO UPDATE Gender*******************************************************************/
    public int updateGender(String gender){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(gender_COL,gender);


        String selection = ID_COL + " LIKE ?";
        String[] selectionArgs = {"1"};

        int count = db.update(
                TABLE_NAME,
                contentValues,
                selection,
                selectionArgs
        );

        return count;

    }//end of update time method






    /* METHOD TO RETRIEVE Total */
    public Integer getResetTotal(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                total_COL
        };

        String selection = ID_COL + " LIKE ?";
        String[] selectionArgs = {"1"};


        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        Integer exTime = Integer.parseInt(cursor.getString(0));
        cursor.close();

        return exTime;

    }//end of method


    /*METHOD TO UPDATE DETAILS* @param totalAmt*/
    public int resetMyInfo(Integer totalAmt){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(total_COL,totalAmt);
        contentValues.put(drank_COL,0.00);
        contentValues.put(remaining_COL,totalAmt);


        String selection = ID_COL + " LIKE ?";
        String[] selectionArgs = {"1"};

        int count = db.update(
                TABLE_NAME,
                contentValues,
                selection,
                selectionArgs
        );

        return count;

    }//end of method update weight

    /* Method to delete records */
    public void deleteWater() {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = ID_COL + " LIKE ?";

        String selectionArgs[] = {"1"};

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME , selection , selectionArgs);
        db.close();

    }






//    public long addLog(String dname,Double damount){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(drinkname,dname);
//        values.put(drank_COL,damount);
//
//
//
//
//        long newRowId = db.insert(TABLE_NAME,null,values);
//        return newRowId;
//    }
//
//
//
//    // we have created a new method for reading all the courses.
//    public ArrayList<WaterModel> readHeartRates() {
//        // on below line we are creating a
//        // database for reading our database.
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // on below line we are creating a cursor with query to read data from database.
//        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//
//        // on below line we are creating a new array list.
//        ArrayList<WaterModel> WaterModelArrayList = new ArrayList<>();
//
//        // moving our cursor to first position.
//        if (cursorCourses.moveToFirst()) {
//            do {
//                // on below line we are adding the data from cursor to our array list.
//                WaterModelArrayList.add(new WaterModel(cursorCourses.getInt(0),cursorCourses.getString(1),
//                        cursorCourses.getString(2)
//                ));
//            } while (cursorCourses.moveToNext());
//            // moving our cursor to next.
//        }
//        // at last closing our cursor
//        // and returning our array list.
//        cursorCourses.close();
//        return WaterModelArrayList;
//    }
}