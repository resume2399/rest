package com.sivalabs.jaxrsdemo.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello")
public class HelloService 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GET
	@Path("/{name}")
	@Produces("text/plain")
    public String sayHello(@PathParam("name") String name)
    {
        logger.debug("Name : {}", name);
    	return "Hello "+name;
    }
	
}
