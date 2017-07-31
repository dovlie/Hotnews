package com.city.erp.controller.dsj;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.city.erp.model.dsj.OriginModel;
import com.city.erp.result.ListResultJson;
import com.city.erp.result.ResultJson;
import com.city.erp.service.dsj.IOriginService;

@RestController
@RequestMapping("/origin")
public class OriginController {

	private IOriginService os = null;

	@Autowired
	public void setOs(IOriginService os) {
		this.os = os;
	}
	
	@RequestMapping("/add")
	public ResultJson add(OriginModel om) throws Exception
	{
		ResultJson result=new ResultJson();
		os.add(om);
		result.setResult("Y");
		
		return result;
	}

	@RequestMapping("/modify")
	public ResultJson modify(OriginModel om) throws Exception
	{
		ResultJson result=new ResultJson();
		os.modify(om);
		result.setResult("Y");
		
		return result;
	}
	@RequestMapping("/delete")
	public ResultJson delete(OriginModel om) throws Exception
	{
		ResultJson result=new ResultJson();
		os.delete(om);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public OriginModel get(@RequestParam String oid) throws Exception
	{
		return os.getOrigin(oid);
	}
	
	@RequestMapping("/get/all")
	public List<OriginModel> getAll() throws Exception
	{
		return os.getListByAll();
	}
	
	@RequestMapping("/get/allwithpage")
	public List<OriginModel> getAllWithPage(@RequestParam int rows,@RequestParam int page) throws Exception
	{
		return os.getListByAllWithPage(rows, page);
	}
	
	@RequestMapping("/get/all/pages")
	public ListResultJson getListWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
	{
		ListResultJson result=new ListResultJson();
		
		int count=os.getCountByAll();
		int pageCount=os.getPageCountByAll(rows);
		List list=os.getListByAllWithPage(rows, page);

		result.setPages(pageCount);
		result.setTotal(pageCount);
		//result.setTotal(count);
		result.setPage(page);
		result.setRows(list);
		
		return result;
	}
	
}
