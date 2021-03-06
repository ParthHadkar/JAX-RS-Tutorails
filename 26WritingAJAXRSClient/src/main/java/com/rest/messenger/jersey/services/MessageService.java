package  com.rest.messenger.jersey.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import  com.rest.messenger.jersey.database.DatabaseStub;
import  com.rest.messenger.jersey.model.Message;

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
	
	public List<Message> getMessagesByYear(int year) 
	{
		ArrayList<Message> messagesForYear = new ArrayList<Message>();
		Calendar calendar = Calendar.getInstance();
		for(Message message:messages.values()) 
		{
			calendar.setTime(message.getCreated());
			if(calendar.get(Calendar.YEAR) == year) 
			{
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getMessagesByPagination(int start,int size) 
	{
		ArrayList<Message> messagesByPagination = new ArrayList<Message>(messages.values());
		if(start + size > messagesByPagination.size())
		{
			return new ArrayList<Message>();
		}
		return messagesByPagination.subList(start, start + size);
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
