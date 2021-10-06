package com.example.autocare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ServiceInterface extends AppCompatActivity {
    //service interface add
    RecyclerView recyclerView;
    FloatingActionButton btn_go;

    //retrieve
    ServiceMyDatabase myDB;
    ArrayList<String> service_id, service_type, service_date, service_description, service_present, service_next, service_cost;
    ServiceCustomAdapter serviceCustomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_interface);

        ImageView homeIcon = findViewById(R.id.home_btn);
        ImageView searchIcon = findViewById(R.id.serviceSearch_btn);

        btn_go = findViewById(R.id.floatingActionButton);
        TextView title = findViewById(R.id.toolbar_app_name);

        recyclerView = findViewById(R.id.recyclerView);

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceInterface.this,"You clicked home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiceInterface.this,MainActivity.class);
                startActivity(intent);
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceInterface.this,ServiceSearch.class);

                intent.putExtra("servicetype", service_type );
                startActivity(intent);
            }
        });

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceInterface.this,"You are going to Add New Service Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiceInterface.this,AddService.class);

                startActivity(intent);
            }
        });

        title.setText("Service Interface");

        //retrieve
        myDB = new ServiceMyDatabase(ServiceInterface.this);
        service_id = new ArrayList<>();
        service_type = new ArrayList<>();
        service_date = new ArrayList<>();
        service_description = new ArrayList<>();
        service_present = new ArrayList<>();
        service_next = new ArrayList<>();
        service_cost = new ArrayList<>();

        storeDataInArrays();
        //retrieve
        serviceCustomAdapter = new ServiceCustomAdapter(ServiceInterface.this, this, service_id, service_type, service_date, service_description,
                service_present, service_next, service_cost);
        recyclerView.setAdapter(serviceCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ServiceInterface.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Services to display", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                service_id.add(cursor.getString(0));
                service_type.add(cursor.getString(1));
                service_date.add(cursor.getString(2));
                service_description.add(cursor.getString(3));
                service_present.add(cursor.getString(4));
                service_next.add(cursor.getString(5));
                service_cost.add(cursor.getString(6));
            }
        }
    }
}