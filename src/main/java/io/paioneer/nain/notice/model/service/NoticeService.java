package io.paioneer.nain.notice.model.service;

import com.querydsl.core.types.OrderSpecifier;
import io.paioneer.nain.notice.jpa.entity.NoticeEntity;
import io.paioneer.nain.notice.jpa.repository.NoticeRepository;
import io.paioneer.nain.notice.model.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;


    //게시글 전체 갯수 조회
    public long countNotice() { return noticeRepository.count(); }


    //전체 목록 출력
    public ArrayList<NoticeDto> selectNoticeList(Pageable pageable) {
        Page<NoticeEntity> entities = noticeRepository.findByNoticeDeleteIsNull(pageable);
        ArrayList<NoticeDto> list = new ArrayList<>();
        for (NoticeEntity entity : entities) {
            list.add(entity.toDto());
        }
        return list;
    }



    public NoticeDto getNotice(Long noticeNo) {
        return noticeRepository.findById(noticeNo).get().toDto();
    }

    // 글상세보기
    public NoticeDto selectNoticeOne(Long noticeNo) {
        NoticeDto noticeDto = noticeRepository.findById(noticeNo).get().toDto();
        noticeDto.setNoticeReadCount(noticeDto.getNoticeReadCount() + 1);
        noticeRepository.save(noticeDto.toEntity());
        return noticeDto;
    }

    //등록
    public Long insertNotice(NoticeDto noticeDto) {

        if(noticeRepository.findLastNo() == null) {
            noticeDto.setNoticeNo(1L);
        }else{
            noticeDto.setNoticeNo(noticeRepository.findLastNo() + 1);
        }
        noticeDto.setNoticeReadCount(0L);
        noticeRepository.save(noticeDto.toEntity());
        return noticeDto.getNoticeNo();
    }

    //수정
    public void updateNotice(NoticeDto noticeDto) {
        noticeRepository.save(noticeDto.toEntity());
    }

    //삭제(숨김)
    public void hiddenNotice(NoticeDto noticeDto) {
        noticeRepository.save(noticeDto.toEntity());
    }

    //삭제
    public void deleteNotice(Long noticeNo) {
        noticeRepository.deleteById(noticeNo);
    }


//    //검색
//    public Map<String, Object> selectSearchNotice(String keyword, Pageable pageable, String type) {
//        List<NoticeDto> result;
//        switch (type) {
//            case "noticeTitle":
//                result = noticeRepository.findBySearchNoticeTitle(keyword, pageable)
//                        .stream()
//                        .map(NoticeEntity::toDto)
//                        .collect(Collectors.toList());
//                break;
//            case "noticeContent":
//                result = noticeRepository.findBySearchNoticeContent(keyword, pageable)
//                        .stream()
//                        .map(NoticeEntity::toDto)
//                        .collect(Collectors.toList());
//                break;
//            case "noticeWriter":
//                result = noticeRepository.findBySearchNoticeWriter(keyword, pageable)
//                        .stream()
//                        .map(NoticeEntity::toDto)
//                        .collect(Collectors.toList());
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid search type: " + type);
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("data", result);
//        response.put("page", pageable.getPageNumber() + 1);
//        response.put("limit", pageable.getPageSize());
//
//        return response;
//    }

    //검색 목록 조회
    public ArrayList<NoticeDto> selectSearchNotice(String type, String keyword, Pageable pageable, OrderSpecifier entityPath) {
        ArrayList<NoticeEntity> entities;
        if (type.equals("noticeContent")) {
            entities = noticeRepository.findBySearchNoticeContent(keyword, pageable, entityPath);
            log.info("keyword : {}, pageable : {}, entityPath : {}", keyword, pageable.toString(), entityPath.toString());
        } else if (type.equals("noticeWriter")) {
            entities = noticeRepository.findBySearchNoticeWriter(keyword, pageable, entityPath);
        } else {
            entities = noticeRepository.findBySearchNoticeTitle(keyword, pageable, entityPath);
        }
        ArrayList<NoticeDto> list = new ArrayList<>();
        for (NoticeEntity noticeEntity : entities) {
            list.add(noticeEntity.toDto());
        }
        return list;
    }

    //검색 목록 개수 조회
    public long countSearchNotice(String type, String keyword, Pageable pageable) {
        long count;
        if (type.equals("noticeTitle")) {
            log.info("service");
            count = noticeRepository.countByNoticeTitle(keyword, pageable);
        } else if (type.equals("writer")) {
            count = noticeRepository.countByNoticeWriter(keyword, pageable);
        } else {
            count = noticeRepository.countByNoticeContent(keyword, pageable);
        }
        return count;
    }



}

