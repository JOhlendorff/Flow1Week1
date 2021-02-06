/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.EmployeeDTO;
import entities.Employee;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade fe = EmployeeFacade.getEmployeeFacade(emf);
        fe.createEmployee(new EmployeeDTO(new Employee("Lars", "Nybro 3", 123123)));
        fe.createEmployee(new EmployeeDTO(new Employee("Sars", "Nybro 1", 12323)));
        fe.createEmployee(new EmployeeDTO(new Employee("Tars", "Nybro 2", 1123)));
        
    }
    
    public static void main(String[] args) {
        populate();
    }
}
