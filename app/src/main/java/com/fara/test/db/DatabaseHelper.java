package com.fara.test.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.fara.test.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final Context context;

    //Create db properties
    private static final String DATABASE_NAME = "Employee.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "bws_coworkers";

    //Create columns
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_GENDER = "gender";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_GENDER + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    //Upgrade table
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //Insert data to db
    public void insertData(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, employee.getName());
        contentValues.put(COLUMN_AGE, employee.getAge());
        contentValues.put(COLUMN_PHONE, employee.getPhone());
        contentValues.put(COLUMN_GENDER, employee.getGender());
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Inserted", Toast.LENGTH_SHORT).show();
        }
    }

    //Get all data from db
    public List<Employee> getAllData() {
        List<Employee> employees = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
                String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                String gender = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER));
                Employee employee = new Employee(name, age, phone, gender);
                employees.add(employee);
            } while (cursor.moveToNext());
        }
        return employees;
    }
}
