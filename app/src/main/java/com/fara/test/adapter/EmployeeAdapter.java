package com.fara.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fara.test.R;
import com.fara.test.model.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private final Context context;
    private final List<Employee> employees;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View objects from xml
        View view = LayoutInflater.from(context)
                .inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    //Bind data to view
    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.textViewName.setText(employee.getName());
        holder.textViewAge.setText(String.format("%s", employee.getAge()));
        holder.textViewPhone.setText(employee.getPhone());
        holder.textViewGender.setText(employee.getGender());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewName;
        private final TextView textViewAge;
        private final TextView textViewPhone;
        private final TextView textViewGender;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textView_name);
            textViewAge = itemView.findViewById(R.id.textView_age);
            textViewPhone = itemView.findViewById(R.id.textView_phone);
            textViewGender = itemView.findViewById(R.id.textView_gender);
        }
    }
}
