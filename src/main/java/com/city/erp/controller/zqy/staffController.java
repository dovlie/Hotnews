package com.city.erp.controller.zqy;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.city.erp.model.sxy.SupplierModel;
import com.city.erp.model.zqy.StaffModel;
import com.city.erp.result.ResultJson;
import com.city.erp.service.zqy.IStaffService;


@RestController
@RequestMapping("/staff")
public class staffController {
private IStaffService ds=null;
	
	@Autowired
	public void setDs(IStaffService ds) {
		this.ds = ds;
	}

	@RequestMapping("/add")
	public ResultJson add(StaffModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.add(dm);
		result.setResult("Y");
		
		return result;
	}

	@RequestMapping("/modify")
	public ResultJson modify(StaffModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.add(dm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/delete")
	public ResultJson delete(StaffModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.delete(dm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public StaffModel get(@RequestParam String no) throws Exception
	{
		return ds.getDepartment(no);
	}
	
	@RequestMapping("/get/all")
	public List<StaffModel> getAll() throws Exception
	{
		return ds.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<StaffModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return ds.getListByAllWithPage(rows, page);
	}
	
	
}
