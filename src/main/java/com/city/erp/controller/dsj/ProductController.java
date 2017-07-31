package com.city.erp.controller.dsj;

import java.util.List;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.city.erp.model.dsj.ProductModel;
import com.city.erp.model.sxy.ProductTypeModel;
import com.city.erp.model.sxy.WarehouseModel;
import com.city.erp.result.ListResultJson;
import com.city.erp.result.ResultJson;
import com.city.erp.service.dsj.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	private IProductService ips = null;
	
	@Autowired
	public void setIps(IProductService ips) {
		this.ips = ips;
	}


	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResultJson add(ProductModel pm,@RequestParam(value="uploadphoto", required=false) Part uploadphoto) throws Exception
	{

		ResultJson result=new ResultJson();
		if(uploadphoto!=null){

			String photoFileName=uploadphoto.getSubmittedFileName();
			String photoContentType=uploadphoto.getContentType();
			ips.add(pm, uploadphoto.getInputStream(), photoFileName, photoContentType);
		}
		else{
			ips.add(pm);
		}

		result.setResult("Y");		
		return result;
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public ResultJson modify(ProductModel pm,@RequestParam(value="uploadphoto", required=false) Part uploadphoto) throws Exception
	{
		ResultJson result=new ResultJson();
		if(uploadphoto!=null){
			String photoFileName=uploadphoto.getSubmittedFileName();
			String photoContentType=uploadphoto.getContentType();
			ips.modify(pm, uploadphoto.getInputStream(), photoFileName, photoContentType);
		}
		else{
			ips.modify(pm);
		}

		result.setResult("Y");		
		return result;
	}
	
	@RequestMapping("/delete")
	public ResultJson delete(ProductModel pm) throws Exception
	{
		ResultJson result=new ResultJson();
		ips.delete(pm);
		result.setResult("Y");
		
		return result;
	}
	
	@RequestMapping("/get")
	public ProductModel get(@RequestParam String pid) throws Exception
	{
		return ips.getProduct(pid);
	}
	
	@RequestMapping("/get/all")
	public List<ProductModel> getAll() throws Exception
	{
		return ips.getListByAll();
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
		result.setPages(pageCount);
		//result.setTotal(count);
		result.setRows(list);
		return result;
	}
	
	@RequestMapping(value="/point/price")
	public List<ProductModel> getProductByPrice(@RequestParam String pid) throws Exception
	{
		return ips.getListByPricePoint(pid);
		
	}
	
	@RequestMapping(value="/point/origin")
	public List<ProductModel> getProductByOrigin(@RequestParam String oid) throws Exception
	{
		return ips.getListByOriginPoint(oid);
		
	}
	
	@RequestMapping(value="/point/promotion")
	public List<ProductModel> getProductByPromotion(@RequestParam String prid) throws Exception
	{
		return ips.getListByPromotionPoint(prid);
		
	}
	@RequestMapping(value="/point/producttype")
	public List<ProductTypeModel> getProductByProductType(@RequestParam String ptid) throws Exception
	{
		return ips.getListByProductTypePoint(ptid);
		
	}
	@RequestMapping(value="/point/warehouse")
	public List<WarehouseModel> getProductByWarehouse(@RequestParam String wid) throws Exception
	{
		return ips.getListByWarehousePoint(wid);
		
	}
	
	//检查产品ID是否存在
	@RequestMapping("/checkexist")
	public ResultJson checkExist(@RequestParam String pid) throws Exception
	{
		ResultJson result=new ResultJson();
		if(ips.checkExist(pid)){
			result.setResult("Y");
		}
		else{
			result.setResult("N");
		}
		return result;
	}
	
}
