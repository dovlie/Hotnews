package com.city.erp.service.impl.zqy;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.city.erp.model.zqy.AdminidtratorModel;
import com.city.erp.service.zqy.AdminidtratorService;

//部门业务实现类

@Service
@Transactional
public class AdminidtratorServiceimpl implements AdminidtratorService {

	private SessionFactory sf=null;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void add(AdminidtratorModel dm) throws Exception {
		sf.getCurrentSession().save(dm);
		
		
	}

	@Override
	public void modify(AdminidtratorModel dm) throws Exception {
		sf.getCurrentSession().update(dm);
		
	}

	@Override
	public void delete(AdminidtratorModel dm) throws Exception {
		sf.getCurrentSession().delete(dm);
		
	}

	@Override
	public List<AdminidtratorModel> getListByAll() throws Exception {
		
		return sf.getCurrentSession().createQuery("from AdminidtratorModel", AdminidtratorModel.class).getResultList();
	}

	@Override
	public List<AdminidtratorModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return sf.getCurrentSession().createQuery("from AdminidtratorModel", AdminidtratorModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}

	public AdminidtratorModel getAdminidtrator(String no) throws Exception {
		
		return sf.getCurrentSession().get(AdminidtratorModel.class, no);
	}

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(dm.no) from AdminidtratorModel dm", Long.class).uniqueResult();
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
	public boolean validate(String uid, String pw) throws Exception {
		boolean result=false;
		String hql="select count(am.uid) from AdminidtratorModel am where am.uid=:uid and am.pw=:pw";
		Query query=sf.getCurrentSession().createQuery(hql);
		query.setString("uid",uid);
		query.setString("pw", pw);
		Long lcount=(Long)query.uniqueResult();
		if(lcount!=null&&lcount>0){
			result=true;
		}
	
		return result;
	}

}
