package com.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
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
		 WebTarget baseTarget = client.target("http://localhost:8080/28MakingAPOSTRequest/webapi/");
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
		
		Message postMessage = new Message(3, "Jersey Post ", "parth");
		Response postResponse = messageTarget.request()
		.post(Entity.json(postMessage));
		int status = postResponse.getStatus();
		
		if(status != 201) 
		{
			System.out.println("Error");
		}
		else 
		{
			Message createdMessage = postResponse.readEntity(Message.class);
			System.out.println("createdMessage "+createdMessage.getMessage());
		}
		
		
		Message updateMessage = new Message(3, "Jersey Put ", "parth");
		Response updateResponse = singlemMessageTarget.resolveTemplate("messageId", "3")
				.request(MediaType.APPLICATION_JSON)
		.put(Entity.json(updateMessage));
		status = updateResponse.getStatus();
		System.out.println("status "+status);
		Message updatedMessage = updateResponse.readEntity(Message.class);
		System.out.println("updateMessage "+updatedMessage.getMessage());
		
		Response deleteResponse = singlemMessageTarget.resolveTemplate("messageId", "3")
				.request()
		.delete();
		status = deleteResponse.getStatus();
		System.out.println("status "+status);
		System.out.println("deleteResponse : deleted successfully");
	}
}
