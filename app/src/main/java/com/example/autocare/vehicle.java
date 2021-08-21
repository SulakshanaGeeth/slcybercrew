package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class vehicle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);



        Button button = findViewById(R.id.button2);
        ImageView homeIcon = findViewById(R.id.home_btn);
        ImageButton yaristwo = findViewById(R.id.imageButton4);
        TextView title = findViewById(R.id.toolbar_app_name);

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




        yaristwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vehicle.this,"You are going to add fill-up page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(vehicle.this,viewvehicle.class);
                startActivity(intent);
            }
        });

        title.setText("Vehicles");







    }
}