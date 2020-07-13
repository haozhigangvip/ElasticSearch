package com.hzg.controller;

import com.hzg.entity.cpd_biological;
import com.hzg.service.ElkService;
import com.hzg.vo.ResultPage;
import com.hzg.vo.result;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

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
	    public ResultPage search(@RequestParam(value = "type", required = true) String type ,
	    								   @RequestParam(value = "key", required = true) String key ,
	    		  						   @RequestParam(value = "page", required = false) Integer page,
	    	 					     	   @RequestParam(value="pagesize", required = false) Integer pageSize,
	    								   @RequestParam(value="area", required = false) String area,
	    								   @RequestParam(value="fuzzy", required=false) Integer fuzzy
	    								   ){

	  		if(page==null){
	    		page=1;
	    	}
	    	if(pageSize==null){
	    		pageSize=30;
	    	}
	    	if(fuzzy==null){
	    		fuzzy=0;
	    	}
	    		
	  		AggregatedPage<Object> pg=elkService.findByKey(type, key, page-1, pageSize,area,fuzzy);
	  		ResultPage result=new ResultPage();
	  		result.setContent(pg.getContent());
	  		result.setNumberOfElements(pg.getNumberOfElements());
	  		result.setTotalElements(pg.getTotalElements());
	  		result.setPage(page);
	  		if( pageSize>0&& pg.getTotalElements()>0){
	  		result.setTotalPages((int)Math.ceil((double)pg.getTotalElements()/pageSize));
	  		}else
	  		{
	  			result.setTotalPages(0);
	  		}
	    	return result;
	    } 
	  	
	  	@RequestMapping("/elkRemoveByID")
	    @ResponseBody
	    public result remove(@RequestParam(value = "type", required = true) String type ,
	    								   @RequestParam(value = "id", required = true) String idx)  {

	  		result rs=elkService.removeByID(type,idx);
	    	return rs;
	    } 

}
