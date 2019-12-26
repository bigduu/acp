package com.bigduu.acp.repository;

import com.bigduu.acp.common.repository.BaseRepository;
import com.bigduu.acp.entity.subject.subsubject.SingleChoiceSubject;

import java.util.Optional;

public interface SingleChoiceSubjectRepository extends BaseRepository<SingleChoiceSubject> {
    Optional<SingleChoiceSubject> findByQuestionLike(String question);
}
