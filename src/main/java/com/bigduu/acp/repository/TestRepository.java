package com.bigduu.acp.repository;

import com.bigduu.acp.common.CSRE.repository.BaseRepository;
import com.bigduu.acp.entity.Test;

import java.util.List;

/**
 * @author bigduu
 * @title: TestRepository
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2121:26
 */
public interface TestRepository extends BaseRepository<Test> {
    List<Test> findAllByUserId(String userId);
}
