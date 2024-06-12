package io.paioneer.nain.community.jpa.repository.community;

import io.paioneer.nain.community.jpa.entity.CommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommunityRepositoryCustom{
    Page<CommunityEntity> findMyList(Long memberNo, Pageable pageable);
    Page<CommunityEntity> searchTitle(String keyword, Pageable pageable);
    Page<CommunityEntity> searchWriter(String keyword, Pageable pageable);
    Page<CommunityEntity> searchContent(String keyword, Pageable pageable);
    Page<CommunityEntity> findListAll(Pageable pageable);
    long countMyList(Long memberNo);
    long searchTitleCount(String keyword, Pageable pageable);
    long searchWriterCount(String keyword, Pageable pageable);
    long searchContentCount(String keyword, Pageable pageable);
}