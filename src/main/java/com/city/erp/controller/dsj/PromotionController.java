package com.city.erp.controller.dsj;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.city.erp.model.dsj.PromotionModel;
import com.city.erp.result.ListResultJson;
import com.city.erp.result.ResultJson;
import com.city.erp.service.dsj.IPromotionService;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

	private IPromotionService prs = null;

	@Autowired
	public void setPrs(IPromotionService prs) {
		this.prs = prs;
	}
	
	@RequestMapping("/add")
	public ResultJson add(PromotionModel prm) throws Exception
	{
		ResultJson result=new ResultJson();
		prs.add(prm);
		result.setResult("Y");
		return result;
	}

	@RequestMapping("/modify")
	public ResultJson modify(PromotionModel prm) throws Exception
	{
		ResultJson result=new ResultJson();
		prs.modify(prm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/delete")
	public ResultJson delete(PromotionModel prm) throws Exception
	{
		ResultJson result=new ResultJson();
		prs.delete(prm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public PromotionModel get(@RequestParam String prid) throws Exception
	{
		return prs.getPromotion(prid);
	}
	
	@RequestMapping("/get/all")
	public List<PromotionModel> getAll() throws Exception
	{
		return prs.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<PromotionModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return prs.getListByAllWithPage(rows, page);
	}
	
	@RequestMapping("/get/all/pages")
	public ListResultJson getListWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
	{
		ListResultJson result=new ListResultJson();
		
		//int count=prs.getCountByAll();
		int pageCount=prs.getPageCountByAll(rows);
		List list=prs.getListByAllWithPage(rows, page);

		result.setPages(pageCount);
		result.setTotal(pageCount);
		//result.setTotal(count);
		result.setPage(page);
		result.setRows(list);
		
		return result;
	}
	
}
