package com.bigduu.acp.repository.subject;

import com.bigduu.acp.common.repository.BaseRepository;
import com.bigduu.acp.entity.subject.subsubject.SingleChoiceSubject;

import java.util.Optional;

/**
 * @author mugeng.du
 */
public interface SingleChoiceSubjectRepository extends BaseRepository<SingleChoiceSubject> {
    Optional<SingleChoiceSubject> findByQuestionLike(String question);
}
