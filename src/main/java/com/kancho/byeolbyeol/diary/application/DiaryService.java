package com.kancho.byeolbyeol.diary.application;

import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.diary.dto.ReqWriteDiaryDto;
import com.kancho.byeolbyeol.diary.domain.Diary;
import com.kancho.byeolbyeol.diary.domain.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public void writeDiary(UserInfo userInfo, ReqWriteDiaryDto reqWriteDiaryDto) {
        Diary diary = Diary.builder()
                .content(reqWriteDiaryDto.getContent())
                .date(LocalDateTime.now())
                .title(reqWriteDiaryDto.getTitle())
                .userId(userInfo.getId())
                .build();

        diaryRepository.save(diary);
    }
}
