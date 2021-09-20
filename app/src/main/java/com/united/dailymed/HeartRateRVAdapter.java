package com.united.dailymed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HeartRateRVAdapter extends RecyclerView.Adapter<HeartRateRVAdapter.ViewHolder> {




    // variable for our array list and context
    private ArrayList<HeartRateModel> HeartRateModelArrayList;
    private Context context;

    // constructor
    public HeartRateRVAdapter(ArrayList<HeartRateModel> courseModalArrayList, Context context) {
        this.HeartRateModelArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_heart_rate_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        HeartRateModel modal = HeartRateModelArrayList.get(position);
        holder.id.setText(String.valueOf(modal.getId()));
        holder.courseNameTV.setText(modal.getHeartRate());
        holder.courseDescTV.setText(modal.getDate());




        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, HeartRateUpdate.class);

                // below we are passing all our values.
                i.putExtra("id", String.valueOf(modal.getId()));
                i.putExtra("rate", modal.getHeartRate());
                i.putExtra("date", modal.getDate());


                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return HeartRateModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView courseNameTV, courseDescTV,id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            id = itemView.findViewById(R.id.heart_rate_entry_id);
        }
    }


}
