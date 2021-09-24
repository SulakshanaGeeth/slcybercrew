package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class vehicle extends AppCompatActivity {

    RecyclerView recyclerView;

    AddVehicleDatabaseHelper myDB;
    ArrayList<String> vehicle_id,vehicle_name,vehicle_year, vehicle_no, vehicle_details,vehicle_insuranceno,vehicle_fuel_type;

    AddVehicle_CustomAdapter addVehicle_customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);



        Button button = findViewById(R.id.button2);
        ImageView homeIcon = findViewById(R.id.home_btn);

        TextView title = findViewById(R.id.toolbar_app_name);

        recyclerView = findViewById(R.id.recycle_view_vehicle);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vehicle.this,"You are going to add fill-up page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(vehicle.this,entervehicledetail.class);
                startActivity(intent);
            }
        });


        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vehicle.this,"You clicked home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(vehicle.this,MainActivity.class);
                startActivity(intent);
            }
        });

        title.setText("Vehicles");

        myDB = new AddVehicleDatabaseHelper(vehicle.this);
        vehicle_id = new ArrayList<>();
        vehicle_name = new ArrayList<>();
        vehicle_year = new ArrayList<>();
        vehicle_no = new ArrayList<>();
        vehicle_details = new ArrayList<>();
        vehicle_insuranceno = new ArrayList<>();
        vehicle_fuel_type = new ArrayList<>();

        storeDataInArrays();

        addVehicle_customAdapter = new AddVehicle_CustomAdapter(vehicle.this,this,vehicle_id,vehicle_name,vehicle_year,vehicle_no,vehicle_details,vehicle_insuranceno,vehicle_fuel_type);
        recyclerView.setAdapter(addVehicle_customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(vehicle.this));

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()) {
                vehicle_id.add(cursor.getString(0));
                vehicle_name.add(cursor.getString(1));
                vehicle_year.add(cursor.getString(2));
                vehicle_no.add(cursor.getString(3));
                vehicle_details.add(cursor.getString(4));
                vehicle_insuranceno.add(cursor.getString(5));
                vehicle_fuel_type.add(cursor.getString(6));
            }
        }
    }


}