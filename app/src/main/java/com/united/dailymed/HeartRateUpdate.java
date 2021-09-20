package com.united.dailymed;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HeartRateUpdate extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_update);

        // variables for our edit text, button, strings and dbhandler class.
        EditText courseNameEdt;
        TextView idEdt;
        EditText edittext;
        Button updateCourseBtn;
        Button deleteCourseBtn;
        HeartDBHandler dbHandler;
        String courseName, courseDuration, id;


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
        idEdt = findViewById(R.id.idEdtid);
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        edittext = findViewById(R.id.idEdtCourseDuration);
        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn = findViewById(R.id.idBtnDelete);

        // on below line we are initialing our dbhandler class.
        dbHandler = new HeartDBHandler(HeartRateUpdate.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        id = getIntent().getStringExtra("id");
        courseName = getIntent().getStringExtra("rate");
        courseDuration = getIntent().getStringExtra("date");


        // setting data to edit text
        // of our update activity.
        idEdt.setText(id);
        courseNameEdt.setText(courseName);
        edittext.setText(courseDuration);


        final Calendar myCalendar = Calendar.getInstance();


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

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

                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(HeartRateUpdate.this, date, myCalendar
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
                dbHandler.updateHeartRate(idEdt.getText().toString(), courseNameEdt.getText().toString(), edittext.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(HeartRateUpdate.this, "Record Updated Successfully.", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(HeartRateUpdate.this, HeartHistory.class);
                startActivity(i);
            }
        });


        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showAlertDialog(v);

                //calling a method to delete our course.
//                dbHandler.deleteHeartEntry(id);
//                Toast.makeText(HeartRateUpdate.this, "Deleted the course", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(HeartRateUpdate.this, HeartHistory.class);
//                startActivity(i);
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
                HeartDBHandler dbHandler = new HeartDBHandler(HeartRateUpdate.this);
                ;
                String id;
                id = getIntent().getStringExtra("id");
                dbHandler.deleteHeartEntry(id);
                Toast.makeText(HeartRateUpdate.this, "Record Deleted Successfully..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(HeartRateUpdate.this, HeartHistory.class);
                startActivity(i);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(HeartRateUpdate.this, HeartHistory.class);
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }


}
