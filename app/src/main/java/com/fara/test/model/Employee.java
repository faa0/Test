package com.fara.test.model;

public class Employee {

    private final String name;
    private final int age;
    private final String phone;
    private final String gender;

    public Employee(String name, int age, String phone, String gender) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }
}
