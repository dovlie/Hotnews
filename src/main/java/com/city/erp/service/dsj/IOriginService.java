package com.city.erp.service.dsj;

import java.util.List;

import com.city.erp.model.dsj.OriginModel;


public interface IOriginService {
	//增加产地
	public void add(OriginModel om) throws Exception;
	//修改产地
	public void modify(OriginModel om) throws Exception;
	//删除产地
	public void delete(OriginModel om) throws Exception;
	//取得所有产地列表
	public List<OriginModel> getListByAll() throws Exception;
	//取得所有产地列表，分页
	public List<OriginModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得指定的产地
	public OriginModel getOrigin(String oid) throws Exception;
	//取得产地总个数
	public int getCountByAll() throws Exception;
	//取得产地总页数
	public int getPageCountByAll(int rows) throws Exception;
	//检查指定的产地能否被删除，如果部门有产品则不能删除此产地
	public boolean checkCanBeDelete(int originNo) throws Exception;
}
