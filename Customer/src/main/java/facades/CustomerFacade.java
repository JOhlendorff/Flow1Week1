/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author jenso
 */
public class CustomerFacade {
    private static CustomerFacade instance;
    private static EntityManagerFactory emf;
    private CustomerFacade(){}
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public static void main(String[] args) {
        CustomerFacade cf = getEmployeeFacade(Persistence.createEntityManagerFactory("pu"));
        Customer cust = cf.addCustomer("Hans", "Stær");
        System.out.println(cf.findByID(1));
        System.out.println(cf.allCustomers());
        System.out.println(cf.findByLastName("Stær"));
        System.out.println(cf.getNumberOfCustomers());
    }
    
    private EntityManager getEntityManager(){ 
     return emf.createEntityManager();
    }
    
    public Customer findByID(int id){
        Long l = new Long(id);
        EntityManager em = emf.createEntityManager();
        try{
            Customer c = em.find(Customer.class, l);
        
        return c;
        }
        finally{
            em.close();
        }
    }
    public List<Customer> findByLastName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery q = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :lastName", Customer.class);
            q.setParameter("lastName", name);
            return q.getResultList();
        }
        finally{
            em.close();
        }
        
    }
    public int getNumberOfCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c ", Customer.class);
            return query.getResultList().size();
        }
        finally{
            em.close();
        }
        
    }
    public List<Customer> allCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c ", Customer.class);
            return query.getResultList();
        }
        finally{
            em.close();
        }
        
    }
    public Customer addCustomer(String fName, String lName){
        EntityManager em = emf.createEntityManager();
        Customer cust = new Customer(fName, lName);
        try{
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        }
        finally{
            em.close();
        }
    }
}
