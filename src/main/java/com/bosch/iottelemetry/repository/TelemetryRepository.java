package com.bosch.iottelemetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bosch.iottelemetry.entity.TelemetryData;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryData, Long> {
}