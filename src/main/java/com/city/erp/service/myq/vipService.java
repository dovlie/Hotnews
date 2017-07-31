package com.city.erp.service.myq;

import java.util.List;

import com.city.erp.model.dsj.PriceModel;
import com.city.erp.model.myq.vipModel;

public interface vipService {
	//增加会员
		public void add(vipModel dm) throws Exception;
		//修改会员
		public void modify(vipModel dm) throws Exception;
		//删除会员
		public void delete(vipModel dm) throws Exception;
		
		//取得所有会员列表
		public List<vipModel> getListByAll() throws Exception;
		//取得所有会员列表，分页
		public List<vipModel> getListByAllWithPage(int rows,int page) throws Exception;
		
		//取得指定的会员
		public vipModel getVip(int no) throws Exception;
		//取得会员总个数
		public int getCountByAll() throws Exception;
		//取得会员总页数
		public int getPageCountByAll(int rows) throws Exception;
}

