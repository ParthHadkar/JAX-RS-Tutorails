package com.jersey.database;

import java.util.HashMap;
import java.util.Map;

import com.jersey.model.Message;
import com.jersey.model.Profile;

public class DatabaseStub 
{
	
	private static Map<Long, Message> messages = new HashMap<Long, Message>(); 
	private static Map<String, Profile> profiles = new HashMap<String, Profile>(); 
	
	public static Map<Long, Message> getMessages()
	{
		return messages;
	}
	
	public static Map<String, Profile> getProfiles()
	{
		return profiles;
	}
	
}
