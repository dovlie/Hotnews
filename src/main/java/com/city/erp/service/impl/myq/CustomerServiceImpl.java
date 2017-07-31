package com.city.erp.service.impl.myq;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.city.erp.model.myq.CustomerModel;
import com.city.erp.service.myq.ICustomerService;
@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	private SessionFactory sf=null;
	
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void add(CustomerModel cm) throws Exception {
		sf.getCurrentSession().save(cm);

	}

	@Override
	public void modify(CustomerModel cm) throws Exception {
		sf.getCurrentSession().update(cm);

	}

	@Override
	public void delete(CustomerModel cm) throws Exception {
		sf.getCurrentSession().delete(cm);

	}

	@Override
	public List<CustomerModel> getListByAll() throws Exception {
		return sf.getCurrentSession().createQuery("from CustomerModel", CustomerModel.class).getResultList();
	}

	@Override
	public List<CustomerModel> getListByAllWithPage(int rows, int page) throws Exception {
		return sf.getCurrentSession().createQuery("from CustomerModel", CustomerModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}

	@Override
	public CustomerModel getCustomer(String cid) throws Exception {
		return sf.getCurrentSession().get(CustomerModel.class, cid);
	}

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(cm.cid) from CustomerModel cm", Long.class).uniqueResult();
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
	public boolean checkCanBeDelete(int customerNo) throws Exception {
		boolean result=true;
		
		Long lcount=sf.getCurrentSession().createQuery("select count(om.oid) from OrderModel om where om.cm.cid=:cid",Long.class).setParameter("no", customerNo).uniqueResult();
		if(lcount>0){
			result=false;
		}
		return result;
	}

}
