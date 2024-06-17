package io.paioneer.nain.community.model.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import io.paioneer.nain.community.jpa.entity.CommunityEntity;
import io.paioneer.nain.community.jpa.repository.community.CommunityRepository;
import io.paioneer.nain.community.jpa.repository.community.CommunityRepositoryImpl;
import io.paioneer.nain.community.model.dto.CommunityDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepositoryImpl communityRepositoryImpl;
    private final CommunityRepository communityRepository;



    //글 전체목록
    public ArrayList<CommunityDto> selectList(Pageable pageable, OrderSpecifier entityPath) {
        ArrayList<CommunityEntity> entities = communityRepository.findListAll(pageable, entityPath);
        ArrayList<CommunityDto> list = new ArrayList<>();
        for (CommunityEntity entity : entities) {
            list.add(entity.toDto());
        }
        return list;
    }

    //글 상세보기
    public CommunityDto selectOne(Long communityNo) {
        CommunityDto communityDto = communityRepository.findById(communityNo).get().toDto();
        communityDto.setReadCount(communityDto.getReadCount() + 1);
        communityRepository.save(communityDto.toEntity());
        return communityDto;
    }

    //등록
    public void insertCommunity(CommunityDto communityDto) {
        communityDto.setCommunityNo(communityRepository.findLastNo() + 1);
        communityDto.setReadCount(String.valueOf(0));
        communityRepository.save(communityDto.toEntity());
    }

    //수정
    public void updateCommunity(CommunityDto communityDto) {
        CommunityEntity communityEntity = communityRepository.findById(communityDto.getCommunityNo()).get();
        communityEntity.setTitle(communityDto.getTitle());
        communityEntity.setContent(communityDto.getContent());
        communityEntity.setCommunityDate(communityDto.getCommunityDate());
        communityRepository.save(communityEntity);
    }

    //삭제값 추가
    public void deleteCommunity(CommunityDto communityDto) {
        communityRepository.save(communityDto.toEntity());
    }

    //글 DB 삭제
    public void terminateCommunity(Long communityNo) {
        communityRepository.deleteById(communityNo);
    }

    //검색 목록 조회
    public ArrayList<CommunityDto> selectSearchList(String type, String keyword, Pageable pageable, OrderSpecifier entityPath) {
        ArrayList<CommunityEntity> entities;
        if (type.equals("title")) {
            entities = communityRepository.searchTitle(keyword, pageable, entityPath);
        } else if (type.equals("writer")) {
            entities = communityRepository.searchWriter(keyword, pageable, entityPath);
        } else {
            entities = communityRepository.searchContent(keyword, pageable, entityPath);
        }
        ArrayList<CommunityDto> list = new ArrayList<>();
        for (CommunityEntity entity : entities) {
            list.add(entity.toDto());
        }
        return list;
    }

    //게시글 전체 개수 조회
    public long countCommunity() {
        return communityRepository.count();
    }

    //내 글 목록 개수 조회
    public long countMyList(Long memberNo, OrderSpecifier entityPath) {
        return communityRepository.countMyList(memberNo, entityPath);
    }

    public long countSearchList(String type, String keyword, Pageable pageable, OrderSpecifier entityPath) {
        long count;
        if (type.equals("title")) {
            count = communityRepository.searchTitleCount(keyword, pageable, entityPath);
        } else if (type.equals("writer")) {
            count = communityRepository.searchWriterCount(keyword, pageable, entityPath);
        } else {
            count = communityRepository.searchContentCount(keyword, pageable, entityPath);
        }
        return count;
    }
}

