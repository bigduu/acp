package com.bigduu.acp.service;

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
public interface TestService {
    Test save(Test test);
    void delete(Test test);
    List<Test> getAllByUser(User user) throws Exception;
    
    Test getAllSubjectTest();
    Test getOneDefaultTest();
    Test getSingleChoiceOnlyTest();
    Test getMultipleChoiceOnlyTest();
    Test getJudgeChoiceOnlyTest();
}
