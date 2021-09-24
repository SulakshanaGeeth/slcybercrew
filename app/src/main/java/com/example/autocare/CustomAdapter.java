package com.example.autocare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList car_id, car_title, car_date, car_mileage;


    CustomAdapter(Activity activity, Context context, ArrayList car_id, ArrayList car_title, ArrayList car_date,
                  ArrayList car_mileage){
        this.activity = activity;
        this.context = context;
        this.car_id = car_id;
        this.car_title = car_title;
        this.car_date = car_date;
        this.car_mileage = car_mileage;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.car_id_txt.setText(String.valueOf(car_id.get(position)));
        holder.car_title_txt.setText(String.valueOf(car_title.get(position)));
        holder.car_date_txt.setText(String.valueOf(car_date.get(position)));
        holder.car_mileage_txt.setText(String.valueOf(car_mileage.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(car_id.get(position)));
                intent.putExtra("title", String.valueOf(car_title.get(position)));
                intent.putExtra("date", String.valueOf(car_date.get(position)));
                intent.putExtra("mileage", String.valueOf(car_mileage.get(position)));
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return car_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView car_id_txt, car_title_txt, car_date_txt, car_mileage_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            car_id_txt = itemView.findViewById(R.id.car_id_txt);
            car_title_txt = itemView.findViewById(R.id.car_title_txt);
            car_date_txt = itemView.findViewById(R.id.car_date_txt);
            car_mileage_txt = itemView.findViewById(R.id.car_mileage_txt);
            mainLayout = itemView.findViewById(R.id.myrowLayout);


        }

    }

}