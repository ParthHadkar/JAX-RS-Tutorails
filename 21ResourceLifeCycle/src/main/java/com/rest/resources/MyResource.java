package com.rest.resources;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/myresource")
@Singleton
public class MyResource 
{
	
	private int count;
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getResource() 
	{
		count = count +1;
        return "MyResource Working!! the methods has been call "+count+" time(s)";
    }
}
