package com.jersey.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jersey.database.DatabaseStub;
import com.jersey.model.Message;

public class MessageService 
{
	
	private static Map<Long, Message> messages = DatabaseStub.getMessages();
	
	public MessageService() 
	{
		messages.put(1L, new Message(1L,"Hello World!", new Date(System.currentTimeMillis()), "parth"));
		messages.put(2L, new Message(2L,"Hello Jersey!", new Date(System.currentTimeMillis()), "parth"));
	}
	
	
	public List<Message> getMessages() 
	{
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id) 
	{
		return messages.get(id);
	}
	
	public Message addMessage(Message message) 
	{
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) 
	{
		if(message.getId() <= 0) 
		{
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) 
	{
		return messages.remove(id);
	}
	
}
