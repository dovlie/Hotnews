package com.city.erp.service.impl.myq;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.erp.model.myq.DispatchModel;
import com.city.erp.service.myq.IDispatchService;
@Service
@Transactional
public class DispatchServiceImpl implements IDispatchService{
	private SessionFactory sf=null;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void add(DispatchModel dm) throws Exception {
		sf.getCurrentSession().save(dm);
		
		
	}

	@Override
	public void modify(DispatchModel dm) throws Exception {
		sf.getCurrentSession().update(dm);
		
	}

	@Override
	public void delete(DispatchModel dm) throws Exception {
		sf.getCurrentSession().delete(dm);

	}

	@Override
	public List<DispatchModel> getListByAll() throws Exception {
		
		return sf.getCurrentSession().createQuery("from DispatchModel", DispatchModel.class).getResultList();
	}

	@Override
	public List<DispatchModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return sf.getCurrentSession().createQuery("from DispatchModel", DispatchModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}

	@Override
	public DispatchModel getDispatch(String no) throws Exception {
		
		return sf.getCurrentSession().get(DispatchModel.class, no);
	}

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(dm.did) from DispatchModel dm", Long.class).uniqueResult();
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
	public boolean checkCanBeDelete(int dispatchNo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
