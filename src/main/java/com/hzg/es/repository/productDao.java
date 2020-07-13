package com.hzg.es.repository;
import com.hzg.entity.product;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productDao extends ElasticsearchRepository<product, Integer> {

  
}
