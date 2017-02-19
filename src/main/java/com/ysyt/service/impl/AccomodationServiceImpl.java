
package com.ysyt.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Util.Util;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.bean.UserBean;
import com.ysyt.dao.IAccomodationDao;
import com.ysyt.service.IAccomodationService;
import com.ysyt.to.request.AmenitiesMasterRequest;

@Service
@Transactional
public class AccomodationServiceImpl implements IAccomodationService {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
    private HttpServletRequest httpRequest;
	
	@Autowired
	private IAccomodationDao iAccomodationDao;

	@Override
	public List<AttributesMaster> getAmenitiesParent(
			AmenitiesMasterRequest request) {
		
		
		
		return iAccomodationDao.getAmenitiesParent(request,sessionFactory);
	}

	
	@Override
	public AttributesMaster createAttributes(AttributesMaster oldAttributeBean) {
		
		AttributesMaster currentAttributeBean = getAttributeMasterById(oldAttributeBean.getId());
	
		if(!Util.isNull(currentAttributeBean)){			
			oldAttributeBean = updateAttributeDetails(currentAttributeBean,oldAttributeBean);
			oldAttributeBean = iAccomodationDao.createOrUpdateAttributeMaster(oldAttributeBean,sessionFactory);

		}else{
			Util.throwPrimeException("Attribute Id doesnt Exist");
		}
		
		
		return oldAttributeBean;
	}
	
	
	@Override
	public AttributesMaster getAttributeMasterById(Long id) {
		
		return iAccomodationDao.getAttributeMasterById(id,sessionFactory);

	}

	private AttributesMaster updateAttributeDetails(AttributesMaster oldAttributesMaster,
			AttributesMaster currentAttributesMaster) {
		
		if(!Util.isNull(currentAttributesMaster.getTitle())){
			oldAttributesMaster.setTitle(currentAttributesMaster.getTitle());
		}
		
		if(!Util.isNull(currentAttributesMaster.getDescription())){
			oldAttributesMaster.setDescription(currentAttributesMaster.getDescription());
		}
		
		if(!Util.isNull(currentAttributesMaster.getIsAmenities())){
			oldAttributesMaster.setIsAmenities(currentAttributesMaster.getIsAmenities());
		}
		
		if(!Util.isNull(currentAttributesMaster.getIsDeleted())){
			oldAttributesMaster.setIsDeleted(currentAttributesMaster.getIsDeleted());
		}
		
		if(!Util.isNull(currentAttributesMaster.getParentId())){
			oldAttributesMaster.setParentId(currentAttributesMaster.getParentId());
		}
			
		
		return oldAttributesMaster;
	}
	
	

}
