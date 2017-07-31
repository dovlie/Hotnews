package com.city.erp.service.dsj;

import java.io.InputStream;
import java.util.List;
import com.city.erp.model.dsj.ProductModel;
import com.city.erp.model.sxy.ProductTypeModel;
import com.city.erp.model.sxy.WarehouseModel;

public interface IProductService {

	//增加产品
	public void add(ProductModel pm) throws Exception;
	
	public void add(ProductModel pm,InputStream photo,String fileName,String contentType) throws Exception;
	//修改产品
	public void modify(ProductModel pm) throws Exception;
	
	public void modify(ProductModel pm,InputStream photo,String fileName,String contentType) throws Exception;
	//删除产品
	public void delete(ProductModel pm) throws Exception;
	//取得所有产品列表
	public List<ProductModel> getListByAll() throws Exception;
	//取得所有产品列表，分页
	public List<ProductModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得指定的产品
	public ProductModel getProduct(String pid) throws Exception;
	//取得产品总个数
	public int getCountByAll() throws Exception;
	//取得产品总页数
	public int getPageCountByAll(int rows) throws Exception;
	//检查员工ID是否存在
	public boolean checkExist(String pid) throws Exception;

	public List<ProductModel> getListByPricePoint(String pid) throws Exception;
	
	public List<ProductModel> getListByPromotionPoint(String prid) throws Exception;
	
	public List<ProductModel> getListByOriginPoint(String oid) throws Exception;

	public List<ProductTypeModel> getListByProductTypePoint(String ptid) throws Exception;
	
	public List<WarehouseModel> getListByWarehousePoint(String wid) throws Exception;
}
