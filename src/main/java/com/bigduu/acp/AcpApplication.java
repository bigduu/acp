package com.bigduu.acp;

import com.bigduu.acp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;

/**
 * @author mugeng.du
 */
@SpringBootApplication
@EnableMongoAuditing
public class AcpApplication {
    
    private final MongoTemplate mongoTemplate;
    
    private final MongoMappingContext mongoMappingContext;
    
    public AcpApplication(MongoTemplate mongoTemplate, MongoMappingContext mongoMappingContext) {
        this.mongoTemplate = mongoTemplate;
        this.mongoMappingContext = mongoMappingContext;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(AcpApplication.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void initIndicesAfterStartup(){
        IndexOperations indexOps = mongoTemplate.indexOps(User.class);
        MongoPersistentEntityIndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
        resolver.resolveIndexFor(User.class).forEach(indexOps::ensureIndex);
    }
    
}
