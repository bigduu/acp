package com.bigduu.acp.repository;

import com.bigduu.acp.entity.subject.JudgeSubject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JudgeSubjectRepository extends MongoRepository<JudgeSubject,String> {
}
