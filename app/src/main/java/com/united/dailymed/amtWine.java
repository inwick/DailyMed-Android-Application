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

public class amtWine extends AppCompatActivity {

    SeekBar seekWine;
    TextView txtWine;
    Button btnWineAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20198336_createnewwinecup);




        //getting the ids of required components
        seekWine = findViewById(R.id.seekBarWine);
        txtWine = findViewById(R.id.txtamountwine);
        btnWineAdded = findViewById(R.id.btnWineDone);

        //setting the method when seek bar value is changed
        seekWine.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                txtWine.setText(progress + " ");//setting the progress value to text view
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    /********************************************* UPDATING AMOUNT WHEN A NEW AMOUNT IS ADDED ********************************************************/
    public void updateAmount(View view){
        WaterDBHandler waterdbhandler=new WaterDBHandler(this);

        Double drankAlready = waterdbhandler.getDrank();
        // Double totDrank = (Double.parseDouble(txtCoffee.getText().toString())*0.8)+drankAlready;

        Double amt = Double.parseDouble(txtWine.getText().toString());

        Double totDrank = calcDrank(drankAlready,amt);

        Double remainingAlready = waterdbhandler.getRemainingAmt();
        Double totRemaining = calRemaining(remainingAlready,amt);
        // Double totRemaining = remainingAlready - (Double.parseDouble(txtCoffee.getText().toString())*0.8);


        int val=waterdbhandler.updateInfo(totDrank,totRemaining);

        if(val>0)
        {

            //Creating the LayoutInflater instance
//            LayoutInflater li = getLayoutInflater();

            //Getting the View object as defined in the custom toast.xml file
            //View layout = li.inflate(R.layout.custom_toast_coffee,(ViewGroup) findViewById(R.id.custom_toast_coffee));

            //Creating the Toast object
//            Toast toast = new Toast(getApplicationContext());
//            toast.setDuration(Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
//            toast.setView(layout);
//            toast.show();

            Intent i = new Intent(amtWine.this,Waterdashboard.class);
            startActivity(i);

        }
        else
        {
            Toast.makeText(this,"Could Not update! ",Toast.LENGTH_SHORT).show();
        }
    }//end of method

    /******************************************** CALCULATING THE DRANK AMOUNT *******************************************************************/
    public Double calcDrank(Double drankAlready,Double amountNow){
        return (drankAlready+(amountNow));
    }

    /******************************************** CALCULATING THE REMAINING AMOUNT *******************************************************************/
    public Double calRemaining(Double remain, Double amt) {
        return(remain - (amt));
    }











    /********************************************* ON CLICK METHOD WHEN CANCELLED ADDITION OF DRINK ********************************************************/
    public void cancelWine(View v){
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
                Intent i = new Intent(amtWine.this,Selectwateramount.class);
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











