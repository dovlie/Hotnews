package com.city.erp.service.zqy;

import java.util.List;

import com.city.erp.model.zqy.StaffModel;

//部门业务接口
public interface IStaffService {
	
	//增加部门
	public void add(StaffModel dm) throws Exception;
	//修改部门
	public void modify(StaffModel dm) throws Exception;
	//删除部门
	public void delete(StaffModel dm) throws Exception;
	
	//取得所有部门列表
	public List<StaffModel> getListByAll() throws Exception;
	//取得所有部门列表，分页
	public List<StaffModel> getListByAllWithPage(int rows,int page) throws Exception;
	
	//取得指定的部门
	public StaffModel getDepartment(String no) throws Exception;
	//取得部门总个数
	public int getCountByAll() throws Exception;
	//取得部门总页数
	public int getPageCountByAll(int rows) throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	
	

}
