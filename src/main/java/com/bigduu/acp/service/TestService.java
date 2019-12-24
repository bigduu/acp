package com.bigduu.acp.service;

import com.bigduu.acp.common.service.BaseService;
import com.bigduu.acp.entity.Test;
import com.bigduu.acp.entity.User;

import java.util.List;

/**
 * @author bigduu
 * @title: TestService
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2119:40
 */
public interface TestService extends BaseService<Test> {
    
    List<Test> getAllByUser(User user) throws Exception;
    
    Test getAllSubjectTest();
    Test getDefaultTest();
    Test getSingleChoiceOnlyTest();
    Test getMultipleChoiceOnlyTest();
    Test getJudgeChoiceOnlyTest();
}
