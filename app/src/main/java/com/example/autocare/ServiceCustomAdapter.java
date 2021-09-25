package com.example.autocare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ServiceCustomAdapter extends RecyclerView.Adapter<ServiceCustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList service_id, service_type, service_date, service_description, service_present, service_next, service_cost;

    ServiceCustomAdapter(Activity activity, Context context, ArrayList service_id, ArrayList service_type, ArrayList service_date, ArrayList service_description,
                         ArrayList service_present, ArrayList service_next, ArrayList service_cost){
        this.activity = activity;
        this.context = context;
        this.service_id = service_id;
        this.service_type = service_type;
        this.service_date = service_date;
        this.service_description = service_description;
        this.service_present = service_present;
        this.service_next = service_next;
        this.service_cost = service_cost;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.service_my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.service_id_txt.setText(String.valueOf(service_id.get(position)));
        holder.service_type_txt.setText(String.valueOf(service_type.get(position)));
        holder.service_date_txt.setText(String.valueOf(service_date.get(position)));
        holder.service_description_txt.setText(String.valueOf(service_description.get(position)));
        holder.service_present_txt.setText(String.valueOf(service_present.get(position)));
        holder.service_next_txt.setText(String.valueOf(service_next.get(position)));
        holder.service_cost_txt.setText(String.valueOf(service_cost.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateService.class);
                intent.putExtra("id", String.valueOf(service_id.get(position)));
                intent.putExtra("type", String.valueOf(service_type.get(position)));
                intent.putExtra("date", String.valueOf(service_date.get(position)));
                intent.putExtra("description", String.valueOf(service_description.get(position)));
                intent.putExtra("present", String.valueOf(service_present.get(position)));
                intent.putExtra("next", String.valueOf(service_next.get(position)));
                intent.putExtra("cost", String.valueOf(service_cost.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return service_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView service_id_txt, service_type_txt, service_date_txt, service_description_txt, service_present_txt, service_next_txt, service_cost_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            service_id_txt = itemView.findViewById(R.id.service_id_txt);
            service_type_txt = itemView.findViewById(R.id.service_type_txt);
            service_date_txt = itemView.findViewById(R.id.service_date_txt);
            service_description_txt = itemView.findViewById(R.id.service_description_txt);
            service_present_txt = itemView.findViewById(R.id.service_present_txt);
            service_next_txt = itemView.findViewById(R.id.service_next_txt);
            service_cost_txt = itemView.findViewById(R.id.service_cost_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
