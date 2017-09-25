package se.tullverket.userprojects.cmtest.helloworld;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Path("/helloworld")
public class HelloWorldRest {
    
    @PersistenceContext(unitName = "helloworld-persistence-unit")
    private EntityManager em;

    @Inject
    private HelloWorld helloWorld;   

    @GET    
    @Produces("application/json")
    public Response greet(@QueryParam("name") String name) {        

		String greeting = helloWorld.getGreeting(name);
        return Response.ok(greeting).build();
    }
	
    @GET    
    @Produces("application/json")
    public Response greetWithUpperCase(@QueryParam("name") String name) {        

		String greeting = helloWorld.getGreeting(name);
        return Response.ok(greeting).build();
    }	
	
    @GET    
    @Produces("application/json")
    public Response greetWithLowerCase(@QueryParam("name") String name) {        

		String greeting = helloWorld.getGreeting(name);
        return Response.ok(greeting).build();
    }
    
    @GET   
    @Path("/load")
    @Produces("application/json")
    public Response loadGreeting(@QueryParam("phrase") String phrase) {        
        HelloWorldEntity entity = new HelloWorldEntity();
        entity.setPhrase(phrase);
        em.persist(entity);         
        return Response.ok().build();
    }

    @GET   
    @Path("/getgreeting")
    @Produces("application/json")
    public Response getGreeting() {
        TypedQuery<HelloWorldEntity> query = em.createQuery("SELECT h FROM HelloWorldEntity h", HelloWorldEntity.class);
        
        String greetingPhrase = null;
        HelloWorldEntity helloWorldEntity;
        try {
            helloWorldEntity = query.getSingleResult();
            greetingPhrase = helloWorldEntity.getPhrase();
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return Response.ok(greetingPhrase).build();
    }    

}