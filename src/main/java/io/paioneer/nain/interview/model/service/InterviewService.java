package io.paioneer.nain.interview.model.service;


import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.jpa.repository.InterviewRepository;
import io.paioneer.nain.interview.model.dto.InterviewDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InterviewService {
    private final InterviewRepository interviewRepository;

    public Long insertInterview(Long memberNo) {
        InterviewEntity interviewEntity = new InterviewEntity();
        interviewEntity.getMember().setMemberNo(memberNo);
        InterviewEntity interview = interviewRepository.save(interviewEntity);
        return interview.toDto().getItvNo();
    }

    public Page<InterviewDto> selectInterviewList(Long memberNo, Pageable pageable) {
        Page<InterviewEntity> interviewEntity = interviewRepository.findAllByMemberNo(memberNo, pageable);
        Page<InterviewDto> interviewDto = interviewEntity.map(InterviewDto::new);
        return interviewDto;
    }

//    public List<InterviewDto> selectInterviewList() {
//        return interviewRepository.findAll().toDto();
//
//    }


}
