package facades;

import dtos.BankCustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public BankCustomer addCustomer(BankCustomer cust){
        BankCustomer bc = new BankCustomer(cust);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bc);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return bc;
    }
    
    public List<BankCustomerDTO> getCustomerByName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<BankCustomer> query = em.createQuery("SELECT b FROM BankCustomer b where b.firstName = :name", BankCustomer.class);
            query.setParameter("name", name);
            List<BankCustomerDTO> l = new ArrayList();
            List<BankCustomer> bl= query.getResultList();
            for (BankCustomer bc : bl) {
                l.add(new BankCustomerDTO(bc));
            }
            return l;
        }
        finally{
            em.close();
        }
        
    }
    
    public BankCustomerDTO getCustomerById(int id){
        EntityManager em = emf.createEntityManager();
        return new BankCustomerDTO(em.find(BankCustomer.class, id));
    }
    
//    //TODO Remove/Change this before use
//    public long getRenameMeCount(){
//        EntityManager em = emf.createEntityManager();
//        try{
//            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
//            return renameMeCount;
//        }finally{  
//            em.close();
//        }
//        
//    }
    
    public List<BankCustomer> getAllBankCustomers(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<BankCustomer> query = em.createQuery("SELECT b FROM BankCustomer b", BankCustomer.class);
        List<BankCustomer> bankCustomers = query.getResultList();
        return bankCustomers;
    }
    
//    public static void main(String[] args) {
//        emf = EMF_Creator.createEntityManagerFactory();
//        CustomerFacade fe = getFacadeExample(emf);
//        fe.getAllBankCustomers().forEach(dto->System.out.println(dto));
//    }

}
