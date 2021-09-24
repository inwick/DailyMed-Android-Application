package com.united.dailymed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditDietPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diet_plan);

        // variables for our edit text, button, strings and dbhandler class.
        EditText courseNameEdt;
        TextView idEdt;
        EditText date,bfirst,lunch,dinner;
        Button updateCourseBtn;
        Button deleteCourseBtn;
        DietDBHandler dbHandler;
        String ID,DATE,BFIRST,LUNCH,DINNER;


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Heart Selected
        bottomNavigationView.setSelectedItemId(R.id.Heart);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Heart:
                        startActivity(new Intent(getApplicationContext(), Heart.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Fitness:
                        startActivity(new Intent(getApplicationContext(), Fitness.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Pill:
                        startActivity(new Intent(getApplicationContext(), Pill.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Water:
                        startActivity(new Intent(getApplicationContext(), Water.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }

        });


        // initializing all our variables.
        idEdt = findViewById(R.id.diet_update_id);
        date = findViewById(R.id.diet_update_date);
        bfirst = findViewById(R.id.diet_update_breakfast);
        lunch = findViewById(R.id.diet_update_lunch);
        dinner = findViewById(R.id.diet_update_dinner);
        updateCourseBtn = findViewById(R.id.diet_btn_update_diet_plan);
        deleteCourseBtn = findViewById(R.id.diet_btn_delete_diet_plan);

        // on below line we are initialing our dbhandler class.
        dbHandler = new DietDBHandler(EditDietPlan.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        ID = getIntent().getStringExtra("Id");
        DATE = getIntent().getStringExtra("Date");
        BFIRST = getIntent().getStringExtra("Bfirst");
        LUNCH = getIntent().getStringExtra("Lunch");
        DINNER = getIntent().getStringExtra("Dinner");


        // setting data to edit text
        // of our update activity.
        idEdt.setText(ID);
        date.setText(DATE);
        bfirst.setText(BFIRST);
        lunch.setText(LUNCH);
        dinner.setText(DINNER);


        final Calendar myCalendar = Calendar.getInstance();


        DatePickerDialog.OnDateSetListener dpicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                date.setText(sdf.format(myCalendar.getTime()));
            }

        };

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditDietPlan.this, dpicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // adding on click listener to our update course button.
        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHandler.updateDiet(idEdt.getText().toString(), date.getText().toString(), bfirst.getText().toString(), lunch.getText().toString(), dinner.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(EditDietPlan.this, "Record Updated Successfully.", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(EditDietPlan.this, DietPlan.class);
                startActivity(i);
            }
        });


        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showAlertDialog(v);


            }
        });

    }

    public void showAlertDialog(View v) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("DELETE CONFIRMATION");
        alert.setMessage("Are you sure to delete record?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               DietDBHandler dbHandler = new DietDBHandler(EditDietPlan.this);
                ;
                String id;
                id = getIntent().getStringExtra("id");
                dbHandler.deletePlan(id);
                Toast.makeText(EditDietPlan.this, "Record Deleted Successfully..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditDietPlan.this, DietPlan.class);
                startActivity(i);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(EditDietPlan.this, DietPlan.class);
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
}