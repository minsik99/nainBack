package io.paioneer.nain.interview.model.service;


import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.jpa.repository.InterviewRepository;
import io.paioneer.nain.interview.model.dto.InterviewDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InterviewService {
    private final InterviewRepository interviewRepository;

    public void insertInterview(Long memberNo) {
        InterviewEntity interviewEntity = new InterviewEntity();
        interviewEntity.setMemberNo(memberNo);
        interviewRepository.save(interviewEntity);
    }

//    public List<InterviewDto> selectInterviewList() {
//        return interviewRepository.findAll().toDto();
//
//    }


}
