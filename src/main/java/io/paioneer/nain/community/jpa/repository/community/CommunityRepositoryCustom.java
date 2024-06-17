package io.paioneer.nain.community.jpa.repository.community;

import io.paioneer.nain.community.jpa.entity.CommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CommunityRepositoryCustom{
    ArrayList<CommunityEntity> findMyList(Long memberNo, Pageable pageable);
    ArrayList<CommunityEntity> searchTitle(String keyword, Pageable pageable);
    ArrayList<CommunityEntity> searchWriter(String keyword, Pageable pageable);
    ArrayList<CommunityEntity> searchContent(String keyword, Pageable pageable);
    ArrayList<CommunityEntity> findListAll(Pageable pageable);
    long countMyList(Long memberNo);
    long searchTitleCount(String keyword, Pageable pageable);
    long searchWriterCount(String keyword, Pageable pageable);
    long searchContentCount(String keyword, Pageable pageable);
    Long findLastNo();
}