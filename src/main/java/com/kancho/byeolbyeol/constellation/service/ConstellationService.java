package com.kancho.byeolbyeol.constellation.service;

import com.kancho.byeolbyeol.constellation.domain.ConstellationRepository;
import com.kancho.byeolbyeol.constellation.dto.response.ResConstellationInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ConstellationService {

    private final ConstellationRepository constellationRepository;

    public List<ResConstellationInfoDto> getConstellationInfoList() {
        return constellationRepository.findAll()
                .stream()
                .map(constellation -> new ResConstellationInfoDto(constellation))
                .collect(Collectors.toList());
    }
}
