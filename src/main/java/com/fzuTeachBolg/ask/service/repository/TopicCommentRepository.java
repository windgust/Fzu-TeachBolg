package com.fzuTeachBolg.ask.service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.fzuTeachBolg.ask.web.model.TopicComment;

public interface TopicCommentRepository extends  ElasticsearchRepository<TopicComment, String> , TopicCommentEsCommonRepository {
}
