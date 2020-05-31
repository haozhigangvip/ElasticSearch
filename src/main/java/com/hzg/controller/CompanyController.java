package com.hzg.controller;

import com.hzg.entity.Company_ELK;
import com.hzg.service.CompanyService;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CompanyController {
		@Autowired
		private CompanyService customerService;
	
	  	@RequestMapping("/searchCompany")
	    @ResponseBody
	    public List<Company_ELK> search(@RequestParam String key )  {
	    	List<Company_ELK> list=customerService.findByComIDandName(key, 0,30);
	    	for (Company_ELK company_ELK : list) {
				System.out.println(company_ELK.toString());
			}
	    	return list;
	    } 
	

}
