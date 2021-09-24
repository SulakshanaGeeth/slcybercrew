package com.example.autocare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fill_Up_CustomAdapter extends RecyclerView.Adapter<Fill_Up_CustomAdapter.MyViewHolder> {

   private Context context;
   Activity activity;
   private ArrayList fillUp_id,fillUp_date,fillUp_qty, fillUp_price,fillUp_meter;
   private String model;

   Fill_Up_CustomAdapter(Activity activity,Context context,ArrayList fillUp_id ,ArrayList fillUp_date,ArrayList fillUp_qty, ArrayList fillUp_price,ArrayList fillUp_meter,String model){
         this.activity = activity;
         this.context = context;
         this.fillUp_id = fillUp_id;
         this.fillUp_date = fillUp_date;
         this.fillUp_qty=fillUp_qty;
         this.fillUp_price=fillUp_price;
         this.fillUp_meter = fillUp_meter;
         this.model=model;
   }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fillup_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.fill_Up_id.setText(String.valueOf(fillUp_id.get(position)));
        holder.fill_Up_date.setText(String.valueOf(fillUp_date.get(position)));
        holder.fillUp_qty.setText(String.valueOf(fillUp_qty.get(position)));
        holder.fillUp_price.setText(String.valueOf(fillUp_price.get(position)));
        holder.fillUp_meter.setText(String.valueOf(fillUp_meter.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Fill_Up_Update.class);
                intent.putExtra("id",String.valueOf(fillUp_id.get(position)));
                intent.putExtra("date",String.valueOf(fillUp_date.get(position)));
                intent.putExtra("qty",String.valueOf(fillUp_qty.get(position)));
                intent.putExtra("price",String.valueOf(fillUp_price.get(position)));
                intent.putExtra("meter",String.valueOf(fillUp_meter.get(position)));
                intent.putExtra("vehicle", model );
                Log.d("car",model);
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fillUp_qty.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

       TextView fill_Up_id,fill_Up_date,fillUp_qty, fillUp_price,fillUp_meter;
       LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fill_Up_id = itemView.findViewById(R.id.tv_fillUp_id);
            fill_Up_date = itemView.findViewById(R.id.tv_fillUp_date);
            fillUp_qty = itemView.findViewById(R.id.tv_fillUp_litters);
            fillUp_price = itemView.findViewById(R.id.tv_fillUp_price);
            fillUp_meter = itemView.findViewById(R.id.tv_fillUp_meter);

            mainLayout = itemView.findViewById(R.id.fillUp_mainLayout);
        }
    }

}
