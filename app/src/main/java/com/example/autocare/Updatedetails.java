package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Updatedetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedetails);
        Button button = findViewById(R.id.btn_up1);
        TextView title = findViewById(R.id.toolbar_app_name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Updatedetails.this,UpdateH.class);
                startActivity(intent);
            }
        });

        ImageView homeIcon = findViewById(R.id.home_btn);

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Updatedetails.this,"You clicked home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Updatedetails.this,MainActivity.class);
                startActivity(intent);
            }
        });
        title.setText("Update details");

    }
}