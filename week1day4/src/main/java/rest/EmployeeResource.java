package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import entities.Employee;
import facades.EmployeeFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("employee")
public class EmployeeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final EmployeeFacade FACADE =  EmployeeFacade.getEmployeeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public EmployeeResource() {
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    /**
     * Retrieves representation of an instance of rest.EmployeeResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Path("employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeByID(@PathParam("id") Long id){
        EntityManager em = EMF.createEntityManager();
        try {
            return new Gson().toJson(FACADE.getEmployeeByID(id)); 
        } finally {
            em.close();
        }
    }
    /**
     *
     * @param name
     * @return
     */
    @GET
    @Path("employee/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeByID(@PathParam("name") String name){
        EntityManager em = EMF.createEntityManager();
        try {
            return new Gson().toJson(FACADE.getEmployeesByName(name)); 
        } finally {
            em.close();
        }
    }
    @GET
    @Path("employee/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeAll(){
        EntityManager em = EMF.createEntityManager();
        try {
            return new Gson().toJson(FACADE.getAllEmployees());
        } finally {
            em.close();
        }
    }
    @GET
    @Path("employee/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPaid(){
        EntityManager em = EMF.createEntityManager();
        try{
            return new Gson().toJson(FACADE.getEmployeesByHighestSalary());
        }finally{
            em.close();
        }
    }
}
