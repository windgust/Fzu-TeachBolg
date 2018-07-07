package com.fzuTeachBolg.ask.service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.fzuTeachBolg.ask.web.model.TopicView;

public interface TopicViewRepository extends  ElasticsearchRepository<TopicView, String> , TopicViewEsCommonRepository {
}
