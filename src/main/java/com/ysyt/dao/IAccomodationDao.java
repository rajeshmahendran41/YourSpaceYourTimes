package com.ysyt.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.ysyt.bean.AttributesMaster;
import com.ysyt.to.request.AmenitiesMasterRequest;


public interface IAccomodationDao {

	List<AttributesMaster> getAmenitiesParent(AmenitiesMasterRequest request,
			SessionFactory sessionFactory);


	AttributesMaster getAttributeMasterById(Long id,
			SessionFactory sessionFactory);


	AttributesMaster createOrUpdateAttributeMaster(
			AttributesMaster oldAttributeBean, SessionFactory sessionFactory);

	
}
