package com.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.messenger.jersey.model.Message;

public class InvocationDemo 
{
	public static void main(String[] args) 
	{
		InvocationDemo invocationDemo = new InvocationDemo();
		Invocation prepareRequestForMessageByYear = invocationDemo.prepareRequestForMessageByYear(2021);
		Response response = prepareRequestForMessageByYear.invoke();
		System.out.println("response status "+response.getStatus());
		System.out.println("response  "+response.readEntity(String.class));
	}
	
	public Invocation prepareRequestForMessageByYear(int year)
	{
		Client client = ClientBuilder.newClient();
		return  client.target("http://localhost:8080/29CreatingInvocations/webapi/")
		                             .path("messages")
		                             .queryParam("year", year)
		                             .request(MediaType.APPLICATION_JSON)
		                             .buildGet();
		 
	}
}
