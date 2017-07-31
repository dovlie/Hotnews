package com.city.erp.service.impl.dsj;

import java.util.List;



import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.erp.model.dsj.PriceModel;
import com.city.erp.service.dsj.IPriceService;

@Service
@Transactional
public class PriceServiceImpl implements IPriceService {

	private SessionFactory sf=null;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void add(PriceModel pm) throws Exception {
		sf.getCurrentSession().save(pm);

	}

	@Override
	public void modify(PriceModel pm) throws Exception {
		sf.getCurrentSession().update(pm);

	}

	@Override
	public void delete(PriceModel pm) throws Exception {
		sf.getCurrentSession().delete(pm);

	}

	@Override
	public PriceModel getPrice(String id) throws Exception {
		return  sf.getCurrentSession().get(PriceModel.class, id);
	}

	@Override
	public List<PriceModel> getListByAll() throws Exception {
		return sf.getCurrentSession().createQuery("from PriceModel", PriceModel.class).getResultList();
	}
	
	@Override
	public List<PriceModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return sf.getCurrentSession().createQuery("from PriceModel", PriceModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}
	
	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(pm.no) from PriceModel pm", Long.class).uniqueResult();
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
