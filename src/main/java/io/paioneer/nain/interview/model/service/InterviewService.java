package io.paioneer.nain.interview.model.service;


import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.jpa.repository.InterviewRepository;
import io.paioneer.nain.interview.model.dto.InterviewDto;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
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
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberNo(memberNo);
        interviewEntity.setMemberEntity(memberEntity);
        InterviewEntity interview = interviewRepository.save(interviewEntity);
        return interview.toDto().getItvNo();
    }

    public Page<InterviewDto> selectInterviewList(Long memberNo, Pageable pageable) {
        Page<InterviewEntity> interviewEntity = interviewRepository.findAllByMemberNo(memberNo, pageable);
        Page<InterviewDto> interviewDto = interviewEntity.map(InterviewDto::new);
        return interviewDto;
    }

    public void deleteInterview(Long itvNo) {
        log.info("Delete Interview : {}", itvNo);
        interviewRepository.deleteById(itvNo);
    }

//    public List<InterviewDto> selectInterviewList() {
//        return interviewRepository.findAll().toDto();
//
//    }


}
