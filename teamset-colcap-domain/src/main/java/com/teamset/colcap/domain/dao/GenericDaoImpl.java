package com.teamset.colcap.domain.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.method.P;


@Transactional(TxType.MANDATORY)
public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> 
{
	
	@Inject
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);
	
	protected final Session getSession()
	{	
		return sessionFactory.getCurrentSession();
	}
	
	private final Class<P> bean;
	
	@SuppressWarnings("unchecked")
	protected GenericDaoImpl()
	{
		bean = (Class<P>) ClassUtil.getTypeArguments(GenericDaoImpl.class, this.getClass()).get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll()
	{
		final Session s = this.getSession();
		final Criteria c = s.createCriteria(bean);
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(final ID id)
	{
		if(id==null)return null;
		return (T) getSession().get(bean, id);
	}
	
	@Override
	public Serializable add(final T entity)
	{
		return getSession().save(entity);
	}
	
	@Override
	public void delete(final T entity)
	{
		getSession().delete(entity);
		getSession().flush();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void delete(final ID ... id)
	{
		final Session s = this.getSession();
		for(final ID p : id)
		{
			s.delete(s.load(bean, p));
		}
		s.flush();
	}
	
	@Override
	public void update(final T entity)
	{
		getSession().update(entity);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public T getOne()
	{
		final Session session = getSession();
		final Criteria c = session.createCriteria(bean);
		c.setMaxResults(1);
		return (T) c.uniqueResult();
	}
	
	protected int getTotalRows(final Criteria criteria)
	{
		criteria.setProjection(Projections.projectionList().add(Projections.rowCount()));
		return ((Long)criteria.uniqueResult()).intValue();
	}
	
	protected int getTotalRows()
	{
		final Session s = this.getSession();
		return getTotalRows(s.createCriteria(bean));
	}
	

	@Override
	public void evict(final T entity){
		getSession().evict(entity);
	}
	

}