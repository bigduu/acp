package com.bigduu.acp.service;


import com.bigduu.acp.entity.subject.subsubject.ErrorSubject;

import java.util.List;

/**
 * @author mugeng.du
 */
public interface ErrorSubjectService {
    /**
     * 添加一个错题
     * @param errorSubject 错题
     * @return 保存后的错题
     */
    ErrorSubject addOne(ErrorSubject errorSubject);
    
    /**
     * 通过一个用户查找所有错题
     * @param username 用户名
     * @return 错题集
     */
    List<ErrorSubject> findAllByUsername(String username);
    
    
    /**
     * 一次性保存所有错题
     * @param errorSubjects 错题集
     * @return 保存后的错题集
     */
    List<ErrorSubject> addAll(List<ErrorSubject> errorSubjects);
    
    
}
