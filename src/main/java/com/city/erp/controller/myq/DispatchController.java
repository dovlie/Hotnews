package com.city.erp.controller.myq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.city.erp.model.myq.CustomerModel;
import com.city.erp.model.myq.DispatchModel;
import com.city.erp.result.ListResultJson;
import com.city.erp.result.ResultJson;
import com.city.erp.service.myq.ICustomerService;
import com.city.erp.service.myq.IDispatchService;

@RestController
@RequestMapping("/dispatch")
public class DispatchController {
	
	private IDispatchService cs = null;
	
	@Autowired
	public void setCs(IDispatchService cs) {
		this.cs = cs;
	}
	
	@RequestMapping("/add")
	public ResultJson add(DispatchModel cm) throws Exception
	{
		ResultJson result=new ResultJson();
		cs.add(cm);
		result.setResult("Y");
		
		return result;
	}

	@RequestMapping("/modify")
	public ResultJson modify(DispatchModel cm) throws Exception
	{
		ResultJson result=new ResultJson();
		cs.modify(cm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/delete")
	public ResultJson delete(DispatchModel cm) throws Exception
	{
		ResultJson result=new ResultJson();
		cs.delete(cm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public DispatchModel get(@RequestParam String did) throws Exception
	{
		return cs.getDispatch(did);
	}
	
	@RequestMapping("/get/all")
	public List<DispatchModel> getAll() throws Exception
	{
		return cs.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<DispatchModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return cs.getListByAllWithPage(rows, page);
	}
	
	@RequestMapping("/get/all/pages")
	public ListResultJson getListWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
	{
		ListResultJson result=new ListResultJson();
		
		int count=cs.getCountByAll();
		int pageCount=cs.getPageCountByAll(rows);
		List list=cs.getListByAllWithPage(rows, page);

		result.setPages(pageCount);
		result.setTotal(pageCount);
		//result.setTotal(count);
		result.setPage(page);
		result.setRows(list);
		
		return result;
	}
}
