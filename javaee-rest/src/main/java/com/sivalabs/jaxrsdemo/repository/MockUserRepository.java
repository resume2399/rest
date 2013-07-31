package com.sivalabs.jaxrsdemo.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.sivalabs.jaxrsdemo.domain.User;

/**
 * @author Siva
 *
 */
public class MockUserRepository
{
	private static Map<Integer, User> USERS_TABLE = new HashMap<Integer, User>();
	private static AtomicInteger ID = new AtomicInteger(3);
	private MockUserRepository()
	{
	}
	static
	{
		for (int i = 1; i <= 3; i++)
		{
			User user = new User(i, "User"+i, "user"+i+"@gmail.com","UserP"+i, new Date());
			USERS_TABLE.put(user.getId(), user);
		}
		
	}
	public static User selectById(Integer id)
	{
		return USERS_TABLE.get(id);
	}
	
	public static List<User> selectAll()
	{
		return new ArrayList<User>(USERS_TABLE.values());
	}
	
	public static User insert(User user)
	{
		user.setId(ID.incrementAndGet());
		USERS_TABLE.put(user.getId(), user);
		return user;
	}
	
	public static User update(User user)
	{
		if(USERS_TABLE.containsKey(user.getId())){
			USERS_TABLE.put(user.getId(), user);
			return user;
		}
		return null;
	}
	
	public static User delete(User user)
	{
		if(USERS_TABLE.containsKey(user.getId())){
			USERS_TABLE.remove(user.getId());
			return user;
		}
		return null;
	}
	
	public static Integer delete(Integer userId)
	{
		if(USERS_TABLE.containsKey(userId)){
			USERS_TABLE.remove(userId);
			return userId;
		}
		return -1;
	}
	
}
