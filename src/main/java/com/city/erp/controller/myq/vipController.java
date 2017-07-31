package com.city.erp.controller.myq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.city.erp.model.dsj.PriceModel;
import com.city.erp.model.myq.vipModel;
import com.city.erp.model.sxy.SupplierModel;
import com.city.erp.result.ResultJson;
import com.city.erp.service.myq.vipService;

//会员控制类
@RestController
@RequestMapping("/vip")
public class vipController {
	
	private vipService ds=null;
	
	@Autowired
	public void setDs(vipService ds) {
		this.ds = ds;
	}

	@RequestMapping("/add")
	public ResultJson add(vipModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.add(dm);
		result.setResult("Y");
		
		return result;
	}

	@RequestMapping("/modify")
	public ResultJson modify(vipModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.modify(dm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/delete")
	public ResultJson delete(vipModel dm) throws Exception
	{
		ResultJson result=new ResultJson();
		ds.delete(dm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/get")
	public vipModel got(@RequestParam int no) throws Exception
	{
		return ds.getVip(no);
	}
	
	@RequestMapping("/get/all")
	public List<vipModel> getAll() throws Exception
	{
		return ds.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<vipModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return ds.getListByAllWithPage(rows, page);
	}
	

}