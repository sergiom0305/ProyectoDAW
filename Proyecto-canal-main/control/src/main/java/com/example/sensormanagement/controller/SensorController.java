package com.example.sensormanagement.controller;

import com.example.sensormanagement.model.SensorData;
import com.example.sensormanagement.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    @Autowired
    private SensorDataService sensorDataService;

    @GetMapping
    public List<SensorData> getAllSensors() {
        return sensorDataService.getAllSensors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorData> getSensorById(@PathVariable Long id) {
        return sensorDataService.getSensorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SensorData createSensor(@RequestBody SensorData sensorData) {
        return sensorDataService.saveSensor(sensorData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorData> updateSensor(@PathVariable Long id, @RequestBody SensorData sensorData) {
        return sensorDataService.updateSensor(id, sensorData)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        if (sensorDataService.deleteSensor(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}