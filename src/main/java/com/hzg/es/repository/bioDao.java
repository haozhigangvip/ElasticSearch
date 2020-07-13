package com.hzg.es.repository;
import com.hzg.entity.bio;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bioDao extends ElasticsearchRepository<bio, Integer> {

  
}
