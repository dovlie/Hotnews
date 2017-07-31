package com.city.erp.controller.sxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.city.erp.model.sxy.WarehouseModel;
import com.city.erp.result.ResultJson;
import com.city.erp.service.sxy.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
private WarehouseService ws=null;
	
	@Autowired
	public void setWs(WarehouseService ws) {
		this.ws = ws;
	}
	
	@RequestMapping("/add")
	public ResultJson add(WarehouseModel wm) throws Exception
	{
		ResultJson result=new ResultJson();
		ws.add(wm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/modify")
	public ResultJson modify(WarehouseModel wm) throws Exception
	{
		ResultJson result=new ResultJson();
		ws.modify(wm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/delete")
	public ResultJson delete(WarehouseModel wm) throws Exception
	{
		ResultJson result=new ResultJson();
		ws.delete(wm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public WarehouseModel get(@RequestParam String no) throws Exception
	{
		return ws.getWarehouse(no);
	}
	
	@RequestMapping("/get/all")
	public List<WarehouseModel> getAll() throws Exception
	{
		return ws.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List< WarehouseModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return ws.getListByAllWithPage(rows, page);
	}
}
