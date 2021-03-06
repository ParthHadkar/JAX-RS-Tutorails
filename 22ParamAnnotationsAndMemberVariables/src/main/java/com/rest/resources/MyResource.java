package com.rest.resources;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("{pathParam}/myresource")
//@Singleton
public class MyResource 
{
	
	@PathParam("pathParam") private String pathParam; // member variable not work for singleton class 
	@QueryParam("query") private String queryParam;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getResource() 
	{
        return "MyResource Working!! the pathParam is "+pathParam+" and  the queryParam is "+queryParam;
    }
}
