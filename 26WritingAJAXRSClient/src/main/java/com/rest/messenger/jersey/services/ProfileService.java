package com.rest.messenger.jersey.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import  com.rest.messenger.jersey.database.DatabaseStub;
import  com.rest.messenger.jersey.model.Message;
import  com.rest.messenger.jersey.model.Profile;

public class ProfileService 
{
	private static Map<String, Profile> profiles = DatabaseStub.getProfiles();
	
	public ProfileService()
	{
		profiles.put("parth", new Profile(1L, "parth", "parth", "hadkar"));
		profiles.put("omkar", new Profile(2L, "omkar", "omkar", "pednekar"));
	}
	
	public List<Profile> getProfiles()
	{
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName)
	{
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile)
	{
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) 
	{
		if(profile.getProfileName().isEmpty()) 
		{
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) 
	{
		return profiles.remove(profileName);
	}
}
