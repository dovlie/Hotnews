package com.city.erp.controller.dsj;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.city.erp.model.zqy.AdminidtratorModel;
import com.city.erp.result.ResultJson;
import com.city.erp.service.zqy.AdminidtratorService;

@RestController
@RequestMapping("/")
public class LoginController {

	private AdminidtratorService as = null;

	@Autowired
	public void setAs(AdminidtratorService as) {
		this.as = as;
	}

	@RequestMapping("/login")
	public ResultJson login(HttpSession session,String uid,String pw,String yz) throws Exception
	{
		ResultJson result=new ResultJson();
		if(as.validate(uid, pw)){
			
			AdminidtratorModel am =as.getAdminidtrator(uid);
			if(session.getAttribute("authCode").equals(yz.toLowerCase())){
				
	            session.setAttribute("user", am);  
	            session.setMaxInactiveInterval(1800); 
				result.setResult("Y");
				return result;
			}else{
				result.setResult("O");
				return result;
			}
		}
		else{
			
			result.setResult("N");
			return result;
		}
	
	}

}
