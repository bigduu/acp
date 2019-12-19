package com.bigduu.acp.repository;

import com.bigduu.acp.entity.subject.SingleChoiceSubject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleChoiceSubjectRepository extends MongoRepository<SingleChoiceSubject,String> {
}
