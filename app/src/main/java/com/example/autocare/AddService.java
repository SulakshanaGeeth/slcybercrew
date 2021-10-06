package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class AddService extends AppCompatActivity {
    EditText type_input, date_input, description_input, present_input, next_input, cost_input;
    Button addService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        TextView title = findViewById(R.id.toolbar_app_name);
        ImageView backIcon = findViewById(R.id.back_btn);


        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddService.this,"You clicked back button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddService.this,ServiceInterface.class);
                startActivity(intent);
            }
        });

        //add service
        type_input = findViewById(R.id.input_type);
        date_input = findViewById(R.id.input_date);
        description_input = findViewById(R.id.input_description);
        present_input = findViewById(R.id.input_present);
        next_input = findViewById(R.id.input_next);
        cost_input = findViewById(R.id.input_total);
        addService = findViewById(R.id.btn_addService);

        //validations
        AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.input_type, RegexTemplate.NOT_EMPTY,R.string.service_input_empty);
        awesomeValidation.addValidation(this,R.id.input_date, RegexTemplate.NOT_EMPTY,R.string.service_input_empty);
        awesomeValidation.addValidation(this,R.id.input_description, RegexTemplate.NOT_EMPTY,R.string.service_input_empty);
        awesomeValidation.addValidation(this,R.id.input_present, RegexTemplate.NOT_EMPTY,R.string.service_input_empty);
        awesomeValidation.addValidation(this,R.id.input_next, RegexTemplate.NOT_EMPTY,R.string.service_input_empty);
        awesomeValidation.addValidation(this,R.id.input_total, RegexTemplate.NOT_EMPTY,R.string.service_input_empty);

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                ServiceMyDatabase myDB = new ServiceMyDatabase(AddService.this);
                myDB.addService(type_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        description_input.getText().toString().trim(),
                        Integer.valueOf(present_input.getText().toString().trim()),
                        Integer.valueOf(next_input.getText().toString().trim()),
                        Integer.valueOf(cost_input.getText().toString().trim()));
                Log.d("name1", type_input.getText().toString().trim());
                } else {
                    Toast.makeText(getApplicationContext(), "Validation has been Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        title.setText("Add Service Details");
    }
}