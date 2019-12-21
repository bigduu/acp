package com.bigduu.acp.repository;

import com.bigduu.acp.entity.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author bigduu
 * @title: TestRepository
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2121:26
 */
public interface TestRepository extends MongoRepository<Test,String> {
    List<Test> findAllByUserId(String userId);
}
