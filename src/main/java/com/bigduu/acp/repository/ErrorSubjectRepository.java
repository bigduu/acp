package com.bigduu.acp.repository;

import com.bigduu.acp.entity.subject.subsubject.ErrorSubject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author mugeng.du
 */
public interface ErrorSubjectRepository extends MongoRepository<ErrorSubject,String> {
    /**
     * 用于查找某个用户的全部错题
     * @param username username
     * @return 返回错题集
     */
    List<ErrorSubject> findAllByUsername(String username);
}
