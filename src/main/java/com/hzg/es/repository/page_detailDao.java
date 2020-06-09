package com.hzg.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.hzg.entity.page_detail;

@Repository
public interface page_detailDao extends ElasticsearchRepository<page_detail, Integer> {

    


}
