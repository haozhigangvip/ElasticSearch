package com.hzg.service;

import com.hzg.entity.cpd_biological;
import com.hzg.entity.page_block;
import com.hzg.entity.page_detail;
import com.hzg.es.repository.cpd_biologicalDAO;
import com.hzg.es.repository.page_blockDao;
import com.hzg.es.repository.page_detailDao;
import com.hzg.vo.EsConstant;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.apache.commons.collections.map.HashedMap;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder.Field;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;


import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ContextConfiguration(locations = "classpath:ApplicationContext-Dao.xml")
public class ElkServiceImpl implements ElkService{
	@Autowired
	private  cpd_biologicalDAO cpdDao;
	@Autowired
	private  page_blockDao pgblockDao;
	@Autowired
	private  page_detailDao pgdetailDao;
	
	//将xml中配置elasticsearch模板注解进来
	@Autowired
	private ElasticsearchTemplate template;
	
	
	
	
	@Override
	public Integer removeByID(String type, String idx){
		Integer rs=0;
		if(type!=null && idx!=null)
		{
			try {
				switch (type) {
				case "cpd_biological":
					List<cpd_biological > ls=cpdDao.findBycpdid(idx);
					if(ls!=null){
						for (cpd_biological cpd : ls) {
							cpdDao.deleteById(cpd.getId());;
						}
					}
					break;
				case "page_block":
					pgblockDao.deleteById(Integer.valueOf(idx));
					break;
				case "page_detail":
					pgdetailDao.deleteById(Integer.valueOf(idx));

					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				rs=1;
			}
		
		}else
		{
			rs=1;
		}
		
		
		
		
		return rs;
	}

	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
    public AggregatedPage<Object> findByKey(String type,String key,Integer page,Integer pageNumic){
    	BoolQueryBuilder qb=null;
		NativeSearchQueryBuilder searchQuery= new NativeSearchQueryBuilder();

		AggregatedPage pages=null;
    	if(page==null){
    		page=0;
    	}
    	if(pageNumic==null){
    		pageNumic=20;
    	}
		searchQuery.withIndices(EsConstant.INDEX_NAME.CPDBIOLOGICAL);
	
		final List<String>   highlightFields = new ArrayList<String>();
		
		Class classx=null;
		

		
		
    	switch (type) {
		case "cpd_biological":
			qb= QueryBuilders.boolQuery()
	    	 .should(QueryBuilders.matchPhraseQuery("name",key).boost(4.0f))
	    	 .should(QueryBuilders.matchPhraseQuery("vitro",key).boost(3.0f))
	    	 .should(QueryBuilders.matchPhraseQuery("vivo",key).boost(2.0f))
	    	 .should(QueryBuilders.matchPhraseQuery("description",key).boost(1.0f))
	    	 .should(QueryBuilders.matchPhraseQuery("cpdid",key).boost(0.5f))
	    	 .should(QueryBuilders.matchPhraseQuery("synonym",key).boost(5.0f));
			
			highlightFields.add("cpdid");
			highlightFields.add("name");
			highlightFields.add("vitro");
			highlightFields.add("vivo");
			highlightFields.add("description");
			highlightFields.add("synonym");
			classx=cpd_biological.class;
			break;	
		case "page_block":
			qb= QueryBuilders.boolQuery()
	    	 .should(QueryBuilders.matchQuery("name",key).boost(5.0f))
	    	 .should(QueryBuilders.matchQuery("vitro",key).boost(4.0f))
	    	 .should(QueryBuilders.matchQuery("vivo",key).boost(3.0f))
	    	 .should(QueryBuilders.matchQuery("description",key).boost(2.0f))
	    	 .should(QueryBuilders.matchQuery("dte",key).boost(1.0f));
			
			highlightFields.add("name");
			highlightFields.add("vitro");
			highlightFields.add("vivo");
			highlightFields.add("description");
			highlightFields.add("dte");
			classx=page_block.class;	
			break;
		case "page_detail":
			qb= QueryBuilders.boolQuery()
	    	 .should(QueryBuilders.matchQuery("cnname",key).boost(4.0f))
	    	 .should(QueryBuilders.matchQuery("engname",key).boost(3.0f))
	    	 .should(QueryBuilders.matchQuery("cndsp",key).boost(2.0f))
	    	 .should(QueryBuilders.matchQuery("engdsp",key).boost(1.0f))
	    	 .should(QueryBuilders.matchQuery("content",key).boost(5.0f));
			
			highlightFields.add("cnname");
			highlightFields.add("engname");
			highlightFields.add("cndsp");
			highlightFields.add("engdsp");
			highlightFields.add("content");
			classx=page_detail.class;	
			break;
		}
	
    	
		Pageable pg=PageRequest.of(page,pageNumic);
	
		searchQuery.withPageable(pg)
                .withQuery(qb)
                .withSort(SortBuilders.scoreSort().order(SortOrder.DESC))
                .withHighlightFields(
				 new HighlightBuilder.Field("*").preTags(EsConstant.HIGH_LIGHT_START_TAG).postTags(EsConstant.HIGH_LIGHT_END_TAG));
		
				
		pages=template.queryForPage(searchQuery.build(),classx,new SearchResultMapper(){
			
		    @Override
			@SuppressWarnings("unchecked")
			public <T> AggregatedPageImpl<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<Map<String,Object>> list = new ArrayList<>();
				SearchHits hits = response.getHits();
				for (SearchHit searchHit : hits) {
					if (hits.getHits().length <= 0) {
						return null;
					}
					
					// 公共字段
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();

					// 反射调用set方法将高亮内容设置进去
					try {
						for (String field : highlightFields) {
							String keyFieldValue= (String) sourceAsMap.get(field);
							HighlightField highlightFieldValue =searchHit.getHighlightFields().get(field);
							
							if(highlightFieldValue == null) {
	                    		sourceAsMap.put(field, keyFieldValue);
	                    	}else {
	                    		String hcontent = highlightFieldValue.fragments()[0].toString();//取值
	                    		sourceAsMap.put(field, hcontent);
	                    	}

							
							
							
						}	
					}

				    catch (Exception e) {
						e.printStackTrace();
					}
					list.add(sourceAsMap);
				}

				if (list.size() > 0) {
					
                    return new AggregatedPageImpl<T>((List<T>) list);
				}
				return null;
			}
	 	
	 });

       
    			return pages;
    }
    

	
	
    
private static String parSetName(String fieldName) {
	
    if (null == fieldName || "".equals(fieldName)) {
        return null;
    }
    System.out.println("fn:"+fieldName);
    int startIndex = 0;
    if (fieldName.charAt(0) == '_')
        startIndex = 1;
    return "set" + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
            + fieldName.substring(startIndex + 1);
}
    	
}
 