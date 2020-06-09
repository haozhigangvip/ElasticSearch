package com.hzg.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import com.hzg.entity.cpd_biological;


public interface ElkService {
    public  AggregatedPage<Object>  findByKey(String type,String key,Integer page,Integer pageNumic);

	public Integer removeByID(String type, String idx);



}
