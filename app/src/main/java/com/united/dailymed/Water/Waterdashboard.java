package com.united.dailymed.Water;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.united.dailymed.Diet.Diet;
import com.united.dailymed.Heart.Heart;
import com.united.dailymed.Home.Home;
import com.united.dailymed.Pill.Pill;
import com.united.dailymed.R;
import com.united.dailymed.Utils.WaterDBHandler;

public class Waterdashboard extends AppCompatActivity {


    Button btnAddNewDrink;
    Button btnChangeWaterSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20198336_waterdashboard);


        //instance of db helper
        WaterDBHandler waterdbhandler = new WaterDBHandler(this);

        //db instance
        SQLiteDatabase db = waterdbhandler.getReadableDatabase();
        final Cursor cursor =waterdbhandler.getInfo(db);

        //retrieving values when the class is created
        while (cursor.moveToNext()) {
            //retrieving total amount
            String totalAmt = cursor.getString(cursor.getColumnIndex(WaterDBHandler.total_COL));
            //getting the id of text view
            TextView txtTotal = findViewById(R.id.totalAmt);
            //setting the value textView
            txtTotal.setText(totalAmt + " ml");


            //retrieving the drank amount
            String drankAmt = cursor.getString(cursor.getColumnIndex(WaterDBHandler.drank_COL));
            //getting the id of text view drank
            TextView txtDrank = findViewById(R.id.DrankAmt);
            //setting the value textView
            txtDrank.setText(drankAmt + " ml");
            //retrieving the remaining amount
            String remainingAmt = cursor.getString(cursor.getColumnIndex(WaterDBHandler.remaining_COL));
            //getting the id of text view remaining
            TextView txtRemain = findViewById(R.id.RemainingAmt);
            //setting the value textView
            if (Double.parseDouble(remainingAmt) <= 0.00) {
                txtRemain.setText("0 ml");//if amount reached set to zero
            } else {
                txtRemain.setText(remainingAmt + " ml");//else set the remaining
            }

            if (Double.parseDouble(totalAmt) <= Double.parseDouble(drankAmt)) {
                //Creating the LayoutInflater instance
                LayoutInflater li = getLayoutInflater();

                //Getting the View object as defined in the custom toast.xml file
                View layout = li.inflate(R.layout.activity_it20198336_custom_toast_welldone, (ViewGroup) findViewById(R.id.custom_toast_welldone));

                //Creating the Toast object
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setView(layout);
                toast.show();
            }

        }

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
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Fitness:
                        startActivity(new Intent(getApplicationContext(), Diet.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Pill:
                        startActivity(new Intent(getApplicationContext(), Pill.class));
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

    public void addDrink(View view) {
        btnAddNewDrink = findViewById(R.id.btnAddDrink);
        btnAddNewDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Waterdashboard.this,Selectwateramount.class);
                Toast.makeText(getApplicationContext(), "Please select a New Drink", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }//end of method

    public void goSettings(View view) {
        btnChangeWaterSettings=findViewById(R.id.btnchangeSettings);
        btnChangeWaterSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Waterdashboard.this,Changewatersettings.class);
                startActivity(i);
            }
        });


    }

}
