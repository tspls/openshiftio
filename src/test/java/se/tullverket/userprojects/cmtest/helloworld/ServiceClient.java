/*
 * Copyright (C) 2017 Swedish Customs. All rights reserved.
 *
 * This file is part of Swedish Customs Software.
 * For details on use and redistribution please refer to
 * the LICENSE.txt file included with these sources.
 */
package se.tullverket.userprojects.cmtest.helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * <p>
 * TODO: [Beskrivning] 
 * </p>
 * <p>
 * @author $Author: tspls$
 * </p>
 */
@Path("/helloworld")
public interface ServiceClient {

    @GET    
    @Produces("application/json")
    public String greet(@QueryParam("name") String name);
    
    @GET
    @Path("/load")
    @Produces("application/json")
    public String loadGreeting(@QueryParam("phrase") String phrase);
    
    @GET    
    @Path("/getgreeting")
    @Produces("application/json")
    public String getGreeting();
    
}
