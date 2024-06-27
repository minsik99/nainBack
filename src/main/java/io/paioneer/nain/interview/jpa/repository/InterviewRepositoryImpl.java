package io.paioneer.nain.interview.jpa.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.jpa.entity.QInterviewEntity;
import io.paioneer.nain.interview.jpa.entity.QQuestionEntity;
import io.paioneer.nain.interview.jpa.entity.QuestionEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InterviewRepositoryImpl implements InterviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;
    private QInterviewEntity interviewEntity = QInterviewEntity.interviewEntity;
    private QQuestionEntity questionEntity = QQuestionEntity.questionEntity;

    @Override
    public ArrayList<QuestionEntity> selectRanQuestion(ArrayList typeList, String category) {

        ArrayList<QuestionEntity> questions = new ArrayList<>();

        for(Object type : typeList) {
            if(String.valueOf(type).equals(category)) {
                List<QuestionEntity> list = queryFactory
                        .selectFrom(questionEntity)
                        .where(questionEntity.qType.eq((String) type))
                        .fetch();
                Collections.shuffle(list);
                for(int i = 0; i < 4; i++) {
                    questions.add(list.get(i));
                }

            }else if(String.valueOf(type).equals("경험")){
                List<QuestionEntity> list = queryFactory
                        .selectFrom(questionEntity)
                        .where(questionEntity.qType.eq((String) type))
                        .fetch();
                Collections.shuffle(list);
                for(int i = 0; i < 2; i++) {
                    questions.add(list.get(i));
                }
            }else {
                List<QuestionEntity> list = queryFactory
                        .selectFrom(questionEntity)
                        .where(questionEntity.qType.eq((String) type))
                        .fetch();
                Collections.shuffle(list);
                questions.add(list.get(0));
            }
        }
        return questions;
    }
}
