package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class Adddetails extends AppCompatActivity {

    EditText title_input, date_input, mileage_input;
    Button add_button,add_button2;
   AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddetails);
        TextView title = findViewById(R.id.toolbar_app_name);
        title_input = findViewById(R.id.title_input);
        date_input = findViewById(R.id.date_input);
        mileage_input = findViewById(R.id.mileage_input);
        add_button = findViewById(R.id.add_button);
        add_button2 = findViewById(R.id.btn_alert45);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.title_input, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.date_input, RegexTemplate.NOT_EMPTY,R.string.invalid_date);
        awesomeValidation.addValidation(this,R.id.mileage_input, RegexTemplate.NOT_EMPTY,R.string.invalid_mileage);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){

                    Toast.makeText(getApplicationContext(),"Form validate successfully",Toast.LENGTH_SHORT).show();
                    MyDatabaseHelper myDB = new MyDatabaseHelper(Adddetails.this);
                    myDB.addbook(title_input.getText().toString().trim(),
                            date_input.getText().toString().trim(),
                            Integer.valueOf(mileage_input.getText().toString().trim()));

                }else{
                    Toast.makeText(getApplicationContext(),"Form validate Failed",Toast.LENGTH_SHORT).show();



                }

            }
        });
        add_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!title_input.getText().toString().isEmpty() && !date_input.getText().toString().isEmpty() && !mileage_input
                        .getText().toString().isEmpty()) {

                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, title_input.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, date_input.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, mileage_input.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                    intent.putExtra(Intent.EXTRA_EMAIL, "test@yahoo.com, test2@yahoo.com, test3@yahoo.com");

                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(Adddetails.this, "There is no app that support this action", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(Adddetails.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });



        ImageView backIcon = findViewById(R.id.back_btn);


        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Adddetails.this,"You clicked back button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Adddetails.this,Reminder.class);
                startActivity(intent);
            }
        });
        title.setText("Add details");

    }
}