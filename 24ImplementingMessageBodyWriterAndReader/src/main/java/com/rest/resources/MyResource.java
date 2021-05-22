package com.rest.resources;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.rest.model.MyDate;

@Path("myresource")
public class MyResource 
{
		
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Date getDate() 
	{
		return Calendar.getInstance().getTime();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	public String getDate(MyDate myDate) 
	{
		return "Result is "+myDate.toString();
	}
}
