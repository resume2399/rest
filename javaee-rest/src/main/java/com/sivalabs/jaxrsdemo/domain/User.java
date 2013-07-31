package com.sivalabs.jaxrsdemo.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Siva
 *
 */
@XmlRootElement
public class User
{
	private Integer id;
	private String name;
	private String email;
	private Date dob;
	
	public User()
	{
	}
	
	public User(Integer id, String name, String email, Date dob)
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}
	
	@Override
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + ", email=" + email+ ", dob=" + dob + "]";
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}
	
}
