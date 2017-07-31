package com.city.erp.controller.dsj;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.city.erp.model.dsj.PriceModel;
import com.city.erp.result.ResultJson;
import com.city.erp.service.dsj.IPriceService;


@RestController
@RequestMapping("/price")
public class PriceController {

	private IPriceService ps=null;
	
	@Autowired
	public void setPs(IPriceService ps) {
		this.ps = ps;
	}

	@RequestMapping("/add")
	public ResultJson add(PriceModel pm) throws Exception
	{
		ResultJson result=new ResultJson();
		ps.add(pm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/modify")
	public ResultJson modify(PriceModel pm) throws Exception
	{
		ResultJson result=new ResultJson();
		ps.modify(pm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/delete")
	public ResultJson delete(PriceModel pm) throws Exception
	{
		ResultJson result=new ResultJson();
		ps.delete(pm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public PriceModel get(@RequestParam String pid) throws Exception
	{
		return ps.getPrice(pid);
	}
	
	@RequestMapping("/get/all")
	public List<PriceModel> getAll() throws Exception
	{
		return ps.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<PriceModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return ps.getListByAllWithPage(rows, page);
	}
	
	

}
