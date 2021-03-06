package  com.rest.messenger.jersey.resources;

import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import  com.rest.messenger.jersey.beans.MessageFilterBean;
import  com.rest.messenger.jersey.exception.DataNotFoundException;
import  com.rest.messenger.jersey.model.Message;
import  com.rest.messenger.jersey.services.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource 
{
	
	MessageService messageServices = new MessageService();

	@GET
	public List<Message> getMessages (@BeanParam MessageFilterBean messageFilterBean)
	{
		if(messageFilterBean.getYear() > 0) 
		{
			return messageServices.getMessagesByYear(messageFilterBean.getYear());
		}
		if(messageFilterBean.getStart() > 0 && messageFilterBean.getSize() > 0) 
		{
			return messageServices.getMessagesByPagination(messageFilterBean.getStart(), messageFilterBean.getSize());
		}
		return messageServices.getMessages();
	}
			
	@GET
	@Path("/{messageId}")
	@Produces(value = {MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	public Message getMessage(@PathParam("messageId") long messageId,@Context UriInfo uriInfo) 
	{
		Message message = messageServices.getMessage(messageId);
		message.addLink(getURIWithParam(String.valueOf(messageId), uriInfo, MessageResource.class), "self");
		message.addLink(getURIWithParam(message.getAuthor(), uriInfo, ProfileResource.class), "profile");
		message.addLink(getURIWithSubResource(String.valueOf(messageId),"getCommentResource", uriInfo, MessageResource.class,CommentResource.class), "comments");
		if(message == null) 
		{
			throw new DataNotFoundException("Message with id "+messageId+" not found");
		}
		return message;
	}

	private String getURIWithParam(String param, UriInfo uriInfo, Class msgClass) {
		return uriInfo.getBaseUriBuilder()
				.path(msgClass)
				.path(param)
				.build()
				.toString();
	}	
	
	private String getURIWithSubResource(String param,String methodName, UriInfo uriInfo, Class parentClass, Class subClass) {
		return uriInfo.getBaseUriBuilder()
				.path(parentClass)
				.path(parentClass,methodName)
				.path(subClass)
				.resolveTemplate("messageId", param)
				.build()
				.toString();
	}
	
	@POST
	public Response addMessage (Message message,@Context UriInfo uriInfo)//Message
	{
		//return messageServices.addMessage(message);
		Message newMessage = messageServices.addMessage(message);
		String messageId = String.valueOf(newMessage.getId());
		URI url = uriInfo.getAbsolutePathBuilder().path(messageId).build();
		return Response.created(url)
				.entity(newMessage)
				.build();//.status(Status.CREATED).header("location", url)
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
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource()//@PathParam("messageId") long messageId, @Context ResourceContext resourceContext) 
	{
		return new CommentResource();
		//return resourceContext.initResource(new CommentResource(messageId));
	}
	
}
