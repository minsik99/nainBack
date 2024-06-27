package io.paioneer.nain.notice.model.service;

import io.paioneer.nain.notice.jpa.entity.NoticeEntity;
import io.paioneer.nain.notice.jpa.repository.NoticeRepository;
import io.paioneer.nain.notice.jpa.repository.NoticeRepositoryImpl;
import io.paioneer.nain.notice.model.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    private final NoticeRepositoryImpl noticeRepositoryImpl;

    //전체 목록 출력
    public ArrayList<NoticeDto> selectNoticeList(Pageable pageable) {
        Page<NoticeEntity> entities = noticeRepository.findAll(pageable);
        ArrayList<NoticeDto> list = new ArrayList<>();
        for (NoticeEntity entity : entities) {
            list.add(entity.toDto());
        }
        return list;
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

    //삭제
    public void deleteNotice(Long noticeNo) {
        noticeRepository.deleteById(noticeNo);
    }


    //제목 검색
    public List<NoticeDto> selectSearchNoticeTitle(String keyword, Pageable pageable) {

        ArrayList<NoticeEntity> lists = noticeRepository.findBySearchNoticeTitle(keyword, pageable);
        ArrayList<NoticeDto> list = new ArrayList<>();
        for (NoticeEntity entity : lists) {
            list.add(entity.toDto());
        }
        return list;
    }

    //내용 검색
    public List<NoticeDto> selectSearchNoticeContent(String keyword, Pageable pageable) {

        ArrayList<NoticeEntity> lists = noticeRepository.findBySearchNoticeContent(keyword, pageable);
        ArrayList<NoticeDto> list = new ArrayList<>();

        for (NoticeEntity entity : lists) {
            list.add(entity.toDto());
        }

        return list;
    }

    //작성자 검색
    public List<NoticeDto> selectSearchNoticeWriter(String keyword, Pageable pageable) {

        ArrayList<NoticeEntity> lists = noticeRepository.findBySearchNoticeWriter(keyword, pageable);
        ArrayList<NoticeDto> list = new ArrayList<>();

        for (NoticeEntity entity : lists) {
            list.add(entity.toDto());
        }
        return list;
    }

    //제목 검색 목록 갯수 조회용
    public Long getSearchNoticeTitleCount(String keyword)  {
        return noticeRepository.countByNoticeTitle(keyword);
    }

    //내용 검색 목록 갯수 조회용
    public Long getSearchNoticeContentCount(String keyword) {
        return noticeRepository.countByNoticeContent(keyword);
    }

    //작성자 검색 목록 갯수 조회용
    public Long getSearchNoticeWriterCount(String keyword, Pageable pageable) {
        return noticeRepository.countByNoticeWriter(keyword, pageable);
    }
    
    //게시글 전체 갯수 조회
    public long countNotice() { return noticeRepository.count(); }
}

