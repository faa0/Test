package com.fara.test.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.fara.test.R;
import com.fara.test.db.DatabaseHelper;
import com.fara.test.model.Employee;

public class EmployeeActivity extends AppCompatActivity {

    private DatabaseHelper myDb;

    private EditText editTextName, editTextAge, editTextPhone;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        editTextName = findViewById(R.id.editText_name);
        editTextAge = findViewById(R.id.editText_age);
        editTextPhone = findViewById(R.id.editText_phone);

        myDb = new DatabaseHelper(EmployeeActivity.this);

        //Default variable value
        gender = getString(R.string.male);
    }

    //Change the value of the variable "gender"
    public void onClickChooseGender(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();
        if (id == R.id.radioButton_male) {
            gender = getString(R.string.male);
        } else if (id == R.id.radioButton_female) {
            gender = getString(R.string.female);
        }
    }

    public void onClickSaveEmployee(View view) {
        //Сhecking fields for emptiness
        if (!TextUtils.isEmpty(editTextAge.getText().toString().trim()) &&
                !TextUtils.isEmpty(editTextName.getText().toString().trim()) &&
                !TextUtils.isEmpty(editTextPhone.getText().toString().trim())) {

            //Get data from fields
            String name = editTextName.getText().toString().trim();
            int age = Integer.parseInt(editTextAge.getText().toString().trim());
            String phone = editTextPhone.getText().toString().trim();

            //Insert data to db
            Employee employee = new Employee(name, age, phone, gender);
            myDb.insertData(employee);

            //Return to MainActivity
            Intent intent = new Intent(EmployeeActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Incorrect data", Toast.LENGTH_SHORT).show();
        }
    }
}