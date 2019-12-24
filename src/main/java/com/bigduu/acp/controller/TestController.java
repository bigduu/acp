package com.bigduu.acp.controller;

import com.bigduu.acp.common.controller.BaseController;
import com.bigduu.acp.entity.Test;
import com.bigduu.acp.service.TestService;
import org.springframework.web.bind.annotation.*;

/**
 * @author bigduu
 * @title: TestController
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2121:58
 */
@RestController
@RequestMapping("/test/subject")
public class TestController extends BaseController<Test> {
    
    private final TestService testService;
    
    private static Test allTest = null;
    
    public TestController(TestService testService) {
        super(testService);
        this.testService = testService;
    }
    
    
    @GetMapping("/subject/all")
    public Test getAllTest(){
        if (allTest == null){
            allTest = testService.getAllSubjectTest();
        }
        return allTest;
    }
    @GetMapping("/subject/default")
    public Test getDefaultTest(){
        return testService.getDefaultTest();
    }
    @GetMapping("/subject/single")
    public Test getSingleChoiceOnlyTest(){
        return testService.getSingleChoiceOnlyTest();
    }
    @GetMapping("/subject/multiple")
    public Test getMultipleChoiceOnlyTest(){
        return testService.getMultipleChoiceOnlyTest();
    }
    @GetMapping("/subject/judge")
    public Test getJudgeChoiceOnlyTest(){
        return testService.getJudgeChoiceOnlyTest();
    }
}
