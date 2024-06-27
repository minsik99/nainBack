package io.paioneer.nain.interview.jpa.repository;


import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.jpa.entity.QQuestionEntity;
import io.paioneer.nain.interview.jpa.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface InterviewRepositoryCustom {

    ArrayList<QuestionEntity> selectRanQuestion(ArrayList typeList);
}
