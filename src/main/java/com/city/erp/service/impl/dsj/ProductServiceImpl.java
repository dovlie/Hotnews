package com.city.erp.service.impl.dsj;

import java.io.InputStream;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.city.erp.model.dsj.OriginModel;
import com.city.erp.model.dsj.ProductModel;
import com.city.erp.model.sxy.ProductTypeModel;
import com.city.erp.model.sxy.WarehouseModel;
import com.city.erp.service.dsj.IProductService;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	private SessionFactory sf=null;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void add(ProductModel pm) throws Exception {
		sf.getCurrentSession().save(pm);

	}

	@Override
	public void add(ProductModel pm, InputStream photo, String fileName, String contentType) throws Exception {

		Session session=sf.getCurrentSession();
		Blob blobphoto=session.getLobHelper().createBlob(photo, photo.available());
		pm.setPhoto(blobphoto);
		pm.setPhotoFileName(fileName);
		pm.setPhotoContentType(contentType);
		session.save(pm);

	}

	@Override
	public void modify(ProductModel pm) throws Exception {
		sf.getCurrentSession().update(pm);

	}

	@Override
	public void modify(ProductModel pm, InputStream photo, String fileName, String contentType) throws Exception {
		Session session=sf.getCurrentSession();
		Blob blobphoto=session.getLobHelper().createBlob(photo, photo.available());
		pm.setPhoto(blobphoto);
		pm.setPhotoFileName(fileName);
		pm.setPhotoContentType(contentType);
		sf.getCurrentSession().update(pm);

	}

	@Override
	public void delete(ProductModel pm) throws Exception {
		sf.getCurrentSession().delete(pm);

	}

	@Override
	public List<ProductModel> getListByAll() throws Exception {
		return sf.getCurrentSession().createQuery("from ProductModel", ProductModel.class).getResultList();
	}

	@Override
	public List<ProductModel> getListByAllWithPage(int rows, int page) throws Exception {
		return sf.getCurrentSession().createQuery("from ProductModel", ProductModel.class).setFirstResult(rows*(page-1)).setMaxResults(rows).getResultList();
	}
	
	@Override
	public List<ProductModel> getListByPricePoint(String pid) throws Exception {
		
		String hql="from ProductModel pm where pm.price.pid=:pid";
		Query query=sf.getCurrentSession().createQuery(hql);
		List<ProductModel> list = null;
		query.setString("pid", pid);
		list=(List<ProductModel>)query.getResultList();
		
		return list;
	}
	

	@Override
	public List<ProductModel> getListByPromotionPoint(String prid) throws Exception {
		
		String hql="from ProductModel pm where pm.promotion.prid=:prid";
		Query query=sf.getCurrentSession().createQuery(hql);
		List<ProductModel> list = null;
		query.setString("prid", prid);
		list=(List<ProductModel>)query.getResultList();
		
		return list;
	}

	@Override
	public List<ProductModel> getListByOriginPoint(String oid) throws Exception {
		
		String hql="from ProductModel pm where pm.origin.oid=:oid";
		Query query=sf.getCurrentSession().createQuery(hql);
		List<ProductModel> list = null;
		query.setString("oid", oid);
		list=(List<ProductModel>)query.getResultList();
		
		return list;
	}

	@Override
	public ProductModel getProduct(String pid) throws Exception {
		return sf.getCurrentSession().get(ProductModel.class, pid);
	}

	@Override
	public int getCountByAll() throws Exception {
		Long lcount=sf.getCurrentSession().createQuery("select count(pm.pid) from ProductModel pm", Long.class).uniqueResult();
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
	public boolean checkExist(String pid) throws Exception {
		Query<Long> query=sf.getCurrentSession().createQuery("select count(pm.pid) from ProductModel pm where pm.pid=:pid",Long.class);
		query.setParameter("pid", pid);
		Long count=query.uniqueResult();
		boolean result=false;
		if(count>0){
			result=true;
		}
		return result;
	}

	@Override
	public List<ProductTypeModel> getListByProductTypePoint(String ptid) throws Exception {
		String hql="from ProductModel pm where pm.producttype.ptid=:ptid";
		Query query=sf.getCurrentSession().createQuery(hql);
		List<ProductTypeModel> list = null;
		query.setString("ptid", ptid);
		list=(List<ProductTypeModel>)query.getResultList();
		
		return list;
	}

	@Override
	public List<WarehouseModel> getListByWarehousePoint(String wid) throws Exception {
		String hql="from ProductModel pm where pm.warehose.wid=:wid";
		Query query=sf.getCurrentSession().createQuery(hql);
		List<WarehouseModel> list = null;
		query.setString("wid", wid);
		list=(List<WarehouseModel>)query.getResultList();
		
		return list;
	}

	

}
