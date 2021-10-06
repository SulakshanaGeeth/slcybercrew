package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fill_Up_Selectvehicle extends AppCompatActivity {

    AddVehicleDatabaseHelper myDB;
    ArrayList<String> vehicle_id,vehicle_name,vehicle_year, vehicle_no, vehicle_details,vehicle_insuranceno,vehicle_fuel_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_up_selectvehicle);



        ImageView backIcon = findViewById(R.id.back_btn);
        TextView title = findViewById(R.id.toolbar_app_name);

        title.setText("Select Vehicle ");

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Fill_Up_Selectvehicle.this,MainActivity.class);
                startActivity(back);
            }
        });

        myDB = new AddVehicleDatabaseHelper(Fill_Up_Selectvehicle.this);
        vehicle_id = new ArrayList<>();
        vehicle_name = new ArrayList<>();
        vehicle_year = new ArrayList<>();
        vehicle_no = new ArrayList<>();
        vehicle_details = new ArrayList<>();
        vehicle_insuranceno = new ArrayList<>();
        vehicle_fuel_type = new ArrayList<>();

        storeDataInArrays();


        Spinner dropdown = findViewById(R.id.spn_fill_up_select_vehicle);
        String[] items = new String[]{"Toyota", "Nissan", "Honda"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, vehicle_name);
        dropdown.setAdapter(adapter);

        Button next = findViewById(R.id.btn_fill_up_select);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Fill_Ups.class);
                String select = dropdown.getSelectedItem().toString();
                Log.d("selectItem",select);
                intent.putExtra("selectItem", select );
                startActivity(intent);
            }
        });


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