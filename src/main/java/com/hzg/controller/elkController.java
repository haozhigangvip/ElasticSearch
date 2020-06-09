package com.hzg.controller;

import com.hzg.entity.cpd_biological;
import com.hzg.service.ElkService;
import com.hzg.vo.result;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class elkController {
		@Autowired
		private ElkService elkService;
	  	@RequestMapping("/elkSearchBykey")
	    @ResponseBody
	    public AggregatedPage<Object> search(@RequestParam(value = "type", required = true) String type ,
	    								   @RequestParam(value = "key", required = true) String key ,
	    		  						   @RequestParam(value = "page", required = false) Integer page,
	    	 					     	   @RequestParam(value="pageSize", required = false) Integer pageTotal)  {

	  		AggregatedPage<Object> result=elkService.findByKey(type, key, page, pageTotal);
	    	return result;
	    } 
	  	
	  	@RequestMapping("/elkRemoveByID")
	    @ResponseBody
	    public result remove(@RequestParam(value = "type", required = true) String type ,
	    								   @RequestParam(value = "id", required = true) String idx)  {

	    	Integer code=elkService.removeByID(type,idx);
	    	result rs=new result();
	    	rs.setCode(code);
	    	return rs;
	    } 

}
