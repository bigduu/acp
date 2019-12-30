package com.bigduu.acp.repository.subject;

import com.bigduu.acp.common.CSRE.repository.BaseRepository;
import com.bigduu.acp.entity.subject.subsubject.MultipleChoiceSubject;

import java.util.Optional;

/**
 * @author mugeng.du
 */
public interface MultipleChoiceSubjectRepository extends BaseRepository<MultipleChoiceSubject> {
    Optional<MultipleChoiceSubject> findByQuestionLike(String question);
}
