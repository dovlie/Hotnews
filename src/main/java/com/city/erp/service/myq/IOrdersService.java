package com.city.erp.service.myq;

import java.io.InputStream;
import java.util.List;
import com.city.erp.model.myq.OrdersModel;

public interface IOrdersService {

	//增加产品
	public void add(OrdersModel pm) throws Exception;
	
	//修改产品
	public void modify(OrdersModel pm) throws Exception;
	
	//删除产品
	public void delete(OrdersModel pm) throws Exception;
	//取得所有产品列表
	public List<OrdersModel> getListByAll() throws Exception;
	//取得所有产品列表，分页
	public List<OrdersModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得指定的产品
	public OrdersModel getOrders(String oid) throws Exception;
	//取得产品总个数
	public int getCountByAll() throws Exception;
	//取得产品总页数
	public int getPageCountByAll(int rows) throws Exception;


}
