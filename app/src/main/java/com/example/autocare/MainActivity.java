package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout fill_up = (LinearLayout )findViewById(R.id.ll_Fill_Up);
        fill_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(MainActivity.this,Fill_Ups.class);
                startActivity(picture_intent );
            }
        });
        LinearLayout reminder = (LinearLayout )findViewById(R.id.ll_Reminder);
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(MainActivity.this,Reminder.class);
                startActivity(picture_intent );
            }
        });
    }
}