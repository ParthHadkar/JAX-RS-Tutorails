package com.jersey.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jersey.database.DatabaseStub;
import com.jersey.model.Comment;
import com.jersey.model.Message;

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
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		//messages.get(messageId).setComments(comments);
		return comments.get(commentId);
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
