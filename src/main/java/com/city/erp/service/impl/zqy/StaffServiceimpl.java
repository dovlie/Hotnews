package com.city.erp.service.impl.zqy;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.city.erp.model.zqy.StaffModel;
import com.city.erp.service.zqy.IStaffService;

//部门业务实现类

@Service
@Transactional
public class StaffServiceimpl implements IStaffService {

	private SessionFactory sf=null;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void add(StaffModel dm) throws Exception {
		sf.getCurrentSession().save(dm);
		
		
	}

	@Override
	public void modify(StaffModel dm) throws Exception {
		sf.getCurrentSession().update(dm);
		
	}

	@Override
	public void delete(StaffModel dm) throws Exception {
		sf.getCurrentSession().delete(dm);
		
	}

	@Override
	public List<StaffModel> getListByAll() throws Exception {
		
		return sf.getCurrentSession().createQuery("from StaffModel", StaffModel.class).getResultList();
	}

	@Override
	public List<StaffModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return sf.getCurrentSession().createQuery("from StaffModel", StaffModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}

	@Override
	public StaffModel getDepartment(String no) throws Exception {
		
		return sf.getCurrentSession().get(StaffModel.class, no);
	}

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(dm.no) from StaffModel dm", Long.class).uniqueResult();
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
