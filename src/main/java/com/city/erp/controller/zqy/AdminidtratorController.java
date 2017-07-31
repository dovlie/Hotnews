package com.city.erp.controller.zqy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.city.erp.model.zqy.AdminidtratorModel;
import com.city.erp.result.ResultJson;
import com.city.erp.service.zqy.AdminidtratorService;


@RestController
@RequestMapping("/user")
public class AdminidtratorController {
private AdminidtratorService ds=null;
	
	@Autowired
	public void setDs(AdminidtratorService ds) {
		this.ds = ds;
	}

	@RequestMapping("/add")
	public ResultJson add(AdminidtratorModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.add(dm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/modify")
	public ResultJson modify(AdminidtratorModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.add(dm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/delete")
	public ResultJson delete(AdminidtratorModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.delete(dm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public AdminidtratorModel get(@RequestParam String no) throws Exception
	{
		return ds.getAdminidtrator(no);
	}
	
	@RequestMapping("/get/all")
	public List<AdminidtratorModel> getAll() throws Exception
	{
		return ds.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<AdminidtratorModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return ds.getListByAllWithPage(rows, page);
	}
	
	
}
