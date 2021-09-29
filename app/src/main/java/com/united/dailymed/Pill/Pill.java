package com.united.dailymed.Pill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;

import android.view.View;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.united.dailymed.Adapter.PillAdapter;
import com.united.dailymed.Diet.Diet;
import com.united.dailymed.Heart.Heart;
import com.united.dailymed.Home.Home;
import com.united.dailymed.Model.PillModel;
import com.united.dailymed.R;
import com.united.dailymed.Utils.DatabaseHandler;
import com.united.dailymed.Water.Water;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pill extends AppCompatActivity implements DialogCloseListener {

    //define RecyclerView
    private RecyclerView pillsRecyclerView;
    private PillAdapter pAdapter;
    private FloatingActionButton fab;

    private List<PillModel> pillList;
    private DatabaseHandler db;




    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 15000;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gestureDetector = new GestureDetector( this, new SwipeDetector());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_pills);


        //Initialize And Assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Pill Selected
        bottomNavigationView.setSelectedItemId(R.id.Pill);

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
                        return true;
                    case R.id.Water:
                        startActivity(new Intent(getApplicationContext(), Water.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }

        });


        //data retrieving

        db = new DatabaseHandler(this);
        db.openDatabase();

        pillList = new ArrayList<>();

        pillsRecyclerView = findViewById(R.id.pillsRecyclerView);
        pillsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pAdapter = new PillAdapter(db,this);
        pillsRecyclerView.setAdapter(pAdapter);

        fab = findViewById(R.id.fab);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(pAdapter));
        itemTouchHelper.attachToRecyclerView(pillsRecyclerView);

        pillList = db.getAllPills();
        //add new pill to top
        Collections.reverse(pillList);
        pAdapter.setPills(pillList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewPill.newInstance().show(getSupportFragmentManager(), AddNewPill.TAG);

            }
        });

    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        pillList = db.getAllPills();
        //add new pill details to top
        Collections.reverse(pillList);
        pAdapter.setPills(pillList);
        pAdapter.notifyDataSetChanged();
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


    private void onRightSwipe() {
        Intent i = new Intent(Pill.this, Heart.class);
        startActivity(i);
    }

}