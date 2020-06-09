package com.hzg.es.repository;
import com.hzg.entity.cpd_biological;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface cpd_biologicalDAO extends ElasticsearchRepository<cpd_biological, Integer> {

    
	List<cpd_biological> findBycpdid(String cpdid);


}
