package io.paioneer.nain.interview.model.service;


import io.paioneer.nain.interview.jpa.entity.AnalysisEntity;
import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.jpa.entity.QuestionEntity;
import io.paioneer.nain.interview.jpa.repository.InterviewRepository;
import io.paioneer.nain.interview.model.dto.InterviewDto;
import io.paioneer.nain.interview.model.dto.QuestionDto;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.model.service.MemberService;
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
    private final MemberService memberService;

    public Long insertInterview(InterviewDto interviewdto) {
        InterviewEntity interview = interviewRepository.save(interviewdto.toEntity());
        log.info("인터뷰 추가 {}", interview.toString());
        return interview.toDto().getItvNo();
    }

    public Page<InterviewDto> selectInterviewList(Long memberNo, Pageable pageable) {
        Page<InterviewEntity> interviewEntity = interviewRepository.findAllByMemberNo(memberNo, pageable);
        log.info("인터뷰 목록{}", interviewEntity.toString());
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


    public InterviewDto selectInterview(Long itvNo) {
         InterviewEntity interviewEntity = interviewRepository.findByItvNo(itvNo);
         log.info("itvNo 문제 {}",interviewEntity.toString());
         InterviewDto interviewDto = interviewEntity.toDto();
        return interviewDto;
    }

    public void updateVideoScore(InterviewDto interviewDto) {
        interviewRepository.save(interviewDto.toEntity());
    }

    public String getFinalAnalysis(int score, String percentile, String emotionAnalysis, int successRate) {

        return String.format("총점수는 %d 점으로 %s 에 속합니다. 시선및 감정분석결과 %s 이후 면접에서 합격할 확률은 %d%% 로 예상됩니다.",
                score, percentile, emotionAnalysis, successRate);
    }

    public String getPercentile(int score) {
        int totalScores = interviewRepository.countTotalScores();
        int countLowerScores = interviewRepository.countScoresLowerThan(score);
        int countHigherScores = interviewRepository.countScoresHigherThan(score);

        if (totalScores == 0) {
            return "총 점수가 없습니다.";
        }

        if(score < 70) {
            double lowerPercentile = ((double) countHigherScores / totalScores) * 100;
            return String.format("하위 %.2f%%", lowerPercentile);
        } else {
            double percentile = ((double) countLowerScores / totalScores) * 100;
            return String.format("상위 %.2f%%", percentile);
        }
    }

    public int getSuccess(int score) {
        if (score < 60) {
            return 50;
        } else if (score < 70) {
            return 60;
        } else if (score < 80) {
            return 70;
        } else if (score < 90) {
            return 80;
        } else {
            return 90;
        }
    }

    public String getTotalAnalysis(int vscore, int score) {
        if (score <= vscore) {
            if (vscore < 60) {
                return "기준치 미만으로";
            } else if (vscore < 70) {
                return "평균치 미만으로";
            } else if (vscore < 80) {
                return "평균치로";
            } else {
                return "평균치 이상으로";
            }
        } else {
            if (vscore < 60) {
                return "기준치 미만이나";
            } else if (vscore < 70) {
                return "평균치 미만으";
            } else if (vscore < 80) {
                return "평균치로";
            } else {
                return "평균치 이상으";
            }
        }

    }
}
