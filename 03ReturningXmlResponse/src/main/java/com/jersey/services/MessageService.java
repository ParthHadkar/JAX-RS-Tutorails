package com.jersey.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jersey.model.Message;

public class MessageService 
{
	public List<Message> getMessages() 
	{
		Message m1 = new Message(1L, "Hello World!", new Date(System.currentTimeMillis()), "parth");
		Message m2 = new Message(2L, "Hello Jersey!", new Date(System.currentTimeMillis()), "parth");
		//Message m1 = new Message(1L, "Hello World!", "parth");
		//Message m2 = new Message(2L, "Hello Jersey!", "parth");
		List<Message> messages = new ArrayList<Message>();
		messages.add(m1);
		messages.add(m2);
		return messages;
	}
	
}
