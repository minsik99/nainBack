package io.paioneer.nain.interview.model.service;


import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.jpa.entity.QuestionEntity;
import io.paioneer.nain.interview.jpa.repository.InterviewRepository;
import io.paioneer.nain.interview.model.dto.InterviewDto;
import io.paioneer.nain.interview.model.dto.QuestionDto;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InterviewService {
    private final InterviewRepository interviewRepository;

    public Long insertInterview(InterviewDto interviewdto) {
        InterviewEntity interview = interviewRepository.save(interviewdto.toEntity());
        log.info("인터뷰 추가 {}", interview.toString());
        return interview.toDto().getItvNo();
    }

    public Page<InterviewDto> selectInterviewList(Long memberNo, Pageable pageable) {
        Page<InterviewEntity> interviewEntity = interviewRepository.findAllByMemberNo(memberNo, pageable);
        List<InterviewDto> interviewDto = interviewEntity.stream()
                .map(InterviewEntity::toDto)
                .collect(Collectors.toList());
        log.info("인터뷰 목록 {}", interviewDto.toString());
        return new PageImpl<>(interviewDto, pageable, interviewEntity.getTotalElements());
    }

    public void deleteInterview(Long itvNo) {
        log.info("Delete Interview : {}", itvNo);
        interviewRepository.deleteById(itvNo);
    }

    public ArrayList<QuestionDto> getRandomQuestion(String category) {
        log.info("질문시작");
        ArrayList<String> typeList = new ArrayList();
        typeList.add("자기소개");
        typeList.add("성격");
        typeList.add("지원동기");
        typeList.add(category); // 기술질문 4개
        typeList.add("경험"); // 2개
        typeList.add("포부");

        ArrayList<QuestionEntity> questions = interviewRepository.selectRanQuestion(typeList, category);
        ArrayList<QuestionDto> list = new ArrayList();
        for (QuestionEntity questionEntity : questions) {
            list.add(questionEntity.toDto());
            log.info("질문 입력 {}", questionEntity.toDto().toString());
        }

        log.info("질문 목록 {}", list.toString());

        return list;
    }


}
