/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarterws.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import smarterws.Credential;

/**
 *
 * @author USER
 */
@Stateless
@Path("smarterws.credential")
public class CredentialFacadeREST extends AbstractFacade<Credential> {

    @PersistenceContext(unitName = "smartERDBPU")
    private EntityManager em;

    public CredentialFacadeREST() {
        super(Credential.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credential entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Credential entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Credential find(@PathParam("id") String id) {
        return super.find(id);
    }
    
    /////////////////////////ASSIGNMENT/////////////////////////////////
    
    //Username
    @GET
    @Path("findByUsername/{username}")
        @Produces({"application/json"})
        public List<Credential> findByUsername(@PathParam("username") String username) 
        {
            Query query = em.createNamedQuery("Credential.findByUsername");
            query.setParameter("username", username);
            return query.getResultList();
        }
    
    //Password Hashed   
    @GET
    @Path("findByPassword/{password}")
        @Produces({"application/json"})
        public List<Credential> findByPassword(@PathParam("password") String password) 
        {
            Query query = em.createNamedQuery("Credential.findByPassword");
            query.setParameter("password", password);
            return query.getResultList();
        }

    //Registration date    
    @GET
    @Path("findByRegistrationdate/{registrationdate}")
        @Produces({"application/json"})
        public List<Credential> findByRegistrationdate(@PathParam("registrationdate") String registrationdate) throws ParseException 
        {
            Query query = em.createNamedQuery("Credential.findByRegistrationdate");
            SimpleDateFormat dateParam = new SimpleDateFormat("yyyy-MM-dd");
            Date regDate = dateParam.parse(registrationdate);
            
            query.setParameter("registrationdate", regDate);
            return query.getResultList();
        }
        
    //username and password
    @GET
    @Path("findByUsernameANDPassword/{username}/{password}")
        @Produces({"application/json"})
        public List<Credential> findByUsernameANDPassword(@PathParam("username") String username, 
               @PathParam("password") String password)
        {
            Query query = em.createNamedQuery("Credential.findByUsernameANDPassword");
            query.setParameter("username", username);
            query.setParameter("password", password);
        
            return query.getResultList();
        }
        
    //find firstname from username
    @GET  
    @Path("findByUsernameForFindFirstName/{username}")    
        @Produces(MediaType.APPLICATION_JSON)
        public Object findByUsernameForFindFirstName(@PathParam("username") String username)
        {
            List<Object[]>queryList = em.createQuery("SELECT c.resid.fname, c.registrationdate FROM Credential AS c WHERE c.username = :username",
            Object[].class)
                    .setParameter("username", username).getResultList();
            
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Object[] row : queryList) 
            {
                JsonObject personObject = Json.createObjectBuilder()
                .add("fName",(String)(row[0]) ).build();
                arrayBuilder.add(personObject);
            }

            JsonArray jArray = arrayBuilder.build();
             return jArray;
        }
    
    //////////////////////////////////////////////////////////////////////    
        
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
