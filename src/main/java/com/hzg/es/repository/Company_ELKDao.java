package com.hzg.es.repository;
import com.hzg.entity.Company_ELK;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface Company_ELKDao extends ElasticsearchRepository<Company_ELK, Long> {

    
	List<Company_ELKDao> findByComIDandCompanyName(String comID,String CompanyName,Pageable pg);


}
