package com.microservices.demo.analytics.service.repository;

import com.microservices.demo.analytics.service.entity.AnalyticsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AnalyticsRepository extends JpaRepository < AnalyticsEntity, UUID > , AnalyticsCustomRepository < AnalyticsEntity, UUID > {

    @Query(value = "select E from AnalyticsEntity E where E.shareName=:shareName")
    List < AnalyticsEntity > getAnalyticsEntitiesByShareNameAndOrderByCreatedDate(@Param("shareName") String shareName, Pageable pageable);
}