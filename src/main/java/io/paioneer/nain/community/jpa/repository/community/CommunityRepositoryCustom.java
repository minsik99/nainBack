package io.paioneer.nain.community.jpa.repository.community;

import com.querydsl.core.types.OrderSpecifier;
import io.paioneer.nain.community.jpa.entity.CommunityEntity;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface CommunityRepositoryCustom{
    ArrayList<CommunityEntity> searchTitle(String keyword, Pageable pageable, OrderSpecifier entityPath);
    ArrayList<CommunityEntity> searchWriter(String keyword, Pageable pageable, OrderSpecifier entityPath);
    ArrayList<CommunityEntity> searchContent(String keyword, Pageable pageable, OrderSpecifier entityPath);
    long countMyList(Long memberNo, OrderSpecifier entityPath);
    long searchTitleCount(String keyword, Pageable pageable);
    long searchWriterCount(String keyword, Pageable pageable);
    long searchContentCount(String keyword, Pageable pageable);
    Long findLastNo();
}