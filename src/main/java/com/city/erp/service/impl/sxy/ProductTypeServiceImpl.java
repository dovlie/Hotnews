package com.city.erp.service.impl.sxy;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.erp.model.sxy.ProductTypeModel;
import com.city.erp.service.sxy.ProductTypeService;

@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService{
	private SessionFactory sf=null;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void add(ProductTypeModel ptm) throws Exception {
		sf.getCurrentSession().save(ptm);
	}

	@Override
	public void modify(ProductTypeModel ptm) throws Exception {
		sf.getCurrentSession().update(ptm);
		
	}

	@Override
	public void delete(ProductTypeModel ptm) throws Exception {
		sf.getCurrentSession().delete(ptm);
	}

	@Override
	public List<ProductTypeModel> getListByAll() throws Exception {
		return sf.getCurrentSession().createQuery("from ProductTypeModel", ProductTypeModel.class).getResultList();
		
	}

	@Override
	public List<ProductTypeModel> getListByAllWithPage(int rows, int page) throws Exception {
		return sf.getCurrentSession().createQuery("from ProductTypeModel", ProductTypeModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}

	
	@Override
	public ProductTypeModel getProductType(String no) throws Exception{
		return  sf.getCurrentSession().get(ProductTypeModel.class, no);
	}
	

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(ptm.ptid) from ProductTypeModel ptm", Long.class).uniqueResult();
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
