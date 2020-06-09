package com.hzg.es.repository;
import com.hzg.entity.page_block;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface page_blockDao extends ElasticsearchRepository<page_block, Integer> {

    


}
