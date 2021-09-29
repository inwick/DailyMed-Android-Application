package com.united.dailymed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class changeGender extends AppCompatActivity {


    Button btngotopopup;

    Button btnChangeGender;
    RadioGroup  rgNewGender;
    Button btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupgender);

    }

    /********************************************* POP UP TO CHANGE gender ********************************************************/
    public void changeGender(View view) {

        btnChangeGender = findViewById(R.id.btnGender);
        btnReset = findViewById(R.id.btnSetActivities);
        rgNewGender = findViewById(R.id.GenderGroup);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeGender(rgNewGender);//calling update method to change the time
                Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();

            }
        });


    }//end of on click

    /********************************************* UPDATE METHOD TO CHANGE gender ********************************************************/
    public void changeGender(RadioGroup rgNewGender) {
        WaterDBHandler waterdbhandler = new WaterDBHandler(this);
        //System.out.println("Look here 2");
        // System.out.println(rgNewTime);
        RadioButton radioButton = findViewById(rgNewGender.getCheckedRadioButtonId());
        //System.out.println("look here 3:"+radioButton.getText().toString());

        //calling the update method in db helper
        long val = waterdbhandler.updateGender((radioButton.getText().toString()));

        if (val > 0) {
            Intent i = new Intent(changeGender.this, Waterdashboard.class);
            startActivity(i);

        } else {
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();

        }
    }//end of method

}




