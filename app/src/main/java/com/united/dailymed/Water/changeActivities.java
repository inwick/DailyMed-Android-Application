package com.united.dailymed;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.united.dailymed.Diet.Diet;
import com.united.dailymed.Heart.Heart;
import com.united.dailymed.Home.Home;
import com.united.dailymed.Pill.Pill;
import com.united.dailymed.Utils.WaterDBHandler;
import com.united.dailymed.Water.Water;
import com.united.dailymed.Water.Waterdashboard;

public class changeActivities extends AppCompatActivity {

    Dialog myDialog;
    Button btngotopopup;
    Button btnChangeTips;
    Button btnDailyActivities;
    RadioGroup rgNewActivities;
    Button btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupactivities);


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
                        startActivity(new Intent(getApplicationContext(), Water.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }

        });

    }

        /* POP UP TO CHANGE Activities */
    public void changeActivities(View view) {

        btnDailyActivities = findViewById(R.id.btnActivities);
        btnReset = findViewById(R.id.btnSetGender);
        rgNewActivities = findViewById(R.id.GenderGroup);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivities(rgNewActivities);//calling update method to change the time
                Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();

            }
        });


    }//end of on click

    /* UPDATE METHOD TO CHANGE Activities */
    public void changeActivities(RadioGroup rgNewActivities) {
        WaterDBHandler waterdbhandler = new WaterDBHandler(this);
        //System.out.println("Look here 2");
        // System.out.println(rgNewTime);
        RadioButton radioButton = findViewById(rgNewActivities.getCheckedRadioButtonId());
        //System.out.println("look here 3:"+radioButton.getText().toString());

        //calling the update method in db helper
        long val = waterdbhandler.updateActivities((radioButton.getText().toString()));

        if (val > 0) {
            Intent i = new Intent(changeActivities.this, Waterdashboard.class);
            startActivity(i);

        } else {
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();

        }
    }//end of method

}




