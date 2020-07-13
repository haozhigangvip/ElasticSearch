package com.hzg.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import com.hzg.entity.cpd_biological;
import com.hzg.vo.ResultPage;
import com.hzg.vo.result;


public interface ElkService {
    public  <T> AggregatedPage<T>  findByKey(String type,String key,Integer page,Integer pageNumic,String area,Integer fuzzy);

	public result removeByID(String type, String idx);



}
