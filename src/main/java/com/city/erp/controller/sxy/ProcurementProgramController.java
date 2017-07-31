package com.city.erp.controller.sxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.city.erp.model.sxy.ProcurementProgramModel;
import com.city.erp.model.sxy.ProductTypeModel;
import com.city.erp.result.ResultJson;
import com.city.erp.service.sxy.ProcurementProgramService;
import com.city.erp.service.sxy.ProductTypeService;

@RestController
@RequestMapping("/procurementprogram")
public class ProcurementProgramController {
private ProcurementProgramService pps=null;
	
	@Autowired
	public void setPps(ProcurementProgramService pps) {
		this.pps = pps;
	}
	
	@RequestMapping("/add")
	public ResultJson add(ProcurementProgramModel ppm) throws Exception
	{
		ResultJson result=new ResultJson();
		pps.add(ppm);
		result.setResult("Y");
		
		return result;
	}
	
	

	@RequestMapping("/modify")
	public ResultJson modify(ProcurementProgramModel ppm) throws Exception
	{
		ResultJson result=new ResultJson();
		pps.modify(ppm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/delete")
	public ResultJson delete(ProcurementProgramModel ppm) throws Exception
	{
		ResultJson result=new ResultJson();
		pps.delete(ppm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public ProcurementProgramModel get(@RequestParam String no) throws Exception
	{
		return pps.getProcurementProgram(no);
	}
	
	@RequestMapping("/get/all")
	public List<ProcurementProgramModel> getAll() throws Exception
	{
		return pps.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<ProcurementProgramModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return pps.getListByAllWithPage(rows, page);
	}
	
}
