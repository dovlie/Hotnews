package com.city.erp.service.dsj;

import java.util.List;

import com.city.erp.model.dsj.PriceModel;

public interface IPriceService {

	//增加价格
	public void add(PriceModel pm) throws Exception;
	//修改价格
	public void modify(PriceModel pm) throws Exception;
	//删除价格
	public void delete(PriceModel pm) throws Exception;
	//取一条价格信息
	public PriceModel getPrice(String id) throws Exception;
	//取价格列表
	public List<PriceModel> getListByAll() throws Exception;
	//分页取列表
	public List<PriceModel> getListByAllWithPage(int rows, int page) throws Exception;
	//取得价格总个数
	public int getCountByAll() throws Exception;
	//取得价格总页数
	public int getPageCountByAll(int rows) throws Exception;
}
