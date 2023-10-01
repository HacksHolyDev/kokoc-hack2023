package com.holydev.sportcharity.DTO.utilities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsInfo {
    private String fio;
    private int amount;
}
