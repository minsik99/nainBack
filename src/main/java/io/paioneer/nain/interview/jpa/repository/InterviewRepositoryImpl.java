package io.paioneer.nain.interview.jpa.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.jpa.entity.QInterviewEntity;
import io.paioneer.nain.interview.jpa.entity.QQuestionEntity;
import io.paioneer.nain.interview.jpa.entity.QuestionEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InterviewRepositoryImpl implements InterviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;
    private QInterviewEntity interviewEntity = QInterviewEntity.interviewEntity;
    private QQuestionEntity questionEntity = QQuestionEntity.questionEntity;

    @Override
    public QuestionEntity selectRanQuestion(String type) {
        return null;
    }


}
