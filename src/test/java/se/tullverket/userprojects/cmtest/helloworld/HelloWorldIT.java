package se.tullverket.userprojects.cmtest.helloworld;

import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HelloWorldIT {
    
    @BeforeClass
    public static void loadData() {
        
        final String path = "http://localhost:8081/rest";

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
        ServiceClient proxy = target.proxy(ServiceClient.class);

        proxy.loadGreeting("Ha en bra dag,");                
    }

    @Test
    public void testGetGreeting() {

        final String path = "http://localhost:8081/rest";

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
        ServiceClient proxy = target.proxy(ServiceClient.class);

        String greeting = proxy.greet("Per");

        Assert.assertEquals(greeting, "Ha en bra dag, Per");

    }
    
    @Test
    public void testGetGreetingPhrase() {

        final String path = "http://localhost:8081/rest";

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
        ServiceClient proxy = target.proxy(ServiceClient.class);

        String greeting = proxy.getGreeting();

        Assert.assertEquals(greeting, "Ha en bra dag,");

    }    

}