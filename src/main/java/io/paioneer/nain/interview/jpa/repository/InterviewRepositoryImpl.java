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
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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
            Long count = queryFactory
                    .select(questionEntity.count())
                    .from(questionEntity)
                    .where(questionEntity.qType.eq(String.valueOf(type)))
                    .fetchOne();

            LinkedHashSet<Integer> numSet = new LinkedHashSet<>();
            while(numSet.size() < 5){
                int num = (int) ((Math.random() * count) + 1);
                numSet.add(num);
            }
            ArrayList numList = new ArrayList(numSet);

            if(String.valueOf(type).equals(category)) {
                for (int i = 0; i < 4; i++) {
                    questions.add(queryFactory
                            .selectFrom(questionEntity)
                            .where(questionEntity.qType.eq((String) type)
                                    .and(Expressions.booleanTemplate("ROWNUM = {0}", numList.get(i))))
                            .fetchOne());
                }
            }else if(String.valueOf(type).equals("경험")){
                    for(int i = 0; i < 2; i++){
                        questions.add(queryFactory
                                .selectFrom(questionEntity)
                                .where(questionEntity.qType.eq((String) type)
                                        .and(Expressions.booleanTemplate("ROWNUM = {0}", numList.get(i))))
                                .fetchOne());
                    }
            }else {
                questions.add(queryFactory
                        .selectFrom(questionEntity)
                        .where(questionEntity.qType.eq((String) type)
                                .and(Expressions.booleanTemplate("ROWNUM = {0}", numList.get(1))))
                        .fetchOne());
            }
        }
        return questions;
    }
}
