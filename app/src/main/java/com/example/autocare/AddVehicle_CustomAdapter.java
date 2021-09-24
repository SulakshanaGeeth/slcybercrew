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

public class AddVehicle_CustomAdapter extends RecyclerView.Adapter<AddVehicle_CustomAdapter.MyViewHolder> {

    private Context context;
    //    Activity activity;
    private ArrayList Addvehicle_id,Addvehicle_name,Addvehicle_year, Addvehicle_no,Addvehicle_detail,Addvehicle_insurense,Addvehicle_fuel;
//    private String model;

    AddVehicle_CustomAdapter(vehicle vehicle, Context context, ArrayList Addvehicle_id, ArrayList Addvehicle_name, ArrayList Addvehicle_year, ArrayList Addvehicle_no, ArrayList Addvehicle_detail, ArrayList Addvehicle_insurense, ArrayList Addvehicle_fuel){

        this.context = context;
        this.Addvehicle_id = Addvehicle_id;
        this.Addvehicle_name = Addvehicle_name;
        this.Addvehicle_year=Addvehicle_year;
        this.Addvehicle_no=Addvehicle_no;
        this.Addvehicle_detail = Addvehicle_detail;
        this.Addvehicle_insurense = Addvehicle_insurense;
        this.Addvehicle_fuel = Addvehicle_fuel;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.vehicle_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.addVehicle_id.setText(String.valueOf(Addvehicle_id.get(position)));
        holder.addVehicle_name.setText(String.valueOf(Addvehicle_name.get(position)));
        holder.addVehicle_year.setText(String.valueOf(Addvehicle_year.get(position)));
        holder.addVehicle_no.setText(String.valueOf(Addvehicle_no.get(position)));
        holder.addVehicle_details.setText(String.valueOf(Addvehicle_detail.get(position)));
        holder.addVehicle_insurence.setText(String.valueOf(Addvehicle_insurense.get(position)));
        holder.addVehicle_type.setText(String.valueOf(Addvehicle_fuel.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, editvehicaldetails.class);
                intent.putExtra("id",String.valueOf(Addvehicle_id.get(position)));
                intent.putExtra("name",String.valueOf(Addvehicle_name.get(position)));
                intent.putExtra("year",String.valueOf(Addvehicle_year.get(position)));
                intent.putExtra("no",String.valueOf(Addvehicle_no.get(position)));
                intent.putExtra("details",String.valueOf(Addvehicle_detail.get(position)));
                intent.putExtra("insurance",String.valueOf(Addvehicle_insurense.get(position)));
                intent.putExtra("fuel",String.valueOf(Addvehicle_fuel.get(position)));

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Addvehicle_id.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView addVehicle_id,addVehicle_name,addVehicle_year,addVehicle_no,addVehicle_details,addVehicle_insurence,addVehicle_type;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            addVehicle_id = itemView.findViewById(R.id.tv_add_vehicle_ID);
            addVehicle_name = itemView.findViewById(R.id.tv_add_vehicle_name);
            addVehicle_year = itemView.findViewById(R.id.tv_add_vehicle_year);
            addVehicle_no = itemView.findViewById(R.id.tv_add_vehicle_no);
            addVehicle_details = itemView.findViewById(R.id.tv_add_vehicle_details);
            addVehicle_insurence = itemView.findViewById(R.id.tv_add_vehicle_insurenceNo);
            addVehicle_type = itemView.findViewById(R.id.tv_add_vehicle_fuel_type);
            mainLayout = itemView.findViewById(R.id.vehicleAdd_mainLayout);
        }
    }
}
