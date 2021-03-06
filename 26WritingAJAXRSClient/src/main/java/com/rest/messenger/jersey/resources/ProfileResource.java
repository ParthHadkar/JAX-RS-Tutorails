package  com.rest.messenger.jersey.resources;

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

import  com.rest.messenger.jersey.model.Profile;
import  com.rest.messenger.jersey.services.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource 
{
	private ProfileService profileServices = new ProfileService();
	
	@GET
	public List<Profile> getProfiles()
	{
		return profileServices.getProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) 
	{
		return profileServices.getProfile(profileName);
	}
	
	@POST
	public Profile addProfile(Profile profile) 
	{
		return profileServices.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName,Profile profile) 
	{
		profile.setProfileName(profileName);
		return profileServices.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) 
	{
		profileServices.removeProfile(profileName);
	}
}
