package com.fzuTeachBolg.ask.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import com.fzuTeachBolg.ask.web.model.Topic;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicEsCommonRepository {
	public Page<Topic> getTopicByCate(String cate ,String q, int p, int ps) ;
	
	public Page<Topic> getTopicByCateAndUser(String cate , String q ,String user , int p, int ps) ;
	
	public Page<Topic> getTopicByCateAndRela(String cate , String field, int p, int ps) ;
	
	public Page<Topic> findByCon(NativeSearchQueryBuilder searchQueryBuilder, String q, int p,int ps);
	
	public Page<Topic> countByCon(NativeSearchQueryBuilder searchQueryBuilder , String field, String q, int p,int ps);
}
