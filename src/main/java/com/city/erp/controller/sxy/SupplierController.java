package com.city.erp.controller.sxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.city.erp.model.sxy.SupplierModel;
import com.city.erp.result.ResultJson;
import com.city.erp.service.sxy.SupplierService;



@RestController
@RequestMapping("/supplier")
public class SupplierController {
	private SupplierService ss=null;
	
	@Autowired
	public void setPs(SupplierService ss) {
		this.ss = ss;
	}
	
	@RequestMapping("/add")
	public ResultJson add(SupplierModel sm) throws Exception
	{
		ResultJson result=new ResultJson();
		ss.add(sm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/modify")
	public ResultJson modify(SupplierModel sm) throws Exception
	{
		ResultJson result=new ResultJson();
		ss.modify(sm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/delete")
	public ResultJson delete(SupplierModel sm) throws Exception
	{
		ResultJson result=new ResultJson();
		ss.delete(sm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public SupplierModel get(@RequestParam String no) throws Exception
	{
		return ss.getSupplier(no);
	}
	
	@RequestMapping("/get/all")
	public List<SupplierModel> getAll() throws Exception
	{
		return ss.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<SupplierModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return ss.getListByAllWithPage(rows, page);
	}
	
	
}
