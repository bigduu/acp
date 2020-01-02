package com.bigduu.acp.controller;

import com.bigduu.acp.entity.subject.Subject;
import com.bigduu.acp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mugeng.du
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;
    
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    
    /**
     * @param subject
     * @param <T>
     * TODO 增加评论功能
     * @return
     */
    @PatchMapping
    public <T extends Subject> Subject comment(T subject){
        return null;
    }
}
