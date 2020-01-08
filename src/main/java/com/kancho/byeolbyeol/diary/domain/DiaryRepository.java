package com.kancho.byeolbyeol.diary.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Query("select d from Diary d where d.usersId = ?1 and date(d.date) = ?2")
    Optional<Diary> findByUsersIdAndDate(Long userId, Date nowLocalDate);

    void deleteByUsersIdAndId(Long id, Long diaryId);
}
