package io.paioneer.nain.resume.jpa.repository;

import io.paioneer.nain.resume.jpa.entity.AcceptedKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcceptedKeywordRepository extends JpaRepository<AcceptedKeywordEntity, Long> {
    List<AcceptedKeywordEntity> findByJobCategory(String jobCategory);

    @Query(value = "SELECT * FROM (SELECT * FROM TB_ACCEPTED_KEYWORD WHERE JOB_CATEGORY = :jobCategory AND KEYWORD_TYPE = '직무' ORDER BY REFERENCE_DATE DESC, FREQUENCY DESC) WHERE ROWNUM <= 10", nativeQuery = true)
    List<AcceptedKeywordEntity> findTop10JobKeywordsByJobCategory(@Param("jobCategory") String jobCategory);

    @Query(value = "SELECT * FROM (SELECT * FROM TB_ACCEPTED_KEYWORD WHERE JOB_CATEGORY = :jobCategory AND KEYWORD_TYPE = '년차' ORDER BY REFERENCE_DATE DESC, FREQUENCY DESC) WHERE ROWNUM <= 10", nativeQuery = true)
    List<AcceptedKeywordEntity> findTop10ExpKeywordsByJobCategory(@Param("jobCategory") String jobCategory);

    @Query(value = "SELECT * FROM (SELECT * FROM TB_ACCEPTED_KEYWORD WHERE JOB_CATEGORY = :jobCategory ORDER BY REFERENCE_DATE DESC) WHERE ROWNUM = 1", nativeQuery = true)
    AcceptedKeywordEntity findFirstByJobCategoryOrderByReferenceDateDesc(@Param("jobCategory") String jobCategory);

}