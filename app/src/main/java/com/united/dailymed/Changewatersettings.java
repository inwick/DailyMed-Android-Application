package com.united.dailymed;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Changewatersettings extends AppCompatActivity {


    //    Button btnTotal;
//    Button btnReset;
//    EditText etNewTotal;
    Dialog myDialog;
    Button btngotopopup;
    Button btnChangeTips;

//    final Context context = this;
//    private Button btnTotal;
//    private TextView tvresult;
//    String totalAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20198336_changewatersetting);

    }

    public void goEditTotal(View view) {


        btngotopopup = findViewById(R.id.btnChangeTotal);
        btngotopopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myDialog.setContentView(R.layout.popup);
                Intent i = new Intent(Changewatersettings.this, changeTotal.class);
                startActivity(i);
            }
        });
    }

    public void GoTips(View view) {
        btnChangeTips= findViewById(R.id.btnTips);
        btnChangeTips.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                Intent i = new Intent(Changewatersettings.this,Tips.class);
                startActivity(i);
            }
        });
    }


//    /********************************************* POP UP TO CHANGE Gender ********************************************************/
//    public void changeGender(View view) {
//        myDialog.setContentView(R.layout.popup);
//        btnExercise = (Button) myDialog.findViewById(R.id.btnChangeExerciseTime);
//        Button btnReset = (Button) myDialog.findViewById(R.id.btnSetTime);
//        rgNewTime = (RadioGroup)myDialog.findViewById(R.id.timeGroup);
//
//
//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                changeTime(rgNewTime);//calling update method to change the time
//                Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        myDialog.show();
//    }//end of on click
//
//    /********************************************* UPDATE METHOD TO CHANGE THE TIME ********************************************************/
//    public void changeTime(RadioGroup rgNewTime){
//        DBOpenHelper dbHelper=new DBOpenHelper(this);
//        //System.out.println("Look here 2");
//        // System.out.println(rgNewTime);
//        RadioButton radioButton = (RadioButton) myDialog.findViewById(rgNewTime.getCheckedRadioButtonId());
//        //System.out.println("look here 3:"+radioButton.getText().toString());
//
//        //calling the update method in db helper
//        long val=dbHelper.updateTime(Integer.parseInt(radioButton.getText().toString()));
//
//        if(val>0)
//        {
//            myDialog.dismiss();
//
//        }
//        else
//        {
//            Toast.makeText(this,"Update Failed",Toast.LENGTH_SHORT).show();
//            myDialog.dismiss();
//        }
//    }//end of method


//        /********************************************* POP UP TO CHANGE Gender********************************************************/
//        public void updateGdr(View view) {
//           myDialog.setContentView(R.layout.popup);
//            btnTotal = (Button) myDialog.findViewById(R.id.btnChangeTotal);
//            btnReset = (Button) myDialog.findViewById(R.id.btnSetTotal);
//            etNewTotal = (EditText) myDialog.findViewById(R.id.etnewTotal);
//
//
//            btnReset.setOnClickListener(view1 -> {
//
//
////               changeTotal(etNewTotal);//calling update method to change the time
//                Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
//
//            });
//
//            myDialog.show();
//        }//end of on click


