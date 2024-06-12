package io.paioneer.nain.interview.jpa.repository;


import io.paioneer.nain.interview.jpa.entity.InterviewEntity;

import java.util.List;

public interface InterviewRepositoryCustom {

    List<InterviewEntity> selectInterviewList();
    //유저의 인터뷰 점수 평균
    List<InterviewEntity> selectScoreList(Long memberNo);





}
