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
		Client client = ClientBuilder.newClient();
		 WebTarget baseTarget = client.target("http://localhost:8080/27SomeBestPractices/webapi/");
		 WebTarget messageTarget = baseTarget.path("messages");
		 WebTarget singlemMessageTarget = messageTarget.path("{messageId}");
		 
		Message message1 = singlemMessageTarget.resolveTemplate("messageId", "1")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		
		Message message2 = singlemMessageTarget.resolveTemplate("messageId", "2")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);

		System.out.println("message1 "+message1.getMessage());
		System.out.println("message2 "+message2.getMessage());
		
		
	}
}
