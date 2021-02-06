/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jenso
 */
@Path("animals_db")
public class AnimalsFromDB {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalsFromDB
     */
    public AnimalsFromDB() {
    }
    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
        return new Gson().toJson(animals);
        } finally {
          em.close();
        }   
    }
    @GET
    @Path("animalbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByID(@PathParam("id")int id){
        EntityManager em = emf.createEntityManager();
            try{
                TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.id =:id", Animal.class);
                query.setParameter("id", id);
                List<Animal> animals = query.getResultList();
                return new Gson().toJson(animals);
            } finally {
            em.close();
        }
        
    }
    
    @GET
    @Path("animalbytype/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type")String type){
        EntityManager em = emf.createEntityManager();
            try{
                TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type =:type", Animal.class);
                query.setParameter("type", type);
                List<Animal> animals = query.getResultList();
                return new Gson().toJson(animals);
            } finally {
            em.close();
        }
        
    }
    @GET
    @Path("random_animal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAnimal() {
        EntityManager em = emf.createEntityManager();
            try{
//                Random rand = new Random();
//                int animalCount = (int) em.createQuery("SELECT COUNT(a) FROM Animal a").getSingleResult();
//                int id = rand.nextInt(animalCount) + 1;
//                TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.id =:id", Animal.class);
//                query.setParameter("id", id);
                
                TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
                List<Animal> animals = query.getResultList();
                Random rand = new Random();
                Animal a = animals.get(rand.nextInt(animals.size() + 1));
                return new Gson().toJson(a);
            } finally {
            em.close();
        }
    }
}
