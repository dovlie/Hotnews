package com.city.erp.service.impl.myq;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.erp.model.dsj.PriceModel;
import com.city.erp.model.myq.vipModel;
import com.city.erp.service.myq.vipService;
@Service
@Transactional
public class vipServiceImpl implements vipService{
	private SessionFactory sf=null;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void add(vipModel dm) throws Exception {
		sf.getCurrentSession().save(dm);
		
		
	}

	@Override
	public void modify(vipModel dm) throws Exception {
		sf.getCurrentSession().update(dm);
		
	}

	@Override
	public void delete(vipModel dm) throws Exception {
		sf.getCurrentSession().delete(dm);

	}

	@Override
	public List<vipModel> getListByAll() throws Exception {
		
		return sf.getCurrentSession().createQuery("from vipModel", vipModel.class).getResultList();
	}

	@Override
	public List<vipModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return sf.getCurrentSession().createQuery("from vipModel", vipModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}

	@Override
	public vipModel getVip(int no) throws Exception {
		
		return sf.getCurrentSession().get(vipModel.class, no);
	}

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(dm.no) from vipModel dm", Long.class).uniqueResult();
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
