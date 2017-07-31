package com.city.erp.service.sxy;

import java.util.List;

import com.city.erp.model.sxy.SupplierModel;


public interface SupplierService {
	//增加供应商
	public void add(SupplierModel sm) throws Exception;
	//修改供应商
	public void modify(SupplierModel sm) throws Exception;
	//删除供应商
	public void delete(SupplierModel sm) throws Exception;
	
	//取得所有供应商列表
	public List<SupplierModel> getListByAll() throws Exception;
	//取得所有供应商列表，分页
	public List<SupplierModel> getListByAllWithPage(int rows,int page) throws Exception;
		
	//取得指定的供应商
	public SupplierModel getSupplier(String no) throws Exception;
	//取得供应商总个数
	public int getCountByAll() throws Exception;
	//取得供应商总页数
	public int getPageCountByAll(int rows) throws Exception;
		
}
