package com.united.dailymed.Water;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;

import android.view.MotionEvent;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.united.dailymed.Diet.Diet;
import com.united.dailymed.Heart.Heart;
import com.united.dailymed.Home.Home;
import com.united.dailymed.Pill.Pill;
import com.united.dailymed.R;

public class Water extends AppCompatActivity {
    Button btnGo;
    Button btnGoDashboard;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        gestureDetector = new GestureDetector( this, new SwipeDetector());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        btnGo= findViewById(R.id.btngo);
        btnGoDashboard=findViewById(R.id.btngodashboard);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Water.this,waterDetails.class);
                Toast.makeText(getApplicationContext(), "Welcome!Let's get started", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        btnGoDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Water.this,Waterdashboard.class);
                Toast.makeText(getApplicationContext(), "Welcome!Let's get started", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        //Initialize And Assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
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
                        return true;
                }

                return false;
            }

        });
    }

    public class SwipeDetector extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {

            // Check movement along the Y-axis. If it exceeds SWIPE_MAX_OFF_PATH,
            // then dismiss the swipe.
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
            {
                return false;
            }

            //toast( "start = "+String.valueOf( e1.getX() )+" | end = "+String.valueOf( e2.getX() )  );
            //from left to right
            if( e2.getX() > e1.getX() )
            {
                if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
                {
                    onLeftSwipe();
                    return true;
                }
            }

            if( e1.getX() > e2.getX() )
            {
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
                {
                    onRightSwipe();
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        // TouchEvent dispatcher.
        if (gestureDetector != null)
        {
            if (gestureDetector.onTouchEvent(ev))
                // If the gestureDetector handles the event, a swipe has been
                // executed and no more needs to be done.
                return true;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return gestureDetector.onTouchEvent(event);
    }

    private void onLeftSwipe() {
        Intent i = new Intent(Water.this, Home.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void onRightSwipe() {
        Intent i = new Intent(Water.this, Diet.class);
        startActivity(i);
    }

}