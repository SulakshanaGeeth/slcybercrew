package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class entervehicledetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entervehicledetail);

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