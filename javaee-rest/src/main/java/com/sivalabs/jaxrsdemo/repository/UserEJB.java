package com.sivalabs.jaxrsdemo.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivalabs.jaxrsdemo.domain.User;

/**
 * @author Siva
 *
 */
@Stateless
public class UserEJB
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(User user)
	{
		em.persist(user);
	}

	public List<User> selectAll()
	{
		return em.createQuery("select u from User u", User.class).getResultList();
	}

	public List<User> selectAll(String sortBy, String orderBy)
	{
		return em.createQuery("select u from User u ORDER BY u."+sortBy+" "+orderBy, User.class).getResultList();
	}
	
	public User selectById(Integer id)
	{
		logger.debug("selectById : " + id);
		TypedQuery<User> query = em.createQuery("select u from User u where u.id=?1", User.class);
		query.setParameter(1, id);
		List<User> users = query.getResultList();
		if(users.isEmpty()){
			return null;
		}
		return users.get(0);
	}
	
	public void update(User user)
	{
		em.merge(user);
	}
	
	public void delete(User user)
	{
		em.remove(user);
	}
	
	public void delete(Integer userId)
	{
		User user = em.find(User.class, userId);
		if(user != null)
		{
			em.remove(user);
		}
	}
}
