package com.kancho.byeolbyeol.diary.application;

import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.common.util.TimeConverter;
import com.kancho.byeolbyeol.diary.dto.ReqModifyDiaryDto;
import com.kancho.byeolbyeol.diary.dto.ReqWriteDiaryDto;
import com.kancho.byeolbyeol.diary.domain.Diary;
import com.kancho.byeolbyeol.diary.domain.DiaryRepository;
import com.kancho.byeolbyeol.diary.dto.ResDiaryDto;
import com.kancho.byeolbyeol.diary.exception.IsNotTheWriterException;
import com.kancho.byeolbyeol.diary.exception.NotFoundDiaryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
                .horoscopeId(reqWriteDiaryDto.getHoroscopeId())
                .userId(userInfo.getId())
                .build();

        diaryRepository.save(diary);
    }

    @Transactional
    public ResDiaryDto modifyDiary(UserInfo userInfo, ReqModifyDiaryDto reqModifyDiaryDto) {
        Diary diary = diaryRepository.findById(reqModifyDiaryDto.getDiaryId())
                .orElseThrow(NotFoundDiaryException::new);

        if(diary.isNotTheWriter(userInfo.getId())) {
            throw new IsNotTheWriterException();
        }

        diary.modify(reqModifyDiaryDto.getTitle(), reqModifyDiaryDto.getContent());

        return ResDiaryDto.builder()
                .id(diary.getId())
                .date(TimeConverter.covertDate(diary.getDate()))
                .title(diary.getTitle())
                .content(diary.getContent())
                .horoscopeId(diary.getHoroscopeId())
                .build();
    }
}
