package com.azanola.test.first;

import com.mongodb.client.MongoClient;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonMongoConfig {
	@Value("${spring.data.mongodb.database}")
	private String databaseName;

	@Bean
	public MongoTemplate defaultMongoTemplate(MongoClient mongoClient) {
		return DefaultMongoTemplate.builder()
				.mongoDatabase(mongoClient, databaseName)
				.build();
	}

	@Bean
	public TokenStore myCustomTokenStore(Serializer serializer, MongoTemplate mongoTemplate) {
		return MongoTokenStore.builder()
				.mongoTemplate(mongoTemplate)
				.serializer(serializer)
				.build();
	}

	@Bean
	public MongoSagaStore myCustomSagaStore(Serializer serializer, MongoTemplate mongoTemplate) {
		return MongoSagaStore.builder()
				.mongoTemplate(mongoTemplate)
				.serializer(serializer)
				.build();
	}

	@Bean
	public MongoEventStorageEngine eventStorageEngine(MongoTemplate mongoTemplate) {
		return MongoEventStorageEngine.builder()
				.mongoTemplate(mongoTemplate)
				.build();
	}
}
