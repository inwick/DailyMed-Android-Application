package com.united.dailymed;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class amtCoffee extends AppCompatActivity {


    SeekBar seekCoffee;
    TextView txtCoffee;
    Button btnCoffeeAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20198336_createnewcoffeecup);

        //getting the ids of required components
        seekCoffee = findViewById(R.id.seekBarCustom);
        txtCoffee = findViewById(R.id.txtamountcustom);
        btnCoffeeAdded = findViewById(R.id.btnCustomDone);

        //setting the method when seek bar value is changed
        seekCoffee.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                txtCoffee.setText(progress + " ");//setting the progress value to text view
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Heart Selected
        bottomNavigationView.setSelectedItemId(R.id.Heart);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.Heart:
                        startActivity(new Intent(getApplicationContext(),Heart.class));
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

    /* UPDATING AMOUNT WHEN A NEW AMOUNT IS ADDED */
    public void updateAmount(View view){
        WaterDBHandler waterdbhandler=new WaterDBHandler(this);

        Double drankAlready = waterdbhandler.getDrank();
        Double amt = Double.parseDouble(txtCoffee.getText().toString());
        Double totDrank = calcDrank(drankAlready,amt);
        Double remainingAlready = waterdbhandler.getRemainingAmt();
        Double totRemaining = calRemaining(remainingAlready,amt);
        int val=waterdbhandler.updateInfo(totDrank,totRemaining);

        if(val>0)
        {
            //Creating the LayoutInflater instance
            LayoutInflater li = getLayoutInflater();
            //Getting the View object as defined in the custom toast.xml file
            View layout = li.inflate(R.layout.activity_it20198336_custom_toast_coffee,(ViewGroup) findViewById(R.id.custom_toast_juice));
            //Creating the Toast object
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.setView(layout);
            toast.show();
            Intent i = new Intent(amtCoffee.this,Waterdashboard.class);
            startActivity(i);

        }
        else
        {
            Toast.makeText(this,"Could Not update! ",Toast.LENGTH_SHORT).show();
        }
    }//end of method

    /* CALCULATING THE DRANK AMOUNT */
    public Double calcDrank(Double drankAlready,Double amountNow){
        return (drankAlready+(amountNow));
    }

    /* CALCULATING THE REMAINING AMOUNT **/
    public Double calRemaining(Double remain, Double amt) {
        return(remain - (amt));
    }



    /* ON CLICK METHOD WHEN CANCELLED ADDITION OF DRINK */
    public void cancelCoffee(View v){
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
                Intent i = new Intent(amtCoffee.this,Selectwateramount.class);
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
