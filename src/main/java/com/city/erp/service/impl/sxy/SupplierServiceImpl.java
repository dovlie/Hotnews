package com.city.erp.service.impl.sxy;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.erp.model.dsj.PriceModel;
import com.city.erp.model.sxy.SupplierModel;
import com.city.erp.service.sxy.SupplierService;




@Service
@Transactional
public class SupplierServiceImpl implements SupplierService{
	private SessionFactory sf=null;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void add(SupplierModel sm) throws Exception {
		sf.getCurrentSession().save(sm);
	}

	@Override
	public void modify(SupplierModel sm) throws Exception {
		sf.getCurrentSession().update(sm);
		
	}

	@Override
	public void delete(SupplierModel sm) throws Exception {
		sf.getCurrentSession().delete(sm);
	}

	@Override
	public List<SupplierModel> getListByAll() throws Exception {
		return sf.getCurrentSession().createQuery("from SupplierModel", SupplierModel.class).getResultList();
		
	}

	@Override
	public List<SupplierModel> getListByAllWithPage(int rows, int page) throws Exception {
		return sf.getCurrentSession().createQuery("from SupplierModel", SupplierModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}

	

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(sm.sid) from SupplierModel sm", Long.class).uniqueResult();
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
	public SupplierModel getSupplier(String no) throws Exception {
		return  sf.getCurrentSession().get(SupplierModel.class, no);
	}

	
}
