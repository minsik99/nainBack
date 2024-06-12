package io.paioneer.nain.interview.jpa.repository;


import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, Long> , InterviewRepositoryCustom{

}
