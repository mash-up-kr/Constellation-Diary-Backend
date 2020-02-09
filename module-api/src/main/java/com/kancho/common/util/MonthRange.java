package com.kancho.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MonthRange {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
