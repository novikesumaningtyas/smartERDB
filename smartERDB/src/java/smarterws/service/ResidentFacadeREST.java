/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarterws.service;

import entities.test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import smarterws.Resident;

/**
 *
 * @author USER
 */
@Stateless
@Path("smarterws.resident")
public class ResidentFacadeREST extends AbstractFacade<Resident> {

    @PersistenceContext(unitName = "smartERDBPU")
    private EntityManager em;

    public ResidentFacadeREST() {
        super(Resident.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Resident entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Resident entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Resident find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    /////////////////////////ASSIGNMENT/////////////////////////////////
    
    //New GET method for Task 3
    
    //Task 3.1
    //Resident ID
    @GET
    @Path("findByResid/{resid}")
    @Produces({"application/json"})
    public List<Resident> findByResid(@PathParam("resid") Integer resid) 
    {
        Query query = em.createNamedQuery("Resident.findByResid");
        query.setParameter("resid", resid);
        return query.getResultList();
    }
    
    //First Name
    @GET
    @Path("findByFname/{fname}")
    @Produces({"application/json"})
    public List<Resident> findByFname(@PathParam("fname") String fname) 
    {
        Query query = em.createNamedQuery("Resident.findByFname");
        String firstName = fname.toLowerCase();
        
        //capitaling first letter to match in database
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        
        query.setParameter("fname", firstName);
        return query.getResultList();
    }
    
    //Last Name
    @GET
    @Path("findBySname/{sname}")
    @Produces({"application/json"})
    public List<Resident> findBySname(@PathParam("sname") String sname) 
    {
        Query query = em.createNamedQuery("Resident.findBySname");
        String lastName = sname.toLowerCase();
        
        //capitaling first letter to match in database
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        
        query.setParameter("sname", lastName);
        return query.getResultList();
    }
    
    //Address
    @GET
    @Path("findByAddress/{address}")
    @Produces({"application/json"})
    public List<Resident> findByAddress(@PathParam("address") String address) 
    {
        Query query = em.createNamedQuery("Resident.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }
    
    //Postcode
    @GET
    @Path("findByPostcode/{postcode}")
    @Produces({"application/json"})
    public List<Resident> findByPostcode(@PathParam("postcode") Integer postcode) 
    {
        Query query = em.createNamedQuery("Resident.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
    
    //Email
    @GET
    @Path("findByEmail/{email}")
    @Produces({"application/json"})
    public List<Resident> findByEmail(@PathParam("email") String email) 
    {
        Query query = em.createNamedQuery("Resident.findByEmail");
        String emailAdd = email.toLowerCase();
        
        query.setParameter("email", emailAdd);
        return query.getResultList();
    }
    
    //Mobile
    @GET
    @Path("findByMobile/{mobile}")
    @Produces({"application/json"})
    public List<Resident> findByMobile(@PathParam("mobile") Integer mobile) 
    {
        Query query = em.createNamedQuery("Resident.findByMobile");
        query.setParameter("mobile", mobile);
        return query.getResultList();
    }
    
    //Number of residents inside house
    @GET
    @Path("findByNumofresidents/{numofresidents}")
    @Produces({"application/json"})
    public List<Resident> findByNumofresidents(@PathParam("numofresidents") Integer numofresidents) 
    {
        Query query = em.createNamedQuery("Resident.findByNumofresidents");
        query.setParameter("numofresidents", numofresidents);
        return query.getResultList();
    }
    
    //Energy Provider 
    @GET
    @Path("findByEnergyprovider/{energyprovider}")
    @Produces({"application/json"})
    public List<Resident> findByEnergyprovider(@PathParam("energyprovider") String energyprovider) 
    {
        Query query = em.createNamedQuery("Resident.findByEnergyprovider");
        query.setParameter("energyprovider", energyprovider);
        return query.getResultList();
    }
    
    //Date Of Birth
    @GET
    @Path("findByDob/{dob}")
        @Produces({"application/json"})
        public List<Resident> findByDob(@PathParam("dob") String dob) throws ParseException 
        {
            Query query = em.createNamedQuery("Resident.findByDob");
            SimpleDateFormat dateParam = new SimpleDateFormat("yyyy-MM-dd");
            Date dateBirth = dateParam.parse(dob);
            
            query.setParameter("dob", dateBirth);
            return query.getResultList();
        }
        
    //Task 3.2 
    //Combination using postcode and number of residents
    @GET
    @Path("findByPostcodeANDNumofresidents/{postcode}/{numofresidents}")
       @Produces({"application/json"})
       public List<Resident> findByPostcodeANDNumofresidents (@PathParam("postcode") Integer postcode, 
               @PathParam("numofresidents") Integer numofresidents) 
       {
           TypedQuery<Resident> query = 
                   (TypedQuery<Resident>) em.createQuery("SELECT r FROM Resident r WHERE r.postcode = :postcode AND r.numofresidents = :numofresidents");
           
           query.setParameter("postcode", postcode);
           query.setParameter("numofresidents", numofresidents);
           return query.getResultList();
       }
           
       
        ///////////////////////////////////////////////////////////////
       
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Resident> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Resident> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
