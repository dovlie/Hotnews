package com.city.erp.controller.dsj;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.city.erp.model.dsj.PublicityModel;
import com.city.erp.result.ListResultJson;
import com.city.erp.result.ResultJson;
import com.city.erp.service.dsj.IPublicityService;

@RestController
@RequestMapping("/publicity")
public class PublicityController {
	
	private IPublicityService ips = null;

	@Autowired
	public void setIps(IPublicityService ips) {
		this.ips = ips;
	}
	
	@RequestMapping("/add")
	public ResultJson add(PublicityModel pm) throws Exception
	{
		ResultJson result=new ResultJson();
		ips.add(pm);
		result.setResult("Y");
		
		return result;
	}

	@RequestMapping("/modify")
	public ResultJson modify(PublicityModel pm) throws Exception
	{
		ResultJson result=new ResultJson();
		ips.modify(pm);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/delete")
	public ResultJson delete(PublicityModel pm) throws Exception
	{
		ResultJson result=new ResultJson();
		ips.delete(pm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public PublicityModel get(@RequestParam String puid) throws Exception
	{
		return ips.getPublicity(puid);
	}
	
	@RequestMapping("/get/all")
	public List<PublicityModel> getAll() throws Exception
	{
		return ips.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<PublicityModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return ips.getListByAllWithPage(rows, page);
	}
	
	@RequestMapping("/get/all/pages")
	public ListResultJson getListWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
	{
		ListResultJson result=new ListResultJson();
		
		int count=ips.getCountByAll();
		int pageCount=ips.getPageCountByAll(rows);
		List list=ips.getListByAllWithPage(rows, page);

		result.setPages(pageCount);
		result.setTotal(pageCount);
		//result.setTotal(count);
		result.setPage(page);
		result.setRows(list);
		
		return result;
	}
}
