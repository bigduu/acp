package com.bigduu.acp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * mongo Data JPA 不在推荐自动创建索引 所以需要手动开启 但是这里似乎不行 还需要查看文档
 * @author mugeng.du
 */
@Configuration
public class MyMongoMappingContextConfig extends MongoMappingContext {
    @Override
    public void setAutoIndexCreation(boolean autoCreateIndexes) {
        super.setAutoIndexCreation(true);
    }
}
