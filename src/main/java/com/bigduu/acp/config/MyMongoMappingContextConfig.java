package com.bigduu.acp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * @author mugeng.du
 */
@Configuration
public class MyMongoMappingContextConfig extends MongoMappingContext {
    @Override
    public void setAutoIndexCreation(boolean autoCreateIndexes) {
        super.setAutoIndexCreation(true);
    }
}
