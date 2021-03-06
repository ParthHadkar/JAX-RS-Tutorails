package com.rest.filters;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter
{
	private static String AUTHORIZATION_HEADER = "Authorization";
	private static String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static String SECURED_URI_PREFIX = "secured";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException 
	{
		String securedUri = requestContext.getUriInfo().getPath();
		if(securedUri.contains(SECURED_URI_PREFIX)) 
		{
			List<String> list = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
			if(list != null && list.size() > 0) 
			{
				String authToken = list.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String decodedString = Base64.decodeAsString(authToken);
				System.out.println("decodedString "+decodedString);
				StringTokenizer stringTokenizer = new StringTokenizer(decodedString,":");
				String userName = stringTokenizer.nextToken();
				String password = stringTokenizer.nextToken();
				System.out.println("userName "+userName);
				System.out.println("password "+password);
				if(userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin@1"))
				{
					System.out.println("return ");
					return;
				}
			}
			System.out.println("response ");
			Response Unauthorizedresponse = Response.status(Response.Status.UNAUTHORIZED)
					.entity("User Can't access the resource ")
					.build();
				requestContext.abortWith(Unauthorizedresponse);
		}
			
	}

}
