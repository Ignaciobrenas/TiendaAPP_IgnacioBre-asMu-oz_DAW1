/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import main.Logable;

/**
 *
 * @author ignac
 */
public class Employee extends Person implements Logable {

    private int employeeId;
    private String password;
    private static final int EMPLOYEE_ID = 123;
    private static final String PASSWORD = "test";

    public Employee(int employeeId, String password, String name) {
        super(name);
        this.employeeId = employeeId;
        this.password = password;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", password=" + password + '}';
    }

    @Override
    public boolean login(int user, String password) {
        return user == EMPLOYEE_ID && password.equals(PASSWORD);
    }

}
