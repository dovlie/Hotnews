package com.city.erp.service.sxy;

import java.util.List;

import com.city.erp.model.sxy.ProcurementProgramModel;

public interface ProcurementProgramService {
	//增加供应商
		public void add(ProcurementProgramModel ppm) throws Exception;
		//修改供应商
		public void modify(ProcurementProgramModel ppm) throws Exception;
		//删除供应商
		public void delete(ProcurementProgramModel ppm) throws Exception;
		
		//取得所有供应商列表
		public List<ProcurementProgramModel> getListByAll() throws Exception;
		//取得所有供应商列表，分页
		public List<ProcurementProgramModel> getListByAllWithPage(int rows,int page) throws Exception;
			
		//取得指定的供应商
		public ProcurementProgramModel getProcurementProgram(String no) throws Exception;
		//取得供应商总个数
		public int getCountByAll() throws Exception;
		//取得供应商总页数
		public int getPageCountByAll(int rows) throws Exception;
			
}
