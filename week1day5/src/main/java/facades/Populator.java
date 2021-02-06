/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BankCustomerDTO;
import entities.BankCustomer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new BankCustomer("Lars", "Larsen", "1", 123123, 1, "Han har mange penge"));
        em.persist(new BankCustomer("Kars", "Larsen", "2", 12312, 2, "Han har færre penge"));
        em.persist(new BankCustomer("Fars", "Larsen", "3", 1231, 1, "Han har færrest penge"));
        em.getTransaction().commit();
        em.close();
        
        
    }
    
    public static void main(String[] args) {
        populate();
    }
}
