package com.city.erp.service.zqy;

import java.util.List;

import com.city.erp.model.zqy.AdminidtratorModel;

//部门业务接口
public interface AdminidtratorService {
	
	//增加部门
	public void add(AdminidtratorModel dm) throws Exception;
	//修改部门
	public void modify(AdminidtratorModel dm) throws Exception;
	//删除部门
	public void delete(AdminidtratorModel dm) throws Exception;
	
	//取得所有部门列表
	public List<AdminidtratorModel> getListByAll() throws Exception;
	//取得所有部门列表，分页
	public List<AdminidtratorModel> getListByAllWithPage(int rows,int page) throws Exception;
	
	//取得指定的部门
	public AdminidtratorModel getAdminidtrator(String no) throws Exception;
	//取得部门总个数
	public int getCountByAll() throws Exception;
	//取得部门总页数
	public int getPageCountByAll(int rows) throws Exception;
	
	boolean validate(String uid, String pw) throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	
	

}
