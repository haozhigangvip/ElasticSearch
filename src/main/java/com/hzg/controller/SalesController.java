package com.hzg.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzg.entity.Company;
import com.hzg.entity.SalesMergeHistory;
import com.hzg.service.CompanyService;
import com.hzg.service.ContactService;
import com.hzg.service.SalesService;
import com.hzg.vo.SalesStatistics;

@Controller
public class SalesController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private SalesService salesService;
	
	@RequestMapping("/geCompanyList")
    @ResponseBody
    private List<Company> getCompanyList(@RequestParam String key){
		System.out.println(key);
		List<Company> list=null;
		if(key!=null){
			list=companyService.findCompanyByLikeNameOrID(key);
			for (Company company : list) {
				System.out.println(company.toString());
			}
		}
		
		return list;
	}
	@RequestMapping("/getContactSalesByCompanyId")
    @ResponseBody
    private List<SalesStatistics> getContactSalesByCompanyId(@RequestParam String comID){
		List<SalesStatistics> list=null;
		if(comID!=null){
			System.out.println(comID);
			list=salesService.getContactSales(comID);

		}
		return list;
	}
	
	@RequestMapping("/updateSales")
    @ResponseBody
    private String getContactSalesByCompanyId(@RequestBody SalesMergeHistory sales){
		SalesMergeHistory resultSales=null;
		int code=0;
		if(sales!=null){
			
			resultSales=salesService.updateSales(sales);
			if(resultSales==null){
				code=1;
			}
		}
		
		return "{\"code\":"+code+"}";
	}
}
