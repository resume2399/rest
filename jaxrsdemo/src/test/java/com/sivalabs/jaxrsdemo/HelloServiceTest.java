package com.sivalabs.jaxrsdemo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;



public class HelloServiceTest 
{
	public static void main(String[] args)
	{
		String uri = "http://localhost:8080/jaxrsdemo/rest";
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri).path("hello/siva1");
		String response = target.request("text/plain").get(String.class);
		System.out.println(response);
	}
}
