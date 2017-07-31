package com.city.erp.service.impl.sxy;


import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.erp.model.sxy.WarehouseModel;
import com.city.erp.service.sxy.WarehouseService;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService{
	private SessionFactory sf=null;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void add(WarehouseModel wm) throws Exception {
		sf.getCurrentSession().save(wm);
	}

	@Override
	public void modify(WarehouseModel wm) throws Exception {
		sf.getCurrentSession().update(wm);
		
	}

	@Override
	public void delete(WarehouseModel wm) throws Exception {
		sf.getCurrentSession().delete(wm);
	}

	@Override
	public List<WarehouseModel> getListByAll() throws Exception {
		return sf.getCurrentSession().createQuery("from WarehouseModel", WarehouseModel.class).getResultList();
		
	}

	@Override
	public List<WarehouseModel> getListByAllWithPage(int rows, int page) throws Exception {
		return sf.getCurrentSession().createQuery("from WarehouseModel", WarehouseModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}

	

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(wm.wid) from WarehouseModel wm", Long.class).uniqueResult();
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
	public WarehouseModel getWarehouse(String no) throws Exception {
		return  sf.getCurrentSession().get(WarehouseModel.class, no);
	}
}
