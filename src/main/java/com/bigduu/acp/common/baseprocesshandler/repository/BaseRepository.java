package com.bigduu.acp.common.baseprocesshandler.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author mugeng.du
 */
@NoRepositoryBean
public interface BaseRepository<T> extends MongoRepository<T,String> {

}
