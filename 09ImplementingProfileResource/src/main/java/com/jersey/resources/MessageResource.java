package com.jersey.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jersey.model.Message;
import com.jersey.services.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource 
{
	
	MessageService messageServices = new MessageService();

	@GET
	public List<Message> getMessages ()
	{
		return messageServices.getMessages();
	}
			
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId) 
	{
		return messageServices.getMessage(messageId);
	}
	
	
	@POST
	public Message addMessage (Message message)
	{
		return messageServices.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId,Message message) 
	{
		message.setId(messageId);
		return messageServices.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId) 
	{
		messageServices.removeMessage(messageId);
	}
	
	
}
