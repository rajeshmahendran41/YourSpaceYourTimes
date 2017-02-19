package com.ysyt.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Util.Util;
import com.ysyt.bean.AmenitiesMapping;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.dao.IAccomodationDao;
import com.ysyt.to.request.AmenitiesMasterRequest;

@Repository
public class AccomodationDaoImpl implements IAccomodationDao {
	
	
	@Autowired
	private HttpServletRequest httpRequest;

	@SuppressWarnings("unchecked")
	@Override
	public List<AttributesMaster> getAmenitiesParent(
			AmenitiesMasterRequest request, SessionFactory sessionFactory) {
		
		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(AttributesMaster.class)
		.add(Restrictions.eq("isDeleted",false))
		.add(Restrictions.eq("parentId",request.getAmenitiesParentId()));
		
		if(!Util.isNull(request.getQuery())){
			criteria.add(Restrictions.ilike("title", "%"+request.getQuery()+"%"));
		}
		if(!Util.isNull(request.getLimit())){
			criteria.setMaxResults(request.getLimit());
		}
		if(!Util.isNull(request.getOffset())){
			criteria.setFirstResult(request.getOffset());
		}
		
		return criteria.list();
		
	}

	@Override
	public AttributesMaster getAttributeMasterById(Long id,SessionFactory sessionFactory) {
		
		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(AttributesMaster.class)
				.add(Restrictions.eq("isDeleted",false))
				.add(Restrictions.eq("id",id));
				
				
				return (AttributesMaster) criteria.uniqueResult();
		
	}

	@Override
	public AttributesMaster createOrUpdateAttributeMaster(
			AttributesMaster oldAttributeBean, SessionFactory sessionFactory) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(oldAttributeBean);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		
		return oldAttributeBean;
	}

	@Override
	public AmenitiesMapping getAmenitiesMappingById(Long id,SessionFactory sessionFactory) {
		
		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(AmenitiesMapping.class)
				.add(Restrictions.eq("isDeleted",false))
				.add(Restrictions.eq("id",id));
				
				
				return (AmenitiesMapping) criteria.uniqueResult();
		
	}

	@Override
	public AmenitiesMapping createOrUpdateAmenitiesMapping(
			AmenitiesMapping oldAttributeBean, SessionFactory sessionFactory) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(oldAttributeBean);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		
		return oldAttributeBean;
		
	}

	

	

	

}
