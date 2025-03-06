package com.bluetoya.taradiddle.common.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Slf4j
@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.pool-min-size}")
    private int poolMinSize;

    @Value("${spring.data.mongodb.pool-max-size}")
    private int poolMaxSize;

    @Value("${spring.data.mongodb.connection-time-out}")
    private int connectionTimeOut;

    @Value("${spring.data.mongodb.max-wait-time}")
    private int maxWaitTime;

    @Value("${spring.data.mongodb.read-time-out}")
    private int readTimeOut;

    @Bean
    public MongoClient mongoClient(@Value("${spring.data.mongodb.uri}") String uri) {
        return MongoClients.create(MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(uri))
            .applyToConnectionPoolSettings(builder ->
                builder.maxSize(poolMaxSize)
                    .minSize(poolMinSize)
                    .maxWaitTime(maxWaitTime, TimeUnit.SECONDS))
            .applyToSocketSettings(builder ->
                builder.connectTimeout(connectionTimeOut, TimeUnit.SECONDS)
                    .readTimeout(readTimeOut, TimeUnit.SECONDS))  // 읽기 타임아웃
            .build());
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, database);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }

}
