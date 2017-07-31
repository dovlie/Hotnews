package com.city.erp.controller.myq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.city.erp.model.myq.CustomerModel;
import com.city.erp.result.ListResultJson;
import com.city.erp.result.ResultJson;
import com.city.erp.service.myq.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private ICustomerService cs = null;
	
	@Autowired
	public void setCs(ICustomerService cs) {
		this.cs = cs;
	}
	
	@RequestMapping("/add")
	public ResultJson add(CustomerModel cm) throws Exception
	{
		ResultJson result=new ResultJson();
		cs.add(cm);
		result.setResult("Y");
		
		return result;
	}

	@RequestMapping("/modify")
	public ResultJson modify(CustomerModel cm) throws Exception
	{
		ResultJson result=new ResultJson();
		cs.modify(cm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/delete")
	public ResultJson delete(CustomerModel cm) throws Exception
	{
		ResultJson result=new ResultJson();
		cs.delete(cm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public CustomerModel get(@RequestParam String cid) throws Exception
	{
		return cs.getCustomer(cid);
	}
	
	@RequestMapping("/get/all")
	public List<CustomerModel> getAll() throws Exception
	{
		return cs.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<CustomerModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
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
