package com.ukefu.ask.config.elastic;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.ukefu.ask")
@ComponentScan(basePackages = { "com.ukefu.ask" })
public class ElasticSearchConfig {
	@Value("${spring.data.elasticsearch.properties.host}")
	private String elastichost;
	@Value("${spring.data.elasticsearch.properties.port}")
	private String elasticport;
	@Value("${spring.data.elasticsearch.cluster-name}")
	private String clustername;
	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String clusternodes;
	@Value("${spring.data.elasticsearch.local}")
	private String local;
	@Value("${spring.data.elasticsearch.repositories.enabled}")
	private String enable;
	
	@Bean
	public NodeBuilder nodeBuilder() {
	    return new NodeBuilder();
	}

	@Bean
	public Client client() {
	    Settings settings = ImmutableSettings.settingsBuilder()
	    		.put("cluster.name", clustername)
	            .put("client.transport.ignore_cluster_name", false)
	            .put("cluster.nodes", clusternodes)
	            .put("local",local)
	            .put("repositories.enabled",enable)
	            .build();
	    TransportClient client = new TransportClient(settings);
		TransportAddress adress = new InetSocketTransportAddress(elastichost, Integer.valueOf(elasticport));
	    client.addTransportAddress(adress);
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
	    return new ElasticsearchTemplate(client());
	}
	
}