//        //instance of db helper
//        WaterDBHandler waterdbhandler = new WaterDBHandler(this);
//
//        //db instance
//        SQLiteDatabase db = waterdbhandler.getReadableDatabase();
//        final Cursor cursor =waterdbhandler.getInfo(db);
//
//        // components from main.xml
//        btnTotal = findViewById(R.id.btnChangeTotal);
//        tvresult = findViewById(R.id.tvChangeTotal);
//        totalAmt = getIntent().getStringExtra(WaterDBHandler.total_COL);
//
//        // add button listener
//        btnTotal.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                // get prompts.xml view
//                LayoutInflater li = LayoutInflater.from(context);
//                View promptsView = li.inflate(R.layout.prompts, null);
//
//                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
//                        context);
//
//                // set prompts.xml to alertdialog builder
//                alertDialogBuilder.setView(promptsView);
//
//                final EditText userInput = (EditText) promptsView
//                        .findViewById(R.id.editTextDialogUserInput);
//
//                // set dialog message
//                alertDialogBuilder
//                        .setCancelable(false)
//                        .setPositiveButton("OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,int id) {
//
//                                        // get user input and set it to result
//                                        // edit text
//                                        tvresult.setText(totalAmt);
//                                    }
//                                })
//                        .setNegativeButton("Cancel",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//
//                // create alert dialog
//                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
//
//                // show it
//                alertDialog.show();
//
//            }
//        });
//    }


        //db instance
        // SQLiteDatabase db = waterdbhandler.getReadableDatabase();
        //final Cursor cursor =waterdbhandler.getInfo(db);

        //retrieving values when the class is created
        //  while (cursor.moveToNext())

        // {
        //retrieving total amount
        // String totalAmt = cursor.getString(cursor.getColumnIndex(WaterDBHandler.total_COL));
        //getting the id of text view
        //TextView txtTotal = findViewById(R.id.totalAmt);
        // tvresult = findViewById(R.id.tvChangeTotal);
        //setting the value textView
        // tvresult.setText(totalAmt + " ml");


        // }

//     String totalAmt = getIntent().getStringExtra(WaterDBHandler.total_COL);
//     tvResult.setText(totalAmt);
//


        /********************************************* UPDATE METHOD TO CHANGE THE Goal
         * @param ********************************************************/


//        public void changeTotal(EditText etNewTotal){
//            WaterDBHandler waterdbhelper=new WaterDBHandler(this);
//            System.out.println("Look here 2");
//             System.out.println(rgNewTime);
//           EditText editText = (EditText) myDialog.findViewById(etNewTotal.getId());
//            //System.out.println("look here 3:"+radioButton.getText().toString());
//
//            //calling the update method in db helper
//            long val=waterdbhelper.updateTotal(Integer.parseInt(editText.getText().toString()));
//
//            if(val>0)
//            {
//                myDialog.dismiss();
//
//            }
//            else
//            {
//                Toast.makeText(this,"Update Failed",Toast.LENGTH_SHORT).show();
//                myDialog.dismiss();
//            }
//        }//end of method




    /* THE DELETE METHOD */
    public void deleteWaterrecord(View view)
    {
        //db instance
        WaterDBHandler waterdbhandler=new WaterDBHandler(this);

        //calling the delete method in db helper
        waterdbhandler.deleteWater();

        //intent
        Intent i = new Intent(Changewatersettings.this,Water.class);
        Toast.makeText(this,"Deleted successfully",Toast.LENGTH_SHORT).show();
        startActivity(i);

    }


    /* THE ON CLICK OF RESET */
    public void resetRecords(final View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Reset Water Record!");
        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.resetwarning);
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Reset Your Records???");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                resetInfo(v);
                Toast.makeText(Changewatersettings.this, "Your records are reset!", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Changewatersettings.this, "Your records were not reset!", Toast.LENGTH_SHORT).show();
            }
        });
        //create and show alert dialog box
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    /* THE RESET WATER RECORD METHOD */
    public void resetInfo(View view){
        //instance of db helper
        WaterDBHandler waterdbhelper=new WaterDBHandler(this);
        Integer total = waterdbhelper.getResetTotal();



        long val=waterdbhelper.resetMyInfo(total);

        if(val>0)
        {
            Intent i = new Intent(Changewatersettings.this,Waterdashboard.class);
            startActivity(i);
        }
        else
        {
            Intent i = new Intent(Changewatersettings.this,Changewatersettings.class);
            Toast.makeText(this,"Reset failed!",Toast.LENGTH_SHORT).show();
            startActivity(i);
        }


    }













}
