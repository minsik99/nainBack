package io.paioneer.nain.interview.jpa.repository;


import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, Long> , InterviewRepositoryCustom{
    @Query("select i from InterviewEntity i where i.memberEntity.memberNo = :memberNo")
    Page<InterviewEntity> findAllByMemberNo(Long memberNo, Pageable pageable);

    @Query("SELECT COUNT(s) FROM InterviewEntity s WHERE s.videoScore/2 + s.voiceScore/2  < :score")
    double countScoresLowerThan(@Param("score") double score);

    @Query("SELECT COUNT(s) FROM InterviewEntity s WHERE s.videoScore/2 + s.voiceScore/2 > :score")
    double countScoresHigherThan(@Param("score") double score);

    @Query("SELECT COUNT(s) FROM InterviewEntity s")
    double countTotalScores();






}
