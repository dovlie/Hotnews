package com.city.erp.service.impl.dsj;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.erp.model.dsj.OriginModel;
import com.city.erp.service.dsj.IOriginService;

@Service
@Transactional
public class OriginServiceImpl implements IOriginService {

	private SessionFactory sf=null;
	
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void add(OriginModel om) throws Exception {
		sf.getCurrentSession().save(om);

	}

	@Override
	public void modify(OriginModel om) throws Exception {
		sf.getCurrentSession().update(om);

	}

	@Override
	public void delete(OriginModel om) throws Exception {
		sf.getCurrentSession().delete(om);

	}

	@Override
	public List<OriginModel> getListByAll() throws Exception {
		return sf.getCurrentSession().createQuery("from OriginModel", OriginModel.class).getResultList();
		
	}

	@Override
	public List<OriginModel> getListByAllWithPage(int rows, int page) throws Exception {
		return sf.getCurrentSession().createQuery("from OriginModel", OriginModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
		
	}

	@Override
	public OriginModel getOrigin(String oid) throws Exception {
		return sf.getCurrentSession().get(OriginModel.class, oid);
		
	}

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(om.oid) from OriginModel om", Long.class).uniqueResult();
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

	@Override
	public boolean checkCanBeDelete(int originNo) throws Exception {
		boolean result=true;
		
		Long lcount=sf.getCurrentSession().createQuery("select count(pm.pid) from ProductModel pm where pm.om.oid=:oid",Long.class).setParameter("no", originNo).uniqueResult();
		if(lcount>0){
			result=false;
		}
		return result;
	}

}
