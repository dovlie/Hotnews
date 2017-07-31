package com.city.erp.service.impl.sxy;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.erp.model.sxy.ProcurementProgramModel;
import com.city.erp.service.sxy.ProcurementProgramService;
@Service
@Transactional
public class ProcurementProgramServiceImpl implements ProcurementProgramService{
	private SessionFactory sf=null;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void add(ProcurementProgramModel ppm) throws Exception {
		sf.getCurrentSession().save(ppm);
	}

	@Override
	public void modify(ProcurementProgramModel ppm) throws Exception {
		sf.getCurrentSession().update(ppm);
		
	}

	@Override
	public void delete(ProcurementProgramModel ppm) throws Exception {
		sf.getCurrentSession().delete(ppm);
	}

	@Override
	public List<ProcurementProgramModel> getListByAll() throws Exception {
		return sf.getCurrentSession().createQuery("from ProcurementProgramModel", ProcurementProgramModel.class).getResultList();
		
	}

	@Override
	public List<ProcurementProgramModel> getListByAllWithPage(int rows, int page) throws Exception {
		return sf.getCurrentSession().createQuery("from ProcurementProgramModel", ProcurementProgramModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}

	
	@Override
	public ProcurementProgramModel getProcurementProgram(String no) throws Exception{
		return  sf.getCurrentSession().get(ProcurementProgramModel.class, no);
	}
	

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(ppm.pid) from ProcurementProgramModel ppm", Long.class).uniqueResult();
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
