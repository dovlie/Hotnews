package com.city.erp.controller.zqy;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.city.erp.model.zqy.FinanceModel;
import com.city.erp.result.ResultJson;
import com.city.erp.service.zqy.IFinanceService;


@RestController
@RequestMapping("/finance")
public class FinanceController {
private IFinanceService ds=null;
	
	@Autowired
	public void setDs(IFinanceService ds) {
		this.ds = ds;
	}

	@RequestMapping("/add")
	public ResultJson add(FinanceModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.add(dm);
		result.setResult("Y");
		
		return result;
	}

	@RequestMapping("/modify")
	public ResultJson modify(FinanceModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.add(dm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/delete")
	public ResultJson delete(FinanceModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.delete(dm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public FinanceModel get(@RequestParam String no) throws Exception
	{
		return ds.getDepartment(no);
	}
	
	@RequestMapping("/get/all")
	public List<FinanceModel> getAll() throws Exception
	{
		return ds.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<FinanceModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return ds.getListByAllWithPage(rows, page);
	}
	

}
