package io.paioneer.nain.subscribe.repository;

import io.paioneer.nain.subscribe.jpa.entity.SubscribeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<SubscribeEntity, Integer> {

}
