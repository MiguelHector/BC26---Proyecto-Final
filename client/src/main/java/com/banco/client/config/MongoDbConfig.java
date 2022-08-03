package com.banco.client.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Arrays;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.banco.client.repository")
public class MongoDbConfig extends AbstractReactiveMongoConfiguration{
    @Value("${mongodb.hostname}")
    private String hostname;

    @Value("${mongodb.dbport}")
    private String port;
     
    @Value("${mongodb.dbname}")
    private String dbName;
 
    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress(getHostname(), Integer.parseInt(getPort())))))
                        .build());
    }
 
    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    protected String getHostname() {
        return hostname;
    }

    protected String getPort() {
        return port;
    }
 
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }
}