package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_interface);

        ImageView homeIcon = findViewById(R.id.home_btn);
        ImageButton imageButton = findViewById(R.id.imageButton2);
        Button button1 = findViewById(R.id.button3);
        TextView title = findViewById(R.id.toolbar_app_name);

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceInterface.this,"You clicked home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiceInterface.this,MainActivity.class);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceInterface.this,"You are going to Service Details Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiceInterface.this,ViewServiceDetails.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceInterface.this,"You are going to Add New Service Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiceInterface.this,AddService.class);
                startActivity(intent);
            }
        });

        Spinner dropdown = findViewById(R.id.spn_select_vehicle);
//create a list of items for the spinner.
        String[] items = new String[]{"Vehicle01", "Vehicle02", "Vehicle03"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        title.setText("Service Interface");
    }
}