package com.united.dailymed;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class amtEnergyDrink extends AppCompatActivity {




    SeekBar seekEnergyDrink;
    TextView txtEnergyDrink;
    Button btnEnergyDrinkAdded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20198336_createnewenergydrinkcup);




        //getting the ids of required components
        seekEnergyDrink = findViewById(R.id.seekBarEnergyDrink);
        txtEnergyDrink = findViewById(R.id.txtamountenergydrink);
        btnEnergyDrinkAdded = findViewById(R.id.btnEnergyDrinkDone);

        //setting the method when seek bar value is changed
        seekEnergyDrink.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                txtEnergyDrink.setText(progress + " ");//setting the progress value to text view
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }



    /********************************************* ON CLICK METHOD WHEN CANCELLED ADDITION OF DRINK ********************************************************/
    public void cancelEnergyDrink(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Cancel Adding Drink!!");

        // Icon Of Alert Dialog
        // alertDialogBuilder.setIcon(R.drawable.warning);

        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Do you really want to cancel adding this drink??");
        alertDialogBuilder.setCancelable(false);

        //positive response action
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent i = new Intent(amtEnergyDrink.this,Selectwateramount.class);
                startActivity(i);
            }
        });

        //negative response action
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked No! Please select the amount", Toast.LENGTH_SHORT).show();
            }
        });

        //creating and displaying alert dialog box
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();



    }
}











