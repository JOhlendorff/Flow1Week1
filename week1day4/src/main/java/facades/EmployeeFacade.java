package facades;

import dtos.EmployeeDTO;
import entities.Employee;
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
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Employee createEmployee(EmployeeDTO e){
        Employee employee = new Employee(e.getName(), e.getAddress(), e.getSalary());
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        }finally {
            em.close();
        }
    }
    public List<EmployeeDTO> getAllEmployees(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("SELECT e FROM Employee e",Employee.class);
            List<EmployeeDTO> l = new ArrayList();
            List<Employee> eL= query.getResultList();
            for (Employee employee : eL) {
                l.add(new EmployeeDTO(employee));
            }
            return l;
        }finally {
            em.close();
        }

    }
    public EmployeeDTO getEmployeeByID(Long id){
        EntityManager em = emf.createEntityManager();
            try{
                Employee e = em.find(Employee.class,id);
                return new EmployeeDTO(e);
            }
            finally{
                em.close();

        }
        
    }
    
    public List<EmployeeDTO> getEmployeesByName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("SELECT e FROM Employee e WHERE e.name =:name",Employee.class);
            query.setParameter("name", name);
            List<EmployeeDTO> l = new ArrayList();
            List<Employee> eL= query.getResultList();
            for (Employee employee : eL) {
                l.add(new EmployeeDTO(employee));
            }
            return l;
        }finally {
            em.close();
        }
        
    }
    public List<EmployeeDTO> getEmployeesByHighestSalary(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("SELECT e FROM employee e WHERE e.salary = (SELECT MAX(e.salary) from employee e)",Employee.class);
            List<EmployeeDTO> l = new ArrayList();
            List<Employee> eL= query.getResultList();
            for (Employee employee : eL) {
                l.add(new EmployeeDTO(employee));
            }
            return l;
        }finally {
            em.close();
        }
        
    }

    
}
