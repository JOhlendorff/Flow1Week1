/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Employee;

/**
 *
 * @author jenso
 */
public class EmployeeDTO {
    private String name;
    private String address;
    private int salary;

    public EmployeeDTO(String name, String address, int salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public EmployeeDTO(Employee employee) {
        this.name = employee.getName();
        this.address = employee.getAddress();
        this.salary = employee.getSalary();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
}
