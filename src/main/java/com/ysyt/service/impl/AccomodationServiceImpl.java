
package com.ysyt.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Util.Util;
import com.ysyt.bean.AmenitiesMapping;
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
		
		if(!Util.isNull(oldAttributeBean.getId())){
			AttributesMaster currentAttributeBean = getAttributeMasterById(oldAttributeBean.getId());
		
			if(!Util.isNull(currentAttributeBean)){			
				oldAttributeBean = updateAttributeDetails(oldAttributeBean,currentAttributeBean);
			}
		}
		
		oldAttributeBean = iAccomodationDao.createOrUpdateAttributeMaster(oldAttributeBean,sessionFactory);
		
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


	@Override
	public AmenitiesMapping createAmenitiyMapping(AmenitiesMapping oldAttributeBean) {
		
		if(!Util.isNull(oldAttributeBean.getId())){
			AmenitiesMapping currentAttributeBean = getAmenitiesMappingById(oldAttributeBean.getId());
		
			if(!Util.isNull(currentAttributeBean)){			
				oldAttributeBean = updateAmenitiesMappingDetails(oldAttributeBean,currentAttributeBean);
			}
		}
		
		oldAttributeBean = iAccomodationDao.createOrUpdateAmenitiesMapping(oldAttributeBean,sessionFactory);
		
		return oldAttributeBean;
		
	}


	private AmenitiesMapping updateAmenitiesMappingDetails(
			AmenitiesMapping currentAttributeBean,
			AmenitiesMapping oldAttributeBean) {
		
		if(!Util.isNull(currentAttributeBean.getAttributeId())){
			oldAttributeBean.setAttributeId(currentAttributeBean.getAttributeId());
		}
		
		if(!Util.isNull(currentAttributeBean.getParentAmenitiyId())){
			oldAttributeBean.setParentAmenitiyId(currentAttributeBean.getParentAmenitiyId());
		}
		if(!Util.isNull(currentAttributeBean.getIsDeleted())){
			oldAttributeBean.setIsDeleted(currentAttributeBean.getIsDeleted());
		}
				
		return oldAttributeBean;
	}

	private AmenitiesMapping getAmenitiesMappingById(Long id) {
		
		return iAccomodationDao.getAmenitiesMappingById(id,sessionFactory);
	}
	
	

}
