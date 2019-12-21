package com.kancho.byeolbyeol.constellation.service;

import com.kancho.byeolbyeol.constellation.domain.ConstellationRepository;
import com.kancho.byeolbyeol.constellation.dto.response.ResConstellationIconDto;
import com.kancho.byeolbyeol.constellation.dto.response.ResConstellationImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ConstellationService {

    private final ConstellationRepository constellationRepository;

    public List<ResConstellationImageDto> getConstellationImageList() {
        return constellationRepository.findAll()
                .stream()
                .map(constellation -> new ResConstellationImageDto(constellation))
                .collect(Collectors.toList());
    }

    public List<ResConstellationIconDto> getConstellationIconList() {
        return constellationRepository.findAll()
                .stream()
                .map(constellation -> new ResConstellationIconDto(constellation))
                .collect(Collectors.toList());
    }
}
