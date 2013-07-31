package com.sivalabs.jaxrsdemo.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivalabs.jaxrsdemo.domain.User;
import com.sivalabs.jaxrsdemo.model.UsersResponse;
import com.sivalabs.jaxrsdemo.repository.UserEJB;

/**
 * @author Siva
 *
 */
@Path("/users")
//@Stateless
@Singleton
public class UserResource
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@EJB
	private UserEJB userEJB;
	
	@GET
	@Path("/{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") Integer id)
	{
		return userEJB.selectById(id);		
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public UsersResponse getAllUsers(@QueryParam("sort") @DefaultValue("name") String sortBy, 
									 @QueryParam("order")  @DefaultValue("ASC") String orderBy)
	{
		List<User> users = userEJB.selectAll(sortBy, orderBy);
		UsersResponse response = new UsersResponse();
		response.setUsers(users);
		return response;
	}
	
	@Path("/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser(User user)
	{
		logger.info("Creating User : {}", user);
		userEJB.insert(user);
    	return user;
	}
	
	@Path("/")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(User user)
	{
		logger.info("Updating User : {}", user);
		userEJB.update(user);
    	return Response.ok().entity(user).build();
	}
	
	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") Integer id, @HeaderParam("AUTH_TOKEN") String authToken)
	{
		if(!"SIVA".equalsIgnoreCase(authToken)){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		logger.info("Deleting UserID : {}", id);
		userEJB.delete(id);
    	return Response.ok().build();
	}
	
	
	@Path("/contact")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_HTML)
	public Response contact(@Context UriInfo uriInfo, MultivaluedMap<String, String> formParams)
	{
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
	    MultivaluedMap<String, String> pathParams = uriInfo.getPathParameters();
	    
	    logger.info("form : {}", formParams);
		logger.info("queryParams : {}", queryParams);
		logger.info("pathParams : {}", pathParams);
		String html = "<html><body>Thank You</body></html>";
    	return Response.ok().entity(html).build();
	}
}
