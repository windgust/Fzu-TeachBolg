package com.fzuTeachBolg.ask.service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.fzuTeachBolg.ask.web.model.Message;

/**
 * 
 * @author admin
 *
 */
public interface UKeFuRepository extends ElasticsearchRepository<Message, String> , UKeFuEsCommonRepository {
	
}
