package com.sivalabs.javaeerest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Test;


public class HelloServiceTest 
{
	private static final String BASE_URI = "http://localhost:8080/javaee-rest/rest";
	
	@Test
    public void testSayHello() 
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI).path("hello/siva");
		String response = target.request(MediaType.TEXT_PLAIN).get(String.class);
		System.err.println(response);
	}
}
