package com.city.erp.service.impl.dsj;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.erp.model.dsj.OriginModel;
import com.city.erp.model.dsj.PublicityModel;
import com.city.erp.service.dsj.IPublicityService;

@Service
@Transactional
public class PublicityServiceImpl implements IPublicityService {

	private SessionFactory sf=null;
	
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void add(PublicityModel pm) throws Exception {
		sf.getCurrentSession().save(pm);

	}

	@Override
	public void modify(PublicityModel pm) throws Exception {
		sf.getCurrentSession().update(pm);

	}

	@Override
	public void delete(PublicityModel pm) throws Exception {
		sf.getCurrentSession().delete(pm);

	}

	@Override
	public PublicityModel getPublicity(String puid) throws Exception {
		return sf.getCurrentSession().get(PublicityModel.class, puid);
	}

	@Override
	public List<PublicityModel> getListByAll() throws Exception {
		return sf.getCurrentSession().createQuery("from PublicityModel", PublicityModel.class).getResultList();
	}

	@Override
	public List<PublicityModel> getListByAllWithPage(int rows, int page) throws Exception {
		return sf.getCurrentSession().createQuery("from PublicityModel", PublicityModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(pm.puid) from PublicityModel pm", Long.class).uniqueResult();
		return lcount.intValue();
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		int count=this.getCountByAll();
		int pageCount=0;
		if(count%rows==0){
			pageCount=count/rows;
		}
		else{
			pageCount=count/rows+1;
		}
		return pageCount;
	}

}
