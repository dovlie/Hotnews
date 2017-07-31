package com.city.erp.controller.sxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.city.erp.model.sxy.ProductTypeModel;
import com.city.erp.result.ResultJson;
import com.city.erp.service.sxy.ProductTypeService;


@RestController
@RequestMapping("/producttype")
public class ProductTypeController {
private ProductTypeService pts=null;
	
	
	@Autowired
	public void setPts(ProductTypeService pts) {
	this.pts = pts;
}

	
	@RequestMapping("/add")
	public ResultJson add(ProductTypeModel ptm) throws Exception
	{
		ResultJson result=new ResultJson();
		pts.add(ptm);
		result.setResult("Y");
		
		return result;
	}

	@RequestMapping("/modify")
	public ResultJson modify(ProductTypeModel ptm) throws Exception
	{
		ResultJson result=new ResultJson();
		pts.modify(ptm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/delete")
	public ResultJson delete(ProductTypeModel ptm) throws Exception
	{
		ResultJson result=new ResultJson();
		pts.delete(ptm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public ProductTypeModel get(@RequestParam String no) throws Exception
	{
		return pts.getProductType(no);
	}
	
	@RequestMapping("/get/all")
	public List<ProductTypeModel> getAll() throws Exception
	{
		return pts.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<ProductTypeModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return pts.getListByAllWithPage(rows, page);
	}
	
}
