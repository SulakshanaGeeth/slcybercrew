package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Reminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Button button = findViewById(R.id.btn_plus);
        Button btn = findViewById(R.id.btn_x);
        Button btn2 = findViewById(R.id.btn_y);

        TextView title = findViewById(R.id.toolbar_app_name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reminder.this, Adddetails.class);

                startActivity(intent);
            }

        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reminder.this, Updatedetails.class);

                startActivity(intent);
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reminder.this, Deletereminders.class);

                startActivity(intent);
            }

        });
        title.setText("Reminder");




        ImageView homeIcon = findViewById(R.id.home_btn);
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Reminder.this,"You clicked home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Reminder.this,  MainActivity.class);

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

    }
}