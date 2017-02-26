package com.ysyt.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Util.Util;
import com.ysyt.bean.Accomodations;
import com.ysyt.bean.AccomodationsDetails;
import com.ysyt.bean.AmenitiesMapping;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.bean.LocationBean;
import com.ysyt.dao.IAccomodationDao;
import com.ysyt.to.request.AccomodationListRequest;
import com.ysyt.to.request.AmenitiesMasterRequest;
import com.ysyt.to.request.LocationRequest;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<AmenitiesMapping> getAmenitiesMappingList(Long typeId,String sourceName,
			SessionFactory sessionFactory) {

		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(AmenitiesMapping.class)
				.add(Restrictions.eq("isDeleted",false))
				.add(Restrictions.eq("sourceId",typeId))
				.add(Restrictions.eq("sourceType", sourceName));
				
				
				return  criteria.list();
	}

	@Override
	public Accomodations getAccomodatoinById(Long id,
			SessionFactory sessionFactory) {

		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Accomodations.class)
				.add(Restrictions.eq("isDeleted",false))
				.add(Restrictions.eq("id",id));
				
				
				return (Accomodations) criteria.uniqueResult();
		
	}

	@Override
	public Accomodations createOrUpdateAccomodation(
			Accomodations oldAccomodation, SessionFactory sessionFactory) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(oldAccomodation);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		
		return oldAccomodation;
	}

	@Override
	public AccomodationsDetails getAccomodationDetailsById(Long id,
			SessionFactory sessionFactory) {
		
		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(AccomodationsDetails.class)
				.add(Restrictions.eq("isDeleted",false))
				.add(Restrictions.eq("id",id));
				
				
				return (AccomodationsDetails) criteria.uniqueResult();
		
	}

	@Override
	public void createOrUpdateAccomodationDetails(
			AccomodationsDetails acccomodationDetails,
			SessionFactory sessionFactory) {

		sessionFactory.getCurrentSession().saveOrUpdate(acccomodationDetails);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationBean> getLocationDetails(LocationRequest request,
			SessionFactory sessionFactory) {
		
		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(LocationBean.class);
		
		if(!Util.isNull(request.getCountry())){
			criteria.add(Restrictions.eq("country", request.getCountry().toLowerCase()).ignoreCase());
		}
		if(!Util.isNull(request.getState())){
			criteria.add(Restrictions.eq("state", request.getState()).ignoreCase());
		}
		if(!Util.isNull(request.getCountry())){
			criteria.add(Restrictions.eq("city", request.getCity().toLowerCase()).ignoreCase());
		}
		if(!Util.isNull(request.getCountry())){
			criteria.add(Restrictions.eq("location", request.getLocation().toLowerCase()).ignoreCase());
		}
		
		if(!Util.isNull(request.getProjection())){
		
			criteria.setProjection(Projections.distinct(Projections.property(request.getProjection())));
		}
		
		if(!Util.isNull(request.getLimit())){
			criteria.setMaxResults(request.getLimit());
		}
		
		if(!Util.isNull(request.getOffset())){
			criteria.setFirstResult(request.getOffset());
		}
		
		return criteria.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAccomodationList(
			AccomodationListRequest request, SessionFactory sessionFactory) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		List<Accomodations> accomdationList = new ArrayList<>();
		List<Long> accomodationIds = new ArrayList<>();
		Long count = 0L;
		
		
		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Accomodations.class);
		
		Criteria criteriaCount = sessionFactory.getCurrentSession().createCriteria(Accomodations.class);;
		
		if(!Util.isNull(request.getLimit())){
			criteria.setMaxResults(request.getLimit());
		}
		
		if(!Util.isNull(request.getOffset())){
			criteria.setFirstResult(request.getOffset());
		}
		
		criteriaCount.setProjection(Projections.distinct(Projections.count("id")));
		count = (Long) criteriaCount.uniqueResult();

		if(!Util.isNull(count)&&count>0){
			accomodationIds = criteria.setProjection(Projections.distinct(Projections.property("id"))).list();
			if(!Util.isNullList(accomodationIds)){
				
				for(Long accomodationId : accomodationIds){
					Accomodations accomodation = new Accomodations();
					accomodation = getAccomodatoinById(accomodationId,sessionFactory);
					accomdationList.add(accomodation);
				}
				response.put("list", accomdationList);
			}
		}
;
		
		response.put("count", count);
		
		return response;
		
	}

	

	

	

}
