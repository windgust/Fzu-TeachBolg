package com.fzuTeachBolg.ask.service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.fzuTeachBolg.ask.web.model.Topic;

public interface TopicRepository extends  ElasticsearchRepository<Topic, String> , TopicEsCommonRepository {
	
}
