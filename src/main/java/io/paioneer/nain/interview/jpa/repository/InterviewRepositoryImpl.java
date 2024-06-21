package io.paioneer.nain.interview.jpa.repository;

import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class InterviewRepositoryImpl implements InterviewRepositoryCustom {



    @Override
    public List<InterviewEntity> selectInterviewList() {
        return List.of();
    }

    @Override
    public List<InterviewEntity> selectScoreList(Long memberNo) {
        return List.of();
    }
}
