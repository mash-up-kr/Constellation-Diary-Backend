package com.kancho.daily.application;

import com.kancho.common.util.MonthRange;
import com.kancho.daily.dto.response.ResCountYearDiaryDto;

import java.util.List;

public interface DiaryCountService {
    ResCountYearDiaryDto countDiaries(Long id, List<MonthRange> moths);
}
