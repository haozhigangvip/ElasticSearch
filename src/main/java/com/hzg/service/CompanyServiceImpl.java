package com.hzg.service;

import com.hzg.entity.Company_ELK;
import com.hzg.es.repository.Company_ELKDao;

import org.apache.commons.collections.map.HashedMap;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ContextConfiguration(locations = "classpath:ApplicationContext-Dao.xml")
public class CompanyServiceImpl implements CompanyService{
	private  Company_ELKDao compElkdao;
	
	//将xml中配置elasticsearch模板注解进来
	@Autowired
	private ElasticsearchTemplate template;
    
	@Override
    public List<Company_ELK> findByComIDandName(String key,Integer page,Integer pageNumic){
    	if(page==null){
    		page=0;
    	}
    	if(pageNumic==null){
    		pageNumic=20;
    	}
    	Map<String,Float> fieldmap=new HashMap<String, Float>();
    	fieldmap.put("comid", (float)1);
    	fieldmap.put("companyname", (float) 2);
    	
    	System.out.println(key);
    	QueryStringQueryBuilder qr=QueryBuilders.queryStringQuery(key)
    	//1.2 设置默认查询字段
    	.fields(fieldmap);
    	Pageable pg=PageRequest.of(page,pageNumic);
    	//2.1、创建一个NativeSearchQuery对象,并将查询条件放入其中
    	NativeSearchQuery query= new NativeSearchQueryBuilder().withQuery(qr)								
    			//2.2 设置分页，从第0页开始，每页10条记录
    			.withPageable(pg)
    			//2.3 设置成bulider
    			.build();
    			
    			//3、使用ElasticSearchTemplate对象执行查询,并将查询结果封装到UserBean对象中
    			List<Company_ELK> list=template.queryForList(query, Company_ELK.class);
    	
    			return list;
    }

    	
}
 