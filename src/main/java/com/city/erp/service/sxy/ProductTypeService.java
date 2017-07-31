package com.city.erp.service.sxy;

import java.util.List;

import com.city.erp.model.sxy.ProductTypeModel;
import com.city.erp.model.sxy.SupplierModel;

public interface ProductTypeService {
	//增加供应商
		public void add(ProductTypeModel ptm) throws Exception;
		//修改供应商
		public void modify(ProductTypeModel ptm) throws Exception;
		//删除供应商
		public void delete(ProductTypeModel ptm) throws Exception;
		
		//取得所有供应商列表
		public List<ProductTypeModel> getListByAll() throws Exception;
		//取得所有供应商列表，分页
		public List<ProductTypeModel> getListByAllWithPage(int rows,int page) throws Exception;
			
		//取得指定的供应商
		public ProductTypeModel getProductType(String no) throws Exception;
		//取得供应商总个数
		public int getCountByAll() throws Exception;
		//取得供应商总页数
		public int getPageCountByAll(int rows) throws Exception;
			
}
