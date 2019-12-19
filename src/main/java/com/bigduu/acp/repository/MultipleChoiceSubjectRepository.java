package com.bigduu.acp.repository;

import com.bigduu.acp.entity.subject.MultipleChoiceSubject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultipleChoiceSubjectRepository extends MongoRepository<MultipleChoiceSubject,String> {
}
