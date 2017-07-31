package com.city.erp.service.myq;

import java.util.List;

import com.city.erp.model.myq.DispatchModel;

public interface IDispatchService {
	//增加客户
		public void add(DispatchModel cm) throws Exception;
		//修改客户
		public void modify(DispatchModel cm) throws Exception;
		//删除客户
		public void delete(DispatchModel cm) throws Exception;
		//取得所有客户列表
		public List<DispatchModel> getListByAll() throws Exception;
		//取得所有客户列表，分页
		public List<DispatchModel> getListByAllWithPage(int rows,int page) throws Exception;
		//取得指定的客户
		public DispatchModel getDispatch(String did) throws Exception;
		//取得客户总个数
		public int getCountByAll() throws Exception;
		//取得客户总页数
		public int getPageCountByAll(int rows) throws Exception;
		//检查指定的客户能否被删除，如果部门有订单则不能删除此客户
		public boolean checkCanBeDelete(int dispatchNo) throws Exception;
}
