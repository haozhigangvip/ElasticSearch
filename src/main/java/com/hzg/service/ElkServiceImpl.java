package com.hzg.service;

import com.hzg.entity.bio;
import com.hzg.entity.compound;
import com.hzg.entity.cpd_biological;
import com.hzg.entity.page_block;
import com.hzg.entity.page_detail;
import com.hzg.entity.product;
import com.hzg.es.repository.bioDao;
import com.hzg.es.repository.compoundDao;
import com.hzg.es.repository.cpd_biologicalDAO;
import com.hzg.es.repository.page_blockDao;
import com.hzg.es.repository.page_detailDao;
import com.hzg.es.repository.productDao;
import com.hzg.vo.EsConstant;
import com.hzg.vo.result;

import org.apache.lucene.queries.function.FunctionScoreQuery;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import java.util.ArrayList;
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
	@Autowired
	private  productDao productdao;
	@Autowired
	private  bioDao biodao;
	@Autowired
	private  compoundDao compDao;
	
	//将xml中配置elasticsearch模板注解进来
	@Autowired
	private ElasticsearchTemplate template;
	
	
	
	
	@Override
	public result removeByID(String type, String idx){
		result  rs=new result();
		rs.setCode(0);
		rs.setMessage("删除成功");
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
				case "product":
					productdao.deleteById(Integer.valueOf(idx));

					break;
				case "bio":
					biodao.deleteById(Integer.valueOf(idx));
					break;
				case "compound":
					List<compound > lst=compDao.findBycpdid(idx);
					if(lst!=null){
						for (compound comp : lst) {
							compDao.deleteById(comp.getId());;
						}
					}
					break;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				rs.setCode(1);
				rs.setMessage(e.getMessage());
			}
		
		}else
		{
			rs.setCode(1);
			rs.setMessage("无效的索引库");
		}
		
		
		
		
		return rs;
	}

	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
    public AggregatedPage<Object> findByKey(String type,String key,Integer page,Integer pageNumic,String area,Integer fuzzy){
    	BoolQueryBuilder qb=null;
		NativeSearchQueryBuilder searchQuery= new NativeSearchQueryBuilder();

		AggregatedPage pages=null;
    	
		searchQuery.withIndices(EsConstant.INDEX_NAME.CPDBIOLOGICAL);
		QueryBuilder  queryBuilder=null;
		final List<String>   highlightFields = new ArrayList<String>();
		
		Class classx=null;
		

		
		
    	switch (type) {
		case "cpd_biological":
			qb= QueryBuilders.boolQuery()
	    	 .should(QueryBuilders.matchPhraseQuery("name",key).boost(4.0f))
	    	 .should(QueryBuilders.matchPhraseQuery("vitro",key).boost(3.0f))
	    	 .should(QueryBuilders.matchPhraseQuery("vivo",key).boost(2.0f))
	    	 .should(QueryBuilders.matchPhraseQuery("description",key).boost(1.0f))
	    	 .should(QueryBuilders.matchPhraseQuery("synonym",key).boost(5.0f));
			
			highlightFields.add("name");
			highlightFields.add("vitro");
			highlightFields.add("vivo");
			highlightFields.add("description");
			highlightFields.add("synonym");
			classx=cpd_biological.class;
			break;
		case "bio":
			qb= QueryBuilders.boolQuery()
	    	 .should(QueryBuilders.matchPhraseQuery("target",key).boost(4.0f));
	    	
			
			highlightFields.add("target");
			highlightFields.add("urid");
			highlightFields.add("ttid");
			
			classx=bio.class;
			break;	
		case "page_block":
			if(area!=null && area.trim().equals("china")){
				if(fuzzy==0){
				qb= QueryBuilders.boolQuery()
				    	 .should(QueryBuilders.matchPhraseQuery("cnname",key).boost(5.0f))
				    	 .should(QueryBuilders.matchPhraseQuery("engname",key).boost(4.0f))
				    	 .should(QueryBuilders.matchPhraseQuery("cndsp",key).boost(3.0f))
				    	 .should(QueryBuilders.matchPhraseQuery("engdsp",key).boost(2.0f));
				}else{
					qb= QueryBuilders.boolQuery()
					    	 .should(QueryBuilders.matchQuery("cnname",key).boost(5.0f))
					    	 .should(QueryBuilders.matchQuery("engname",key).boost(4.0f))
					    	 .should(QueryBuilders.matchQuery("cndsp",key).boost(3.0f))
					    	 .should(QueryBuilders.matchQuery("engdsp",key).boost(2.0f));
				}
						highlightFields.add("cnname");
						highlightFields.add("engname");
						highlightFields.add("cndsp");
						highlightFields.add("engdsp");
			}else{
				if(fuzzy==0){
					qb= QueryBuilders.boolQuery()
					    	 .should(QueryBuilders.matchPhraseQuery("engname",key).boost(4.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("engdsp",key).boost(2.0f));
				}else{
					qb= QueryBuilders.boolQuery()
					    	 .should(QueryBuilders.matchQuery("engname",key).boost(4.0f))
					    	 .should(QueryBuilders.matchQuery("engdsp",key).boost(2.0f));
				}
						highlightFields.add("engname");
						highlightFields.add("engdsp");
			}
			
			classx=page_block.class;	
			break;
		case "page_detail":
			if(area!=null && area.trim().equals("china")){
				if(fuzzy==0){
					qb= QueryBuilders.boolQuery()
					    	 .should(QueryBuilders.matchPhraseQuery("dtext",key).boost(5.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("cnname",key).boost(4.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("engname",key).boost(3.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("cndsp",key).boost(2.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("engdsp",key).boost(1.0f));
				}else{
					qb= QueryBuilders.boolQuery()
					    	 .should(QueryBuilders.matchQuery("dtext",key).boost(5.0f))
					    	 .should(QueryBuilders.matchQuery("cnname",key).boost(4.0f))
					    	 .should(QueryBuilders.matchQuery("engname",key).boost(3.0f))
					    	 .should(QueryBuilders.matchQuery("cndsp",key).boost(2.0f))
					    	 .should(QueryBuilders.matchQuery("engdsp",key).boost(1.0f));
				}
						
						highlightFields.add("dtext");
						highlightFields.add("cnname");
						highlightFields.add("engname");
						highlightFields.add("cndsp");
						highlightFields.add("engdsp");
			}else{
				if(fuzzy==0){
					qb= QueryBuilders.boolQuery()
					    	 .should(QueryBuilders.matchPhraseQuery("dtext",key).boost(5.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("engname",key).boost(3.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("engdsp",key).boost(1.0f));
				}else{
					qb= QueryBuilders.boolQuery()
					    	 .should(QueryBuilders.matchQuery("dtext",key).boost(5.0f))
					    	 .should(QueryBuilders.matchQuery("engname",key).boost(3.0f))
					    	 .should(QueryBuilders.matchQuery("engdsp",key).boost(1.0f));
				}
						highlightFields.add("dtext");
						highlightFields.add("engname");
						highlightFields.add("engdsp");
			}
			classx=page_detail.class;	
			break;
		case "compound":
			queryBuilder=null;
			if(area!=null && area.trim().equals("china")){
				if(fuzzy==0){
					qb= QueryBuilders.boolQuery()
							.must(QueryBuilders.termQuery("onshelfus",true))
							.must(QueryBuilders.boolQuery()
							 .should(QueryBuilders.matchPhraseQuery("name",key).boost(60.0f))
						     .should(QueryBuilders.matchPhraseQuery("synonyms",key).boost(30.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("synonymseng",key).boost(20.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("description",key).boost(1.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("targets",key).boost(100.0f)));	
							 
				}else{
					qb= QueryBuilders.boolQuery()
							.must(QueryBuilders.termQuery("onshelfus",true))
							.must(QueryBuilders.boolQuery()
							 .should(QueryBuilders.matchQuery("name",key).boost(60.0f))
						     .should(QueryBuilders.matchQuery("synonyms",key).boost(30.0f))
					    	 .should(QueryBuilders.matchQuery("synonymseng",key).boost(20.0f))
					    	 .should(QueryBuilders.matchQuery("description",key).boost(1.0f))
					    	 .should(QueryBuilders.matchQuery("targets",key).boost(100.0f)));	
				}
						
				
				highlightFields.add("name");
				highlightFields.add("synonyms");
				highlightFields.add("synonymseng");	
				highlightFields.add("description");
				highlightFields.add("targets");
			}else{
				if(fuzzy==0){
					qb=QueryBuilders.boolQuery()
							.must(QueryBuilders.termQuery("onshelfus",true))
							.must(QueryBuilders.boolQuery()
							 .should(QueryBuilders.matchPhraseQuery("name",key).boost(60.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("synonymseng",key).boost(30.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("description",key).boost(20.0f))
					    	 .should(QueryBuilders.matchPhraseQuery("targets",key).boost(100.0f)));
				}else{
					qb=QueryBuilders.boolQuery()
							.must(QueryBuilders.termQuery("onshelfus",true))
							.must(QueryBuilders.boolQuery()
							 .should(QueryBuilders.matchQuery("name",key).boost(60.0f))
					    	 .should(QueryBuilders.matchQuery("synonymseng",key).boost(30.0f))
					    	 .should(QueryBuilders.matchQuery("description",key).boost(20.0f))
					    	 .should(QueryBuilders.matchQuery("targets",key).boost(100.0f)));

				}
				highlightFields.add("name");
				highlightFields.add("synonymseng");
				highlightFields.add("description");
				highlightFields.add("targets");
			}
			classx=compound.class;	
			break;	
		case "product":		
			if(area!=null && area.trim().equals("china")){
				if(fuzzy==0){
					qb= QueryBuilders.boolQuery()
							.must(QueryBuilders.termQuery("onshelfcn",true))
							.must(QueryBuilders.boolQuery()
							.should(QueryBuilders.matchPhraseQuery("cnname",key).boost(5.0f))
							.should(QueryBuilders.matchPhraseQuery("engname",key).boost(4.0f)));
				}else{
					qb= QueryBuilders.boolQuery()
							.must(QueryBuilders.termQuery("onshelfcn",true))
							.must(QueryBuilders.boolQuery()
							.should(QueryBuilders.matchQuery("cnname",key).boost(5.0f))
							.should(QueryBuilders.matchQuery("engname",key).boost(4.0f)));
				}
								
				highlightFields.add("cnname");
				highlightFields.add("engname");
						
			}else{
				if(fuzzy==0){
					qb=QueryBuilders.boolQuery()
							.must(QueryBuilders.termQuery("onshelfus",true))
							.must(QueryBuilders.boolQuery()
							.should(QueryBuilders.matchPhraseQuery("engname",key).boost(4.0f)));
				}else{
					qb=QueryBuilders.boolQuery()
							.must(QueryBuilders.termQuery("onshelfus",true))
							.must(QueryBuilders.boolQuery()
							.should(QueryBuilders.matchQuery("engname",key).boost(4.0f)));
				}

				highlightFields.add("engname");
			}
			classx=product.class;	
			break;	
		}	
	
    	
		Pageable pg=PageRequest.of(page,pageNumic);
	
		searchQuery.withPageable(pg)
                .withQuery(QueryBuilders.functionScoreQuery(qb).boostMode(CombineFunction.SUM))
                .withSort(SortBuilders.fieldSort("hot").order(SortOrder.DESC))
                .withSort(SortBuilders.scoreSort().order(SortOrder.DESC))
              
                .withHighlightFields(
				 new HighlightBuilder.Field("*").requireFieldMatch(false).preTags(EsConstant.HIGH_LIGHT_START_TAG).postTags(EsConstant.HIGH_LIGHT_END_TAG).fragmentSize(80000));
	
		
				
		pages=template.queryForPage(searchQuery.build(),classx,new SearchResultMapper(){
			
		    @Override
			@SuppressWarnings("unchecked")
			public <T> AggregatedPageImpl<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<Map<String,Object>> list = new ArrayList<>();
				SearchHits hits = response.getHits();
				
				for (SearchHit searchHit : hits) {
				
					Map<String,Object> mp=searchHit.getSourceAsMap();
				
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
					
                    return new AggregatedPageImpl<T>((List<T>) list,pageable,response.getHits().getTotalHits());
				}else{
					return new AggregatedPageImpl<T>( new ArrayList<T>());
				}
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
 