package io.paioneer.nain.statistical.jpa.repository;

import io.paioneer.nain.subscribe.jpa.entity.SubscribeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticalRepository extends JpaRepository<SubscribeEntity, String>, StatisticalRepositoryCustom {
}
