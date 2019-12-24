package com.bigduu.acp.repository;

import com.bigduu.acp.common.repository.BaseRepository;
import com.bigduu.acp.entity.subject.subSubject.MultipleChoiceSubject;

import java.util.Optional;

public interface MultipleChoiceSubjectRepository extends BaseRepository<MultipleChoiceSubject> {
    Optional<MultipleChoiceSubject> findByQuestionLike(String question);
}
