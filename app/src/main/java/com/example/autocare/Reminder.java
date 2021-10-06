package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Reminder extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView no_data;
    ImageView empty_imageview;
    Button add_button;
    Button set_alert;
    MyDatabaseHelper myDB;
    ArrayList<String> car_id, car_title, car_date, car_mileage;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.btn_plus);
        set_alert = findViewById(R.id.btn_setalert);

        TextView title = findViewById(R.id.toolbar_app_name);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reminder.this, Adddetails.class);

                startActivity(intent);
            }

        });
        set_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reminder.this, Notification.class);

                startActivity(intent);
            }

        });


        myDB = new MyDatabaseHelper(Reminder.this);
        car_id = new ArrayList<>();
        car_title = new ArrayList<>();
        car_date = new ArrayList<>();
        car_mileage = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(Reminder.this, this, car_id, car_title, car_date,
                car_mileage);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Reminder.this));


        ImageView homeIcon = findViewById(R.id.home_btn);
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Reminder.this, "You clicked home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Reminder.this, MainActivity.class);

                startActivity(intent);
            }
        });
        title.setText("Reminder");


    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {

            Toast.makeText(Reminder.this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                car_id.add(cursor.getString(0));
                car_title.add(cursor.getString(1));
                car_date.add(cursor.getString(2));
                car_mileage.add(cursor.getString(3));

            }


        }

    }
}
