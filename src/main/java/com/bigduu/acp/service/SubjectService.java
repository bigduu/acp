package com.bigduu.acp.service;

import com.bigduu.acp.entity.subject.Subject;
import com.bigduu.acp.entity.subject.subsubject.SubjectType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bigduu
 * @title: SubjectService
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2119:40
 */
@Service
public interface SubjectService {
    
    /**
     * 获取一种类型的所有题
     * @param subjectType 题的类型
     * @return 该类型的题
     */
    List<? extends Subject> getAll(SubjectType subjectType) throws Exception;
    
    /**
     * 获取随机题目 指定数目
     *
     * @param subjectType 获取题目的类型
     * @param number 获取题目的数量
     * @exception Exception 数据不足
     * @return 返回改数量的该类型的题目
     */
    List<? extends Subject> getRandomTypeOfSubject(SubjectType subjectType,Integer number) throws Exception;
    
    <T extends Subject> T comment(T subject);
    
}
