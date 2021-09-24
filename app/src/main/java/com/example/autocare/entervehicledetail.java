package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class entervehicledetail extends AppCompatActivity {

    EditText vehicle_name_input, vehicle_year_input, vehicle_no_input,
            vehicle_details_input, vehicle_insurance_input, vehicle_fuel_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entervehicledetail);

        vehicle_name_input = findViewById(R.id.et_add_vehicle_name);
        vehicle_year_input = findViewById(R.id.et_add_vehicle_year);
        vehicle_no_input = findViewById(R.id.et_add_vehicle_no);
        vehicle_details_input = findViewById(R.id.et_add_vehicle_details);
        vehicle_insurance_input = findViewById(R.id.et_add_insurance_no);
        vehicle_fuel_input = findViewById(R.id.et_add_fuel_type);

        add_button = findViewById(R.id.btn_add_vehicle);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddVehicleDatabaseHelper mydb = new AddVehicleDatabaseHelper(entervehicledetail.this);
                mydb.addVehicle (vehicle_name_input.getText().toString().trim(),
                        Integer.valueOf(vehicle_year_input.getText().toString().trim()),
                        Integer.valueOf(vehicle_no_input.getText().toString().trim()),
                        vehicle_details_input.getText().toString().trim(),
                        Integer.valueOf(vehicle_insurance_input.getText().toString().trim()),
                        vehicle_fuel_input.getText().toString().trim());
                Intent intent = new Intent(getApplicationContext(), vehicle.class);
                startActivity(intent);
            }
        });


        ImageView backIcon = findViewById(R.id.back_btn);
        TextView title = findViewById(R.id.toolbar_app_name);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(entervehicledetail.this,"You clicked home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(entervehicledetail.this,vehicle.class);
                startActivity(intent);
            }
        });

        title.setText("Add Vehicle Details");




    }
}