package com.city.erp.service.dsj;

import java.util.List;

import com.city.erp.model.dsj.PromotionModel;

public interface IPromotionService {
	//增加优惠
	public void add(PromotionModel prm) throws Exception;
	//修改优惠
	public void modify(PromotionModel prm) throws Exception;
	//删除优惠
	public void delete(PromotionModel prm) throws Exception;
	//取得所有优惠列表
	public List<PromotionModel> getListByAll() throws Exception;
	//取得所有优惠列表，分页
	public List<PromotionModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得指定的优惠
	public PromotionModel getPromotion(String prid) throws Exception;
	//取得优惠总个数
	public int getCountByAll() throws Exception;
	//取得优惠总页数
	public int getPageCountByAll(int rows) throws Exception;
	//检查指定的优惠能否被删除，如果部门有产品则不能删除此优惠
	public boolean checkCanBeDelete(int promotionNo) throws Exception;
}
