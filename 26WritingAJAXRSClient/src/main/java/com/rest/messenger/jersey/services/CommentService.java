package  com.rest.messenger.jersey.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import  com.rest.messenger.jersey.database.DatabaseStub;
import  com.rest.messenger.jersey.model.Comment;
import  com.rest.messenger.jersey.model.ErrorMessage;
import  com.rest.messenger.jersey.model.Message;

public class CommentService 
{
	
	private static Map<Long, Message> messages = DatabaseStub.getMessages();
	
	public CommentService() 
	{
		//Message ms = new Message(1L,"Hello World!", new Date(System.currentTimeMillis()), "parth");
		//Message ms = messages.get(1L);
		 //messages.get(1L).getComments().put(1L, new Comment(1L,"Hello World! Comment", new Date(System.currentTimeMillis()), "parth"));
		//Message ms1 = new Message(2L,"Hello World!", new Date(System.currentTimeMillis()), "parth");
		//Message ms1 = messages.get(2L);
		// messages.get(2L).getComments().put(2L, new Comment(2L,"Hello World! Comment Comment", new Date(System.currentTimeMillis()), "parth"));
		 //messages.get(2L).getComments().put(3L, new Comment(3L,"Hello World! Comment Comment", new Date(System.currentTimeMillis()), "parth"));
		//messages.put(1L, ms);
		//messages.put(2L, ms1);
	}
	
	
	public List<Comment> getComments(long messageId) 
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId,long commentId) 
	{
		ErrorMessage errorMessage = new ErrorMessage("Message with id "+messageId+" not found",404,"Comming soon");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		Message message =  messages.get(messageId);
		if(message == null) 
		{
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if(comment == null) 
		{
			errorMessage.setErrorMessage("Message with id "+messageId+" and comment Id "+commentId+" not found");
			throw new NotFoundException(response);
		}
		//messages.get(messageId).setComments(comments);
		return comment;
	}
	
	public Comment addComment(long messageId,Comment comment) 
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		messages.get(messageId).setComments(comments);
		return comment;
	}
	
	public Comment updateComment(long messageId,Comment comment) 
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0) 
		{
			return null;
		}
		comments.put(comment.getId(), comment);
		messages.get(messageId).setComments(comments);
		return comment;
	}
	
	public Comment removeComment(long messageId,long commentId) 
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.remove(commentId);
		messages.get(messageId).setComments(comments);
		return comment;
	}
	
}
