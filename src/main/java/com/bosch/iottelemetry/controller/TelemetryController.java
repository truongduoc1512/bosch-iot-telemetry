package com.bosch.iottelemetry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bosch.iottelemetry.entity.TelemetryData;
import com.bosch.iottelemetry.repository.TelemetryRepository;

@RestController
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {
    @Autowired
    private TelemetryRepository repository;

    @PostMapping
    public ResponseEntity<String> receiveData(@RequestBody TelemetryData data) {
        repository.save(data);
        return ResponseEntity.ok("Dữ liệu viễn trắc đã được ghi nhận thành công!");
    }
}