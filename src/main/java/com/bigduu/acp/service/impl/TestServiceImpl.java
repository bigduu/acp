package com.bigduu.acp.service.impl;

import com.bigduu.acp.entity.Test;
import com.bigduu.acp.service.SubjectService;
import com.bigduu.acp.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bigduu
 * @title: TestServiceImpl
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2119:40
 */
@Service
public class TestServiceImpl implements TestService {
    
    private final SubjectService subjectService;
    
    public TestServiceImpl(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    
    @Override
    public Test getOneTest() {
        
        return null;
    }
}
