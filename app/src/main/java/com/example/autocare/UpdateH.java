package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateH extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_h);

        ImageView homeIcon = findViewById(R.id.home_btn);
        TextView title = findViewById(R.id.toolbar_app_name);

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateH.this,"You clicked home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateH.this,MainActivity.class);
                startActivity(intent);
            }
        });
        title.setText("Update here");
    }
}