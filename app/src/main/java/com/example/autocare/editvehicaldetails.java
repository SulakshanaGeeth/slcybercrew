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

public class editvehicaldetails extends AppCompatActivity {

    EditText vehicle_name_input, vehicle_year_input, vehicle_no_input,
            vehicle_details_input, vehicle_insurance_input, vehicle_fuel_input;
    Button update_button,delete_button;

    String id,name,year,no,details,insurance,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editvehicaldetails);

        TextView title = findViewById(R.id.toolbar_app_name);
        title.setText("Edit Vehicle Details");

        ImageView backIcon = findViewById(R.id.back_btn);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editvehicaldetails.this,vehicle.class);
                startActivity(intent);
            }
        });


        vehicle_name_input = findViewById(R.id.et_edit_vehicle_name);
        vehicle_year_input = findViewById(R.id.et_edit_vehicle_year);
        vehicle_no_input = findViewById(R.id.et_edit_vehicle_no);
        vehicle_details_input = findViewById(R.id.et_edit_vehicle_details);
        vehicle_insurance_input = findViewById(R.id.et_edit_insurance_no);
        vehicle_fuel_input = findViewById(R.id.et_edit_fuel_type);

        update_button = findViewById(R.id.btn_edit_vehicle);
        delete_button = findViewById(R.id.btn_delete_vehicle);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddVehicleDatabaseHelper myDB = new AddVehicleDatabaseHelper(editvehicaldetails.this);
                //and only then we call this
                myDB.updateData(id,vehicle_name_input.getText().toString().trim(),vehicle_year_input.getText().toString().trim(),
                        vehicle_no_input.getText().toString().trim(),vehicle_details_input.getText().toString().trim(),
                        vehicle_insurance_input.getText().toString().trim(),vehicle_fuel_input.getText().toString().trim());
                Intent intent = new Intent(getApplicationContext(), vehicle.class);
                startActivity(intent);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddVehicleDatabaseHelper mydb = new AddVehicleDatabaseHelper((editvehicaldetails.this));
                mydb.deleteOneRow(id);
                Intent intent = new Intent(getApplicationContext(), vehicle.class);
                startActivity(intent);
            }
        });

        getAndSetIntentData();


    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("year") && getIntent().hasExtra("no") && getIntent().hasExtra("details")
                && getIntent().hasExtra("insurance")&& getIntent().hasExtra("fuel")){
            //getting data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            year = getIntent().getStringExtra("year");
            no = getIntent().getStringExtra("no");
            details = getIntent().getStringExtra("details");
            insurance = getIntent().getStringExtra("insurance");
            type = getIntent().getStringExtra("fuel");

            //setting Intent Data
            vehicle_name_input.setText(name);
            vehicle_year_input.setText(year);
            vehicle_no_input.setText(no);
            vehicle_details_input.setText(details);
            vehicle_insurance_input.setText(insurance);
            vehicle_fuel_input.setText(type);
        }
        else{
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
    }


}