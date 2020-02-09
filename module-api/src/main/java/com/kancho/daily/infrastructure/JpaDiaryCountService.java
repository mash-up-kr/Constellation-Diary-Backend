package com.kancho.daily.infrastructure;

import com.kancho.daily.application.DiaryCountService;
import com.kancho.common.util.MonthRange;
import com.kancho.daily.dto.response.ResCountYearDiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaDiaryCountService implements DiaryCountService {

    private final EntityManager em;

    @Override
    public ResCountYearDiaryDto countDiaries(Long id, List<MonthRange> moths) {
        ResCountYearDiaryDto resCountYearDiaryDto;

        String selectQuery =
                "SELECT new com.kancho.daily.dto.response.ResCountYearDiaryDto(" +
                        "Count(CASE WHEN d.date between ?1 and ?2 THEN 1 END), " +
                        "Count(CASE WHEN d.date between ?3 and ?4 THEN 1 END), " +
                        "Count(CASE WHEN d.date between ?5 and ?6 THEN 1 END), " +
                        "Count(CASE WHEN d.date between ?7 and ?8 THEN 1 END), " +
                        "Count(CASE WHEN d.date between ?9 and ?10 THEN 1 END), " +
                        "Count(CASE WHEN d.date between ?11 and ?12 THEN 1 END), " +
                        "Count(CASE WHEN d.date between ?13 and ?14 THEN 1 END), " +
                        "Count(CASE WHEN d.date between ?15 and ?16 THEN 1 END), " +
                        "Count(CASE WHEN d.date between ?17 and ?18 THEN 1 END), " +
                        "Count(CASE WHEN d.date between ?19 and ?20 THEN 1 END), " +
                        "Count(CASE WHEN d.date between ?21 and ?22 THEN 1 END), " +
                        "Count(CASE WHEN d.date between ?23 and ?24 THEN 1 END)) " +
                        "FROM Diary d " +
                        "WHERE d.usersId = ?25";
        TypedQuery<ResCountYearDiaryDto> query =
                em.createQuery(selectQuery, ResCountYearDiaryDto.class);

        for (int i = 0; i < 12; i++) {
            query.setParameter((i * 2) + 1, moths.get(i).getStartDate());
            query.setParameter((i * 2) + 2, moths.get(i).getEndDate());
        }

        query.setParameter(25, id);

        try {
            resCountYearDiaryDto = query.getSingleResult();
        } catch (NoResultException e) {
            resCountYearDiaryDto = new ResCountYearDiaryDto();
        }
        return resCountYearDiaryDto;
    }
}
