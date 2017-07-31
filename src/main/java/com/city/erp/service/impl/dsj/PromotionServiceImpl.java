package com.city.erp.service.impl.dsj;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.erp.model.dsj.PromotionModel;
import com.city.erp.service.dsj.IPromotionService;

@Service
@Transactional
public class PromotionServiceImpl implements IPromotionService {

	private SessionFactory sf=null;
	
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void add(PromotionModel prm) throws Exception {
		sf.getCurrentSession().save(prm);

	}

	@Override
	public void modify(PromotionModel prm) throws Exception {
		sf.getCurrentSession().update(prm);

	}

	@Override
	public void delete(PromotionModel prm) throws Exception {
		sf.getCurrentSession().delete(prm);

	}

	@Override
	public List<PromotionModel> getListByAll() throws Exception {
		return sf.getCurrentSession().createQuery("from PromotionModel", PromotionModel.class).getResultList();
		
	}

	@Override
	public List<PromotionModel> getListByAllWithPage(int rows, int page) throws Exception {
		return sf.getCurrentSession().createQuery("from PromotionModel", PromotionModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
		
	}

	@Override
	public PromotionModel getPromotion(String prid) throws Exception {
		return sf.getCurrentSession().get(PromotionModel.class, prid);
		
	}

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(prm.prid) from PromotionModel prm", Long.class).uniqueResult();
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
	public boolean checkCanBeDelete(int promotionNo) throws Exception {
		boolean result=true;
		
		Long lcount=sf.getCurrentSession().createQuery("select count(pm.pid) from PromotionModel prm where pm.prm.prid=:prid",Long.class).setParameter("no", promotionNo).uniqueResult();
		if(lcount>0){
			result=false;
		}
		return result;
	}

}
