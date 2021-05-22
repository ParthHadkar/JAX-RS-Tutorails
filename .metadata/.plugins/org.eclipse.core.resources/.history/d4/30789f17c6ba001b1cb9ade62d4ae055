package com.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import  com.rest.messenger.jersey.model.Message;

public class RestApiClient 
{
	
	public static void main(String[] args) 
	{
		// one way of getting the data
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/26WritingAJAXRSClient/webapi/messages/1")
				.request()
				.get();
		Message message = response.readEntity(Message.class);
		System.out.println("message1 "+message.getMessage());
		
		// another way of getting the data
		 WebTarget target = client.target("http://localhost:8080/26WritingAJAXRSClient/webapi/messages/1");
		Builder builder = target.request();
		response = builder.get();
		message = response.readEntity(Message.class);
		System.out.println("message2 "+message.getMessage());
		
		// one another way of getting the data
		message = client.target("http://localhost:8080/26WritingAJAXRSClient/webapi/messages/1")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		System.out.println("message2 "+message.getMessage());
		
		// For Debugging purpose it return the actual payload that get out of http
		String msgStr = client.target("http://localhost:8080/26WritingAJAXRSClient/webapi/messages/1")
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		System.out.println("message3 "+msgStr);
	}
}
