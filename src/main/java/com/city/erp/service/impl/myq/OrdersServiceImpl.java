package com.city.erp.service.impl.myq;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.city.erp.model.myq.OrdersModel;
import com.city.erp.service.myq.IOrdersService;


@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

	private SessionFactory sf=null;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void add(OrdersModel pm) throws Exception {
		sf.getCurrentSession().save(pm);

	}

	@Override
	public void modify(OrdersModel pm) throws Exception {
		sf.getCurrentSession().update(pm);

	}

	@Override
	public void delete(OrdersModel pm) throws Exception {
		sf.getCurrentSession().delete(pm);

	}

	@Override
	public List<OrdersModel> getListByAll() throws Exception {
		return sf.getCurrentSession().createQuery("from OrdersModel", OrdersModel.class).getResultList();
	}

	@Override
	public List<OrdersModel> getListByAllWithPage(int rows, int page) throws Exception {
		return sf.getCurrentSession().createQuery("from OrdersModel", OrdersModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}

	@Override
	public OrdersModel getOrders(String oid) throws Exception {
		return sf.getCurrentSession().get(OrdersModel.class, oid);
	}

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(pm.oid) from OrdersModel pm", Long.class).uniqueResult();
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
