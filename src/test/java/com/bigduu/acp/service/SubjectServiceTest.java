package com.bigduu.acp.service;

import com.bigduu.acp.entity.subject.Subject;
import com.bigduu.acp.entity.subject.SubjectType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bigduu
 * @title: SubjectServiceTest
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2120:40
 */
@SpringBootTest
class SubjectServiceTest {
    
    @Autowired
    private SubjectService subjectService;
    
    @Test
    void getAll() throws Exception {
        List<? extends Subject> all = subjectService.getAll(SubjectType.SINGLE_CHOICE);
        System.out.println(all);
    }
    
    @Test
    void getRandomTypeOfSubject() throws Exception {
        List<? extends Subject> randomTypeOfSubject = subjectService.getRandomTypeOfSubject(SubjectType.SINGLE_CHOICE, 20);
        System.out.println(randomTypeOfSubject);
    }
}