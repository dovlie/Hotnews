package com.city.erp.controller.myq;

import java.util.List;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.city.erp.model.myq.DispatchModel;
import com.city.erp.model.myq.OrdersModel;
import com.city.erp.result.ListResultJson;
import com.city.erp.result.ResultJson;
import com.city.erp.service.myq.IOrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	private IOrdersService ips = null;
	
	@Autowired
	public void setIps(IOrdersService ips) {
		this.ips = ips;
	}

	@RequestMapping("/add")
	public ResultJson add(OrdersModel om) throws Exception
	{
		ResultJson result=new ResultJson();
		ips.add(om);
		result.setResult("Y");
		
		return result;
	}

	@RequestMapping("/modify")
	public ResultJson modify(OrdersModel om) throws Exception
	{
		ResultJson result=new ResultJson();
		ips.modify(om);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/delete")
	public ResultJson delete(OrdersModel pm) throws Exception
	{
		ResultJson result=new ResultJson();
		ips.delete(pm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get/all/pages")
	public ListResultJson getListWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
	{
		ListResultJson result=new ListResultJson();
		
		int count=ips.getCountByAll();
		int pageCount=ips.getPageCountByAll(rows);
		List list=ips.getListByAllWithPage(rows, page);
		
		result.setTotal(pageCount);
		result.setPage(page);
		//result.setPages(pageCount);
		result.setTotal(count);
		result.setRows(list);
		
		return result;
	}
	
	@RequestMapping(value="/get")
	public OrdersModel getEmployee(@RequestParam String oid) throws Exception
	{
		return ips.getOrders(oid);
		
	}
	
}
