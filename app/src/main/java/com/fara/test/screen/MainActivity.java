package com.fara.test.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fara.test.R;
import com.fara.test.adapter.EmployeeAdapter;
import com.fara.test.db.DatabaseHelper;
import com.fara.test.model.Employee;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;

    private List<Employee> employees;
    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        myDb = new DatabaseHelper(MainActivity.this);

        //Get data from db
        employees = myDb.getAllData();

        adapter = new EmployeeAdapter(this, employees);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    //Go to EmployeeActivity
    public void onClickAddEmployee(View view) {
        Intent intent = new Intent(MainActivity.this, EmployeeActivity.class);
        startActivity(intent);
    }
}