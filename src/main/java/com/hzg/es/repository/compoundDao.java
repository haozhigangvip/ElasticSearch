package com.hzg.es.repository;
import com.hzg.entity.compound;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface compoundDao extends ElasticsearchRepository<compound, Integer> {

    
	List<compound> findBycpdid(String cpdid);


}
