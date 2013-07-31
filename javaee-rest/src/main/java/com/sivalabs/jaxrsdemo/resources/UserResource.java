package com.sivalabs.jaxrsdemo.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivalabs.jaxrsdemo.domain.User;
import com.sivalabs.jaxrsdemo.model.UsersResponse;
import com.sivalabs.jaxrsdemo.repository.UserRepository;

/**
 * @author Siva
 *
 */
@Path("/users")
public class UserResource
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") Integer id)
	{
		/*User user = new User();
		user.setId(id);
		user.setName("User"+id);
		user.setEmail("user"+id+"@gmail.com");
		user.setDob(new Date());		
		return user;*/
		return UserRepository.selectById(id);		
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public UsersResponse getAllUsers()
	{
		/*User user = new User();
		user.setId(id);
		user.setName("User"+id);
		user.setEmail("user"+id+"@gmail.com");
		user.setDob(new Date());		
		return user;*/
		List<User> users = UserRepository.selectAll();
		UsersResponse response = new UsersResponse();
		response.setUsers(users);
		return response;
	}
	
	@Path("/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser(User user){
		logger.debug("Creating User : {}", user);
		UserRepository.insert(user);
    	return user;
	}
	
}
