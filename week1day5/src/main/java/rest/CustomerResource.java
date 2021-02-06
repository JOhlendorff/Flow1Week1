package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.CustomerFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("customers")
public class CustomerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final CustomerFacade FACADE =  CustomerFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
//    @Path("count")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
////    public String getRenameMeCount() {
////        long count = FACADE.getRename();
////        //System.out.println("--------------->"+count);
////        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
//    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeByID(@PathParam("id") int id){
        EntityManager em = EMF.createEntityManager();
        try {
            return new Gson().toJson(FACADE.getCustomerById(id)); 
        } finally {
            em.close();
        }
    }
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeAll(){
        EntityManager em = EMF.createEntityManager();
        try {
            return new Gson().toJson(FACADE.getAllBankCustomers());
        } finally {
            em.close();
        }
    }
}
