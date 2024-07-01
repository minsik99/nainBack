package io.paioneer.nain.interview.jpa.repository;


import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.jpa.entity.QuestionEntity;

import java.util.ArrayList;

public interface InterviewRepositoryCustom {

    ArrayList<QuestionEntity> selectRanQuestion(ArrayList typeList, String category);

    InterviewEntity findByItvNo(Long itvNo);

    Double getVoiceScore(Long itvNo);
}
