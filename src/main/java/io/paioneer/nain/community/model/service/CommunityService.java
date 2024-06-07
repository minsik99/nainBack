package io.paioneer.nain.community.model.service;

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
    public ArrayList<CommunityDto> selectList(Pageable pageable) {
        Page<CommunityEntity> entities = communityRepository.findAll(pageable);
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
        communityRepository.save(new CommunityEntity(communityDto));
        return communityDto;
    }

    //등록
    public void insertCommunity(CommunityDto communityDto) {
        communityRepository.save(new CommunityEntity(communityDto));
    }

    //수정
    public void updateCommunity(CommunityDto communityDto) {
        CommunityEntity communityEntity = communityRepository.findById(communityDto.getCommunityNo()).get();
        log.info("updateCommunity{}", communityDto);
        communityEntity.setTitle(communityDto.getTitle());
        communityEntity.setContent(communityDto.getContent());
        communityRepository.save(communityEntity);
    }

    //삭제
    public void deleteCommunity(Long communityNo){
        communityRepository.deleteById(communityNo);
    }

    public ArrayList<CommunityDto> selectMyList(Long memberNo, Pageable pageable) {
        Page<CommunityEntity> entities = communityRepository.findMyList(memberNo, pageable);
        ArrayList<CommunityDto> list = new ArrayList<>();
        for (CommunityEntity entity : entities) {
            list.add(entity.toDto());
        }
        return list;
    }
}
