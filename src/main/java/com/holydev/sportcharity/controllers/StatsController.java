package com.holydev.sportcharity.controllers;

import com.holydev.sportcharity.DTO.utilities.StatisticsInfo;
import com.holydev.sportcharity.services.EntityBased.utilities.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatsController {
    private final StatisticsService statisticsService;

    @GetMapping("/top10money")
    public ResponseEntity<List<StatisticsInfo>> getTop10money() {
        try {
            return ResponseEntity.ok(statisticsService.getTop10Money().stream().map(
                    x -> StatisticsInfo.builder()
                            .fio(x.getFio())
                            .amount(x.getMoney())
                            .build()).toList());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/top10train")
    public ResponseEntity<List<StatisticsInfo>> getTop10train() {
        try {
            return ResponseEntity.ok(statisticsService.getTop10TrainCount().stream().map(
                    x -> StatisticsInfo.builder()
                            .fio(x.fio())
                            .amount(x.amount())
                            .build()).toList());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
