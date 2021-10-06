package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceSearch extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_search);

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);

        ImageView backIcon = findViewById(R.id.back_btn);
        TextView title = findViewById(R.id.toolbar_app_name);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ServiceSearch.this,"You clicked back button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiceSearch.this,ServiceInterface.class);
                startActivity(intent);
            }
        });

        list = new ArrayList<>();
        Intent intent = getIntent();

        //ArrayList<HashMap<String,String>> hashMap = (ArrayList(HashMap<String, String>)) intent.getSerializableExtra("hashMap");

        ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra("servicetype");
        //textView.setText(String.valueOf(list));

        //ArrayList<String> service_type = intent.getStringExtra("servicetype");
       // Log.d("type",service_type);


        /*list.add("Apple");
        list.add("Banana");
        list.add("Pineapple");
        list.add("Orange");
        list.add("Lychee");
        list.add("Guava");
        list.add("Peach");
        list.add("Melon");
        list.add("Watermelon");
        list.add("Papaya");*/

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(ServiceSearch.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });

        title.setText("Search Service");
    }
}