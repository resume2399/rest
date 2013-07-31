package com.sivalabs.jaxrsdemo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sivalabs.jaxrsdemo.domain.User;

/**
 * @author Siva
 *
 */
@XmlRootElement
public class UsersResponse
{
	private List<User> users;
	
	public List<User> getUsers()
	{
		return users;
	}
	public void setUsers(List<User> users)
	{
		this.users = users;
	}
}
