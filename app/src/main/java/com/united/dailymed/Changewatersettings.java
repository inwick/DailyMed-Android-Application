package com.united.dailymed;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.united.dailymed.Heart.Heart;

public class Changewatersettings extends AppCompatActivity {


    //    Button btnTotal;
//    Button btnReset;
//    EditText etNewTotal;
    Dialog myDialog;
    Button btngotopopup;
    Button btnChangeTips;
    Button btnDailyActivities;
    Button btnChangeGender;
    RadioGroup rgNewActivities;
Button btnReset;
//    final Context context = this;
//    private Button btnTotal;
//    private TextView tvresult;
//    String totalAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20198336_changewatersetting);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Heart Selected
        bottomNavigationView.setSelectedItemId(R.id.Water);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.Heart:
                        startActivity(new Intent(getApplicationContext(), Heart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Fitness:
                        startActivity(new Intent(getApplicationContext(),Fitness.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Pill:
                        startActivity(new Intent(getApplicationContext(),Pill.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Water:
                        startActivity(new Intent(getApplicationContext(),Water.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }

        });

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
    public void goEditActivities(View view) {


        btnDailyActivities = findViewById(R.id.btnActivities);
        btnDailyActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myDialog.setContentView(R.layout.popup);
                Intent i = new Intent(Changewatersettings.this, changeActivities.class);
                startActivity(i);
            }
        });
    }
    public void goEditGender(View view) {


        btnChangeGender = findViewById(R.id.btnGender);
        btnChangeGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myDialog.setContentView(R.layout.popup);
                Intent i = new Intent(Changewatersettings.this, changeGender.class);
                startActivity(i);
            }
        });
    }


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
